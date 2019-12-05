package cn.gaozheng.terminal.controller;

import cn.gaozheng.sales.service.MemberService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "会员")
@RequestMapping("/member")
public class MemberController {
    @Autowired
    MemberService memberService;

//    @ApiOperation(value = "会员列表")
//    @GetMapping("/memberInfos")
//    public ServiceStatus<PageInfo<UserInfo>> memberInfos(MemberListParm req){
//        try {
//            PageInfo<UserInfo> delegateListMPageInfo  = memberService.memberInfos(req);
//            return new ServiceStatus(ServiceStatus.Status.Success,delegateListMPageInfo);
//        }catch (Exception e){
//            return new ServiceStatus(ServiceStatus.Status.Fail, ExceptionUtil.getExceptionDesc(e));
//        }
//    }
//    @ApiOperation(value = "会员列表(不是代理)")
//    @GetMapping("/memberInfoNotIncludeDelegate")
//    public ServiceStatus<UserInfo> memberInfoNotIncludeDelegate(MemberListParm req){
//        try {
//            List<UserInfo> userInfoList= memberService.memberInfoNotIncludeDelegate(req);
//            return new ServiceStatus(ServiceStatus.Status.Success,userInfoList);
//        }catch (Exception e){
//            return new ServiceStatus(ServiceStatus.Status.Fail, ExceptionUtil.getExceptionDesc(e));
//        }
//    }
//    @ApiOperation(value = "会员信息")
//    @GetMapping("/memberInfo")
//    public ServiceStatus<UserInfo> memberInfo( Integer userId){
//        try {
//            UserInfo userInfo  = memberService.memberInfo(userId);
//            return new ServiceStatus(ServiceStatus.Status.Success,userInfo);
//        }catch (Exception e){
//            return new ServiceStatus(ServiceStatus.Status.Fail, ExceptionUtil.getExceptionDesc(e));
//        }
//    }
}
