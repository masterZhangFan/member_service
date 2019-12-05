package cn.gaozheng.sales.controller;

import cn.gaozheng.mini.util.SessionUtil;
import cn.gaozheng.sales.model.po.User;
import cn.gaozheng.sales.model.vo.Fan;
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
import java.util.List;

import static cn.gaozheng.mini.util.Constants.*;

@RestController
@Api(description = "用户登录")
@RequestMapping("/user")
public class UserController {
    @Autowired
    ILoginService loginService;
    @Autowired
    UserInfoService userInfoService;


    @ApiOperation(value = "获取粉丝")
    @GetMapping("/getDirectlyFanWithUserId")
    public ServiceStatus<List<Fan>> getDirectlyFanWithUserId(HttpSession httpSession){
        try {
            List<Fan> fans = userInfoService.getDirectlyFanWithUserId(userInfo(httpSession).getUserId());
            return new ServiceStatus(ServiceStatus.Status.Success,fans);
        }catch (Exception e){
            return new ServiceStatus(ServiceStatus.Status.Fail, ExceptionUtil.getExceptionDesc(e));
        }
    }
    @ApiOperation(value = "获取间接粉丝")
    @GetMapping("/getIndirectFanWithUserId")
    public ServiceStatus<List<Fan>> getIndirectFanWithUserId(HttpSession httpSession){
        try {
            List<Fan> fans = userInfoService.getIndirectFanWithUserId(userInfo(httpSession).getUserId());
            return new ServiceStatus(ServiceStatus.Status.Success,fans);
        }catch (Exception e){
            return new ServiceStatus(ServiceStatus.Status.Fail, ExceptionUtil.getExceptionDesc(e));
        }
    }


    private UserInfo userInfo(HttpSession httpSession){
        return SessionUtil.getSignInClient(httpSession);
    }
}
