package cn.gaozheng.mini.controller;

import cn.gaozheng.sales.model.vo.LoginModel;
import cn.gaozheng.sales.model.vo.UserInfo;
import cn.gaozheng.sales.model.vo.base.ServiceStatus;
import cn.gaozheng.sales.service.ILoginService;
import cn.gaozheng.sales.service.UserInfoService;
import cn.gaozheng.sales.utils.ExceptionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@Api(description = "用户登录")
@RequestMapping("/login")
public class LoginController {
    @Autowired
    ILoginService loginService;
    @Autowired
    UserInfoService userInfoService;

    @ApiOperation(value = "短信发送")
    @PostMapping("/sendSms")
    public String sendSms(@RequestBody LoginModel loginModel, HttpSession session) throws Exception {
        return loginService.sendSms(loginModel.getPhoneNumber(),session);
    }
    @ApiOperation(value = "获取微信token")
    @GetMapping("/wxToken")
    public String wxToken(String code) {
        return loginService.wxToken(code);
    }
    @ApiOperation(value = "登录")
    @PostMapping("/signIn")
    public UserInfo users( @RequestBody LoginModel loginModel, HttpSession httpSession){
       UserInfo userInfo = loginService.signIn(loginModel,httpSession);
        return userInfo;
    }

}
