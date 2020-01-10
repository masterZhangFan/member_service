package cn.gaozheng.terminal.controller;

import cn.gaozheng.sales.mapper.UserMapper;
import cn.gaozheng.sales.model.vo.DelegateListM;
import cn.gaozheng.sales.model.vo.DelegateListParm;
import cn.gaozheng.sales.model.vo.base.ServiceStatus;
import cn.gaozheng.sales.service.ChargeService;
import cn.gaozheng.sales.service.DelegateService;
import cn.gaozheng.sales.service.ILoginService;
import cn.gaozheng.sales.service.TokenUtilsServer;
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

}

