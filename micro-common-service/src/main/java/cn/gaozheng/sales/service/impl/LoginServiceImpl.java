package cn.gaozheng.sales.service.impl;

import cn.gaozheng.mini.config.SendSms;
import cn.gaozheng.mini.exception.ServiceException;
import cn.gaozheng.sales.exception.SaleException;
import cn.gaozheng.sales.exception.SalesException;
import cn.gaozheng.sales.mapper.TblChargeConfigMapper;
import cn.gaozheng.sales.mapper.TblDelegateMapper;
import cn.gaozheng.sales.mapper.TblMemberMapper;
import cn.gaozheng.sales.mapper.UserMapper;
import cn.gaozheng.sales.model.po.*;
import cn.gaozheng.sales.model.vo.*;
import cn.gaozheng.sales.model.vo.base.EnumUtils;
import cn.gaozheng.sales.service.ILoginService;
import cn.gaozheng.sales.service.UserInfoService;
import cn.gaozheng.sales.utils.EmptyUtil;
import cn.gaozheng.sales.utils.SendPostUtil;
import cn.gaozheng.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

import java.util.List;

import static cn.gaozheng.mini.util.Constants.*;

@Slf4j
@AllArgsConstructor
@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    TblMemberMapper tblMemberMapper;
    @Autowired
    TblDelegateMapper tblDelegateMapper;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    TblChargeConfigMapper tblChargeConfigMapper;
    @Override
    public String sendSms(String phone,HttpSession session) throws Exception {
        String code = RandomUtil.getInstance().flexibleRandom(6);
        log.debug("发送验证码：phone: {}\nmsg: {}", phone, code);
        SendSms.sendMsg(phone,code);
        session.setAttribute(SESSION_SMS_CODE_KEY, code);
        session.setAttribute(SESSION_SMS_START__KEY, System.currentTimeMillis());
        session.setAttribute(SESSION_SMS_PHONE_NUMBER_KEY,
                phone);
        return code;
    }
    public String wxToken(String code){
        if (!EmptyUtil.isNotEmpty(code)){
            throw new SaleException("请传入code参数");
        }
        TblChargeConfig tblChargeConfig = getChargeConfig();
        String  appid = tblChargeConfig.getAppid();
        String  secret=  tblChargeConfig.getSecret();
        //页面获取openId接口
        String getopenid_url = "https://api.weixin.qq.com/sns/oauth2/access_token";
        String param =
                "appid=" + appid + "&secret=" + secret + "&code=" + code + "&grant_type=authorization_code";
        String errMsg = "获取失败";
        String openId = null;
        try {
            String openIdStr = SendPostUtil.sendGet(getopenid_url,param,null);
            JSONObject json = JSONObject.parseObject(openIdStr);//转成Json格式
            openId = json.getString("openid");//获取openId
        }
      catch (Exception ex){

      }
      if (!EmptyUtil.isNotEmpty(openId)){
          throw new SaleException(errMsg);
      }
        return openId;
    }
    private TblChargeConfig getChargeConfig(){
        List<TblChargeConfig> tblChargeConfigList =   tblChargeConfigMapper.selectAll();
        if (tblChargeConfigList == null || tblChargeConfigList.size() == 0) throw new SaleException("无支付配置");
        return tblChargeConfigList.get(0);
    }
    @Override
    public UserInfo signIn( LoginModel loginModel, HttpSession httpSession){
        User user =  null;
        UserInfo userInfo = null;
        if (EmptyUtil.isNotEmpty(loginModel.getPhoneNumber()) && EmptyUtil.isNotEmpty(loginModel.getCode())){
            signInCheck(loginModel.getPhoneNumber(), loginModel.getCode(), httpSession);
             user = checkUser(loginModel.getPhoneNumber());
        }
        else if (EmptyUtil.isNotEmpty(loginModel.getOpenId())){
            user = checkUserWithOpenId(loginModel.getOpenId());
        }
        else {
            throw new SaleException("登录参数错误");
        }
        userInfo = userInfoService.getUserInfo(user.getUserId());
        //更新用户openId
        if (EnumUtils.LoginTypeDelegate.equals(loginModel.getLoginType())){
            if (userInfo.getDelegate() == null) throw new SalesException("不是代理");
        }
        if (EnumUtils.LoginTypeMember.equals(loginModel.getLoginType())){
            if (userInfo.getDelegate()!= null){
                throw new SaleException("请直接代理登录");
            }
        }
        if (StringUtils.isNotEmpty(loginModel.getOpenId())) {
            //清空
            userMapper.clearOpenId(loginModel.getOpenId());
            user.setWxOpenId(loginModel.getOpenId());
            userMapper.updateByPrimaryKey(user);
        }
        httpSession.setAttribute(SESSION_USER_CACHE_KEY, userInfo);
        httpSession.removeAttribute(SESSION_SMS_CODE_KEY);
        httpSession.removeAttribute(SESSION_SMS_START__KEY);
        httpSession.removeAttribute(SESSION_SMS_PHONE_NUMBER_KEY);
        return userInfo;
    }



    private static final String msg = "验证码失效，请重新获取";
    public static void signInCheck(String phoneNumber, String code, HttpSession session) {

        Object o1 = session.getAttribute(SESSION_SMS_CODE_KEY);
        Object o2 = session.getAttribute(SESSION_SMS_START__KEY);
        Object o3 = session.getAttribute(SESSION_SMS_PHONE_NUMBER_KEY);
        if (o1 == null || o2 == null || o3 == null) {
            throw new ServiceException(msg);
        }
        String sendCode = String.valueOf(o1);
        long sendMillis = Long.parseLong(String.valueOf(o2));
        String sendPhoneNumber = String.valueOf(o3);
        if (!sendPhoneNumber.equals(phoneNumber)) {
            throw new ServiceException("请检查输入的手机号码");
        }
        long bygone = (System.currentTimeMillis() - sendMillis) / 1000 / 60;
        if (bygone > 5) {
            throw new ServiceException(msg);
        }
        if (!sendCode.equals(code)) {
            throw new ServiceException(msg);
        }
    }
    private User checkUser(String phoneNumber){
        User user = userMapper.getUserWithPhoneNumber(phoneNumber);
        if (user == null){
            throw new SaleException("用户不存在");
        }
        return user;
    }
    private User checkUserWithOpenId(String openId){
        User user = userMapper.getUserWithOpenId(openId);
        if (user == null){
            throw new SaleException("用户不存在");
        }
        return user;
    }
}
