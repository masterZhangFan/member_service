package cn.gaozheng.sales.service.impl;

import cn.gaozheng.sales.exception.SaleException;
import cn.gaozheng.sales.mapper.*;
import cn.gaozheng.sales.model.po.*;
import cn.gaozheng.sales.model.vo.UserInfo;
import cn.gaozheng.sales.model.vo.base.EnumUtils;
import cn.gaozheng.sales.model.vo.charge.UserCommissionApplayParm;
import cn.gaozheng.sales.model.vo.charge.UserCommssionSetParm;
import cn.gaozheng.sales.service.*;
import cn.gaozheng.sales.utils.BeanUtils;
import cn.gaozheng.sales.utils.EmptyUtil;
import cn.gaozheng.sales.utils.SendPostUtil;
import cn.gaozheng.sales.wechart.WXPayUtil;
import cn.gaozheng.util.NumberUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ChargeServiceImpl implements ChargeService {
    @Autowired
    TblChargeMapper tblChargeMapper;
    @Autowired
    TblChargeConfigMapper tblChargeConfigMapper;
    @Autowired
    TblMemberSettingMapper tblMemberSettingMapper;
    @Autowired
    TblPayOrderMapper tblPayOrderMapper;
    @Autowired
    FieldAccountMapper fieldAccountMapper;
    @Autowired
    TblCashBackMapper tblCashBackMapper;
    @Autowired
    UserCommissionMapper userCommissionMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ILoginService iLoginService;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    RbTreeService rbTreeService;
    @Autowired
    DelegateService delegateService;
    @Autowired
    UserCommissionSetMapper userCommissionSetMapper;
    @Autowired
    UserWithdrawSetMapper userWithdrawSetMapper;
    @Autowired
    UserCommissionLogMapper userCommissionLogMapper;
    @Autowired
    UserWithdrawLogMapper userWithdrawLogMapper;

    @Override
    public List<TblCharge> chargeList(){
        return tblChargeMapper.chargeList();
    }
    @Override
    public UserCommissionSet getUserCommissionSet(Long userId){
        User user =userMapper.selectByPrimaryKey(userId);
        if (user == null) throw new SaleException("用户不存在");
       return userCommissionSetMapper.getWithUid(user.getUserName());
    }
    @Override
    public Boolean setUserCommissionSet(UserCommssionSetParm userCommssionSetParm, Long userId){
        User user =userMapper.selectByPrimaryKey(userId);
        if (user == null) throw new SaleException("用户不存在");
        UserCommissionSet userCommissionSet  = this.getUserCommissionSet(userId);
        if (!EmptyUtil.isNotEmpty(userCommssionSetParm.getAlipayAccount())){
            throw new SaleException("支付宝账号错误");
        }
        if (!EmptyUtil.isNotEmpty(userCommssionSetParm.getAlipayRealname())){
            throw new SaleException("支付宝账号名称错误");
        }
        if (userCommissionSet == null){
            userCommissionSet = new UserCommissionSet();
        }
        BeanUtils.copyPropertiesIgnoreNullValue(userCommssionSetParm,userCommissionSet);
        userCommissionSet.setUid(user.getUserName());
        if (userCommissionSet.getId() == null ){
            userCommissionSetMapper.insert(userCommissionSet);
        }
        else {
            userCommissionSetMapper.updateByPrimaryKey(userCommissionSet);
        }
       return true;
    }
    @Override
    public Boolean userCommissionApplay( UserCommissionApplayParm userCommissionApplayParm,Long userId){
        userCommissionApplayParm.checkException();
        User user =userMapper.selectByPrimaryKey(userId);
        if (user == null) throw new SaleException("用户不存在");
        UserInfo userInfo = userInfoService.getUserInfo(userId);
        if (new BigDecimal(userInfo.getCash().toString()).compareTo(new BigDecimal(userCommissionApplayParm.getAlipayAccount()))<0){
            throw new SaleException("超出可提现金额");
        }
        UserWithdrawSet userWithdrawSet = getUserWithdrawSet();
        if (userCommissionApplayParm.getAmount().compareTo(userWithdrawSet.getMaxPrice())>0){
            throw new SaleException("最多提现"+userWithdrawSet.getMaxPrice()+"元");
        }
        if (userCommissionApplayParm.getAmount().compareTo(userWithdrawSet.getMinPrice())<0){
            throw new SaleException("最多提现"+userWithdrawSet.getMaxPrice()+"元");
        }
        UserCommission userCommission = getUserCommission(user.getUserName());
        userCommission.setAuditing(userCommission.getAuditing().add(new BigDecimal(userCommissionApplayParm.getAmount())));
        userCommissionMapper.updateByPrimaryKey(userCommission);
        insertLog(user,userCommissionApplayParm,userCommission.getTotal());
        return true;
    }
    private void insertLog(User user,UserCommissionApplayParm userCommissionApplayParm,BigDecimal oldCommission){
        UserCommissionLog userCommissionLog = new UserCommissionLog();
        userCommissionLog.setUid(user.getUserName());
        userCommissionLog.setType(2);//申请
        userCommissionLog.setDescribe("申请提现");
        userCommissionLog.setBill(new BigDecimal(userCommissionApplayParm.getAmount()));
        userCommissionLog.setOldCommission(oldCommission);
        userCommissionLog.setCreateTime(new Date());
        userCommissionLog.setStatus(2);
        userCommissionLogMapper.insert(userCommissionLog);

        UserWithdrawLog userWithdrawLog = new UserWithdrawLog();
        userWithdrawLog.setUid(userCommissionLog.getUid());
        userWithdrawLog.setBill(userCommissionLog.getBill());
        userWithdrawLog.setToAccount("alipay");
        userWithdrawLog.setToRealname(userCommissionApplayParm.getAlipayRealname());
        userWithdrawLog.setSubmitRemark(userWithdrawLog.getUid()+"【"+user.getPhone()+"】提现申请");
        userWithdrawLog.setCreateTime(userCommissionLog.getCreateTime());
        userWithdrawLog.setStatus(2);
        userWithdrawLogMapper.insert(userWithdrawLog);

    }
    private UserWithdrawSet getUserWithdrawSet(){
        List<UserWithdrawSet> userWithdrawSets = userWithdrawSetMapper.selectAll();
        if (userWithdrawSets == null || userWithdrawSets.size() == 0) throw new SaleException("未配置用户最大提现数");
        return userWithdrawSets.get(0);
    }
    private UserCommission getUserCommission(String uid){
        UserCommission userCommission = userCommissionMapper.getUserCommission(uid);
        if (userCommission == null){
            userCommission = new UserCommission();
            userCommission.setUid(uid);
            userCommission.setTotal(new BigDecimal(0));
            userCommission.setSettlement(new BigDecimal(0));
            userCommission.setAuditing(new BigDecimal(0));
            userCommissionMapper.insert(userCommission);
        }
        return userCommission;
    }
    public Map orders( HttpServletRequest request, String code,Integer payFor,Integer chargeId,Long userId) {
        try {
           TblChargeConfig tblChargeConfig = getChargeConfig();
            TblPayOrder tblPayOrder = this.createOrder(payFor,chargeId,userId);
            String order = tblPayOrder.getPayOrder();
            String  appid = tblChargeConfig.getAppid();
            String  mch_id= tblChargeConfig.getMchId();
            String  secret=  tblChargeConfig.getSecret();
            String  paternerKey=  tblChargeConfig.getApikey();
            String notify_url =  tblChargeConfig.getNotifyUrl();
            //页面获取openId接口
            String openId = iLoginService.wxToken(code);//获取openId

            //拼接统一下单地址参数
            Map<String, String> paraMap = new HashMap<>();
            //获取请求ip地址
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            if (ip.indexOf(",") != -1) {
                String[] ips = ip.split(",");
                ip = ips[0].trim();
            }
            paraMap.put("appid", appid);
            paraMap.put("body", "尧舜商城-订单结算");
            paraMap.put("mch_id", mch_id);
            paraMap.put("nonce_str", WXPayUtil.generateNonceStr());
            paraMap.put("openid", openId);
            paraMap.put("out_trade_no", order);//订单号
            paraMap.put("spbill_create_ip", ip);
            paraMap.put("total_fee",new BigDecimal(tblPayOrder.getPayMoney().toString()).multiply(new BigDecimal(100)).toBigInteger().toString());
            paraMap.put("trade_type", "JSAPI");
            paraMap.put("notify_url",notify_url);// 此路径是微信服务器调用支付结果通知路径随意写
            String sign = WXPayUtil.generateSignature(paraMap, paternerKey);
            paraMap.put("sign", sign);
            String xml = WXPayUtil.mapToXml(paraMap);//将所有参数(map)转xml格式

            // 统一下单 https://api.mch.weixin.qq.com/pay/unifiedorder
            String unifiedorder_url =" https://api.mch.weixin.qq.com/pay/unifiedorder";

            String xmlStr = SendPostUtil.sendPost(unifiedorder_url, xml,null);//发送post请求"统一下单接口"返回预支付id:prepay_id
            //以下内容是返回前端页面的json数据
            String prepay_id = "";//预支付id
            if (xmlStr.indexOf("SUCCESS") != -1) {
                Map<String, String> map = WXPayUtil.xmlToMap(xmlStr);
                prepay_id = (String) map.get("prepay_id");
            }
            Map<String, String> payMap = new HashMap<String, String>();
            payMap.put("appId", appid);
            payMap.put("timeStamp", WXPayUtil.getCurrentTimestamp() + "");
            payMap.put("nonceStr", WXPayUtil.generateNonceStr());
            payMap.put("signType", "MD5");
            payMap.put("package", "prepay_id=" + prepay_id);
            String paySign = WXPayUtil.generateSignature(payMap, paternerKey);
            payMap.put("paySign", paySign);
            return payMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private TblPayOrder createOrder(Integer payFor,Integer chargeId,Long userId){
        TblMemberSetting tblMemberSetting = null;
        String order = getOrderNo();
        TblPayOrder tblPayOrder = new TblPayOrder();
        tblPayOrder.setPayOrder(order);
        tblPayOrder.setCreateTime(new Date());
        tblPayOrder.setPayFor(payFor);
        tblPayOrder.setPayType(1);
        tblPayOrder.setIsPaySuccess(false);
        tblPayOrder.setUserId(userId);
        if (payFor == 1 ){
            tblMemberSetting = getMemberSetting();
            tblPayOrder.setPayMoney(Double.parseDouble(tblMemberSetting.getMemberPrice()+""));
        }
        else {
            if (chargeId == null || chargeId < 1){
                throw new SaleException("chargeId必填");
            }
            TblCharge tblCharge = tblChargeMapper.selectByPrimaryKey(chargeId);
            if (tblCharge == null) throw new SaleException("充值配置错误");
            tblPayOrder.setPayMoney(tblCharge.getChargeAmount());
        }
        return tblPayOrder;
    }
    @Override
    public TblChargeConfig getChargeConfig(){
       List<TblChargeConfig> tblChargeConfigList =   tblChargeConfigMapper.selectAll();
       if (tblChargeConfigList == null || tblChargeConfigList.size() == 0) throw new SaleException("无支付配置");
       return tblChargeConfigList.get(0);
    }
    private TblMemberSetting getMemberSetting(){
        List<TblMemberSetting> tblMemberSettingList = tblMemberSettingMapper.selectAll();
        if (tblMemberSettingList == null || tblMemberSettingList.size() == 0){
            throw new SaleException("无效的会员配置");
        }
        return tblMemberSettingList.get(0);
    }
    public static String getRandomStringByLength(int length) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    @Override
    public  Boolean chargeSuccess(String payOrder){
        TblPayOrder tblPayOrder = tblPayOrderMapper.getPayorder(payOrder);
        if (tblPayOrder == null) throw new SaleException("订单不存在");

        UserInfo userInfo = userInfoService.getUserInfo(tblPayOrder.getUserId());
        if (userInfo == null) {
            throw new SaleException("用户不存在");
        }
        if (EnumUtils.ChargeTypeBalance.equals(tblPayOrder.getPayFor())){
            //充值
            FieldAccount fieldAccount = fieldAccountMapper.getFieldAccountWithUserId(tblPayOrder.getUserId());
            Long beforeShoppingAmout = fieldAccount.getBalance()!= null?fieldAccount.getBalance():0L;
            Float beforeCallAmount = fieldAccount.getPrice()!= null?fieldAccount.getPrice():0.0F;
            Long shoppingAmount = tblPayOrder.getShoppingAmount()!= null?Long.parseLong(NumberUtil.multiply( tblPayOrder.getShoppingAmount(), EnumUtils.ShoppingMultiplyParm).toString()):0L;
            Float callAmount = tblPayOrder.getCallAmount()!= null?tblPayOrder.getCallAmount():0.0F;
            fieldAccount.setBalance(beforeShoppingAmout+shoppingAmount);
            fieldAccount.setPrice(beforeCallAmount+callAmount);
            fieldAccountMapper.updateByPrimaryKey(fieldAccount);
            ChargeLog chargeLog = new ChargeLog();
            chargeLog.setChargeeMoneyBefore(beforeShoppingAmout);
            chargeLog.setChargerMoneyBefore(beforeShoppingAmout);
            chargeLog.setChargeeCashBefore(NumberUtil.multiply(Double.valueOf(beforeCallAmount.toString()),1L).longValue());
            chargeLog.setChargerCashBefore(NumberUtil.multiply(Double.valueOf(beforeCallAmount.toString()),1L).longValue());
            chargeLog.setMoney(shoppingAmount);
            chargeLog.setCash(new BigDecimal(callAmount.toString()).toBigInteger().longValue());
            chargeLog.setChargeeMoneyAfter(fieldAccount.getBalance());
            chargeLog.setChargerMoneyAfter(fieldAccount.getBalance());
            chargeLog.setChargeeCashAfter(NumberUtil.multiply(Double.valueOf(fieldAccount.getPrice().toString()),1L).longValue());
            chargeLog.setChargerCashAfter(NumberUtil.multiply(Double.valueOf(fieldAccount.getPrice().toString()),1L).longValue());
            chargeLog.setTime(new Date());
            chargeLog.setChargeeId(tblPayOrder.getUserId());
            chargeLog.setChargeeName(userInfo!=null?userInfo.getPhone():"");
            chargeLog.setOperateType("充值");
            chargeLog.setRemark("公众号充值");
            chargeLog.setOrderId(tblPayOrder.getPayOrder());
        }
        else if (EnumUtils.ChargeTypeMember.equals(tblPayOrder.getPayFor())){
            //会员充值
            TblMemberSetting tblMemberSetting = this.getMemberSetting();
            User firstLevelUserBase = rbTreeService.fatherTree(userInfo.getUserId());
            if (firstLevelUserBase!= null){
                UserInfo firstLevelUserInfo = userInfoService.getUserInfo(firstLevelUserBase.getUserId());
                if (firstLevelUserInfo.getMemberLevel().equals(EnumUtils.MemberTypeJuniorSenior)){
                    //处理返现50元
                    cashBack(userInfo.getUserId(),firstLevelUserBase.getUserId(),tblMemberSetting.getOnceLevelCashback().doubleValue(),EnumUtils.CashBackTypeFirstLevel);
                }
                User secondLevelUser = rbTreeService.fatherTree(firstLevelUserInfo.getUserId());
                if (secondLevelUser != null){
                    UserInfo secondUserInfo = userInfoService.getUserInfo(secondLevelUser.getUserId());
                    if (secondUserInfo.getMemberLevel().equals(EnumUtils.MemberTypeJuniorSenior)){
                        //处理返现50元
                        cashBack(userInfo.getUserId(),secondLevelUser.getUserId(),tblMemberSetting.getSecondLevelCashback().doubleValue(),EnumUtils.CashBackTypeSecondLevel);
                    }
                }
            }
            User userDelegateBase = delegateService.getDelegateOfUser(userInfo.getUserId());
            if (userDelegateBase!= null){
                Double diffCash =0.0;
                UserInfo userDelegateInfo = userInfoService.getUserInfo(userDelegateBase.getUserId());
                if (userDelegateInfo.getDelegate()!= null && userDelegateInfo.getDelegate().getDelegateEnbale()){
                    cashBack(userInfo.getUserId(),userDelegateBase.getUserId(),userDelegateInfo.getDelegate().getCashBackAmount(),EnumUtils.CashBackTypeDelegate);
                    diffCash = userDelegateInfo.getDelegate().getCashBackAmount();
                }
                User delegateFatherBase = delegateService.fatherDelegate(userDelegateBase.getUserId());
                if (delegateFatherBase != null){
                    UserInfo delegateFatherInfo = userInfoService.getUserInfo(userDelegateBase.getUserId());
                    if (delegateFatherInfo.getDelegate()!= null && delegateFatherInfo.getDelegate().getDelegateEnbale()){
                        cashBack(userInfo.getUserId(),delegateFatherInfo.getUserId(),delegateFatherInfo.getDelegate().getCashBackAmount() - diffCash,EnumUtils.CashBackTypeDelegate);
                    }
                }
            }
        }
        User user = userMapper.selectByPrimaryKey(tblPayOrder.getUserId());
        userMapper.setMemberLevel(user.getUserName());
        tblPayOrder.setIsPaySuccess(true);
        tblPayOrderMapper.updateByPrimaryKey(tblPayOrder);
        return true;
    }
    @Transactional
    public void cashBack(Long fromUserId,Long toUserId,Double cashBack,Integer cashType){

        User user = userMapper.selectByPrimaryKey(toUserId);
        UserCommission userCommission = new UserCommission();
        userCommission.setUid(user.getUserName());
        userCommission.setTotal(null);
        userCommission.setSettlement(new BigDecimal(cashBack));
        userCommissionMapper.insert(userCommission);

        TblCashBack tblCashBack = new TblCashBack();
        tblCashBack.setCashBackFromUserId(fromUserId);
        tblCashBack.setCashBackToUserId(toUserId);
        tblCashBack.setCashBackMoney(cashBack);
        tblCashBack.setCashBackType(cashType);
        tblCashBackMapper.insert(tblCashBack);
    }
    /**
     * @function 生成商户订单号/退款单号
     * @date 2015-12-17
     * @return String
     */
    public static String getOrderNo(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date date = new Date();
        return sdf.format(date) + getRandomStringByLength(4);
    }
}
