package cn.gaozheng.sales.service.impl;

import cn.gaozheng.mini.config.SendSms;
import cn.gaozheng.sales.exception.SaleException;
import cn.gaozheng.sales.exception.SalesException;
import cn.gaozheng.sales.mapper.TblChargeConfigMapper;
import cn.gaozheng.sales.mapper.TblDelegateMapper;
import cn.gaozheng.sales.mapper.UserMapper;
import cn.gaozheng.sales.model.po.*;
import cn.gaozheng.sales.model.vo.*;
import cn.gaozheng.sales.model.vo.base.EnumUtils;
import cn.gaozheng.sales.service.ILoginService;
import cn.gaozheng.sales.service.SmsSendService;
import cn.gaozheng.sales.service.TokenUtilsServer;
import cn.gaozheng.sales.service.UserInfoService;
import cn.gaozheng.sales.utils.EmptyUtil;
import cn.gaozheng.sales.utils.NumberUtils;
import cn.gaozheng.sales.utils.SendPostUtil;
import cn.gaozheng.sales.utils.TimeUtils;
import cn.gaozheng.util.NumberUtil;
import cn.gaozheng.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.List;

import static cn.gaozheng.mini.util.Constants.*;

@Slf4j
@AllArgsConstructor
@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    TblDelegateMapper tblDelegateMapper;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    TblChargeConfigMapper tblChargeConfigMapper;
    @Autowired
    SmsSendService smsSendService;
    @Autowired
    TokenUtilsServer tokenUtilsServer;

    @Override
    public String sendSms(String phone) throws Exception {
        if (!NumberUtils.isPhone(phone)){
            throw new SaleException("手机号格式错误");
        }
        checkUser(phone);
        String code = RandomUtil.getInstance().flexibleRandom(6);
        SendSms.sendMsg(phone,code);
        log.debug("发送验证码：phone: {}\nmsg: {}", phone, code);
        smsSendService.insertSmsInfo(phone,code);
        return null;
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
        return openId;
    }
    private TblChargeConfig getChargeConfig(){
        List<TblChargeConfig> tblChargeConfigList =   tblChargeConfigMapper.selectAll();
        if (tblChargeConfigList == null || tblChargeConfigList.size() == 0) throw new SaleException("无支付配置");
        return tblChargeConfigList.get(0);
    }
    @Override
    public UserInfo signIn( LoginModel loginModel){
        User user =  null;
        UserInfo userInfo = null;
        if (EmptyUtil.isNotEmpty(loginModel.getPhoneNumber()) && EmptyUtil.isNotEmpty(loginModel.getCode())){
            signInCheck(loginModel.getPhoneNumber(), loginModel.getCode());
             user = checkUser(loginModel.getPhoneNumber());
        }
        else if (EmptyUtil.isNotEmpty(loginModel.getOpenId())){
            user = checkUserWithOpenId(loginModel.getOpenId());
        }
        else {
            throw new SaleException("登录参数错误");
        }
        userInfo = userInfoService.getUserInfo(user.getUserId());
        userInfo.setToken(tokenUtilsServer.generateTokenString(userInfo.getUserId(), TimeUtils.formatDateString(new Date())));
        //更新用户openId
        if (EnumUtils.LoginTypeDelegate.equals(loginModel.getLoginType()))
        {
            if (userInfo.getDelegate() == null) throw new SalesException("不是代理");
        }
        if (EnumUtils.LoginTypeMember.equals(loginModel.getLoginType()))
        {
            if (userInfo.getDelegate()!= null){throw new SaleException("请直接代理登录"); }
        }
        if (StringUtils.isNotEmpty(loginModel.getOpenId())) {
            //清空
            userMapper.clearOpenId(loginModel.getOpenId());
            user.setWxOpenId(loginModel.getOpenId());
            userMapper.updateByPrimaryKey(user);
        }
        return userInfo;
    }
    public void signInCheck(String phoneNumber, String code) {
        smsSendService.checkSmsCode(phoneNumber,code);
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
