package cn.gaozheng.terminal.controller;

import cn.gaozheng.mini.util.SessionUtil;
import cn.gaozheng.sales.model.vo.Fan;
import cn.gaozheng.sales.model.vo.UserInfo;
import cn.gaozheng.sales.service.ILoginService;
import cn.gaozheng.sales.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    public List<Fan> getDirectlyFanWithUserId(HttpSession httpSession){
        List<Fan> fans = userInfoService.getDirectlyFanWithUserId(userInfo(httpSession).getUserId());
        return fans;
    }
    @ApiOperation(value = "获取推荐粉丝")
    @GetMapping("/getIndirectFanWithUserId")
    public List<Fan> getIndirectFanWithUserId(HttpSession httpSession){
        List<Fan> fans = userInfoService.getIndirectFanWithUserId(userInfo(httpSession).getUserId());
        return fans;
    }
    private UserInfo userInfo(HttpSession httpSession){
        return SessionUtil.getSignInClient(httpSession);
    }
}
