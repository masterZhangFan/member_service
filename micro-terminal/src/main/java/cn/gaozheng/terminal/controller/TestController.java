package cn.gaozheng.terminal.controller;

import cn.gaozheng.sales.mapper.UserMapper;
import cn.gaozheng.sales.model.po.RbTree;
import cn.gaozheng.sales.model.vo.DelegateListM;
import cn.gaozheng.sales.model.vo.DelegateListParm;
import cn.gaozheng.sales.model.vo.base.ServiceStatus;
import cn.gaozheng.sales.service.*;
import cn.gaozheng.sales.utils.ExceptionUtil;
import cn.gaozheng.sales.utils.TimeUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@Api(description = "预审核订单")
@RequestMapping("/test")
public class TestController {
//    @Autowired
//    TestService testService;
    @Autowired
    ILoginService loginService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ChargeService chargeService;
    @Autowired
    TokenUtilsServer tokenUtilsServer;
    @Autowired
    DelegateService delegateService;
    @Autowired
    RbTreeService rbTreeService;
    @Autowired
    UserInfoService userInfoService;


    @ApiOperation(value = "获取token")
    @GetMapping("/getTokenStr")
    public String getToken(Long userId){
        String tokenString = tokenUtilsServer.generateTokenString(userId, TimeUtils.formatDateString(new Date()));
        return tokenString;
    }
    @ApiOperation(value = "delegateList")
    @GetMapping("/delegateList")
    public PageInfo<DelegateListM> delegateList( DelegateListParm parm){
        return delegateService.delegateList(parm);
    }

    @ApiOperation(value = "genRbTree")
    @GetMapping("/genRbTree")
    public void getToken(String uids,Integer puid){
        rbTreeService.genRbTree(uids,puid);
    }

    @ApiOperation(value = "随机生成用户")
    @GetMapping("/genUser")
    public void genUser(Integer count,String start,String end){
        userInfoService.genUser(count,start,end);
    }

}

