package cn.gaozheng.sales.controller;

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
    @GetMapping("/sendSms")
    public ServiceStatus sendSms(@RequestBody LoginModel loginModel, HttpSession session) {
        try {
            return new ServiceStatus(ServiceStatus.Status.Success,loginService.sendSms(loginModel.getPhoneNumber(),session));
        }catch (Exception e){
            return new ServiceStatus(ServiceStatus.Status.Fail, ExceptionUtil.getExceptionDesc(e));
        }
    }
    @ApiOperation(value = "获取微信token")
    @GetMapping("/wxToken")
    public ServiceStatus wxToken(String code) {
        try {
            return new ServiceStatus(ServiceStatus.Status.Success,loginService.wxToken(code));
        }catch (Exception e){
            return new ServiceStatus(ServiceStatus.Status.Fail, ExceptionUtil.getExceptionDesc(e));
        }
    }
    @ApiOperation(value = "登录")
    @PostMapping("/signIn")
    public ServiceStatus<UserInfo> users( @RequestBody LoginModel loginModel, HttpSession httpSession){
        try {
            UserInfo userInfo = loginService.signIn(loginModel,httpSession);
            return new ServiceStatus(ServiceStatus.Status.Success,userInfo);
        }catch (Exception e){
            return new ServiceStatus(ServiceStatus.Status.Fail, ExceptionUtil.getExceptionDesc(e));
        }
    }

}
