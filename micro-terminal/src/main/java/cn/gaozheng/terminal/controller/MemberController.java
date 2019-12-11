package cn.gaozheng.terminal.controller;

import cn.gaozheng.sales.model.vo.UserInfo;
import cn.gaozheng.sales.model.vo.base.ServiceStatus;
import cn.gaozheng.sales.model.vo.member.MemberListParm;
import cn.gaozheng.sales.service.MemberService;
import cn.gaozheng.sales.service.TokenUtilsServer;
import cn.gaozheng.sales.utils.ExceptionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Api(description = "会员")
@RequestMapping("/member")
public class MemberController {
    @Autowired
    MemberService memberService;
    @Autowired
    TokenUtilsServer tokenUtilsServer;

    @ApiOperation(value = "会员列表(不是代理)")
    @GetMapping("/memberInfoNotIncludeDelegate")
    public List<UserInfo> memberInfoNotIncludeDelegate(HttpServletRequest request){
        Long uid = tokenUtilsServer.uid(request);
        MemberListParm req = new MemberListParm();
        req.setUserId(uid);
        List<UserInfo> userInfoList= memberService.memberInfoNotIncludeDelegate(req);
        return userInfoList;
    }
}
