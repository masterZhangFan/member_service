package cn.gaozheng.terminal.controller;

import cn.gaozheng.sales.model.vo.LoginModel;
import cn.gaozheng.sales.model.vo.UserInfo;
import cn.gaozheng.sales.service.ILoginService;
import cn.gaozheng.sales.service.UserInfoService;
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
    public String sendSms(@RequestBody LoginModel loginModel) throws Exception {
        return loginService.sendSms(loginModel.getPhoneNumber());
    }
    @ApiOperation(value = "获取微信token")
    @GetMapping("/wxToken")
    public String wxToken(String code) {
        return loginService.wxToken(code);
    }
    @ApiOperation(value = "登录")
    @PostMapping("/signIn")
    public UserInfo users( @RequestBody LoginModel loginModel){
       UserInfo userInfo = loginService.signIn(loginModel);
        return userInfo;
    }

}
