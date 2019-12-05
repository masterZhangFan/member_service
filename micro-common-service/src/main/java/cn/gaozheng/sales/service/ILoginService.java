package cn.gaozheng.sales.service;

import cn.gaozheng.sales.model.po.User;
import cn.gaozheng.sales.model.vo.LoginModel;
import cn.gaozheng.sales.model.vo.UserInfo;

import javax.servlet.http.HttpSession;

public interface ILoginService {
    String sendSms(String phone,HttpSession session) throws Exception ;

    /**
     * 登录之后返回token
     * @param loginModel
     * @return
     */
    UserInfo signIn( LoginModel loginModel, HttpSession session);

    /**
     * 微信token
     * @param code
     * @return
     */
    String wxToken(String code);

}
