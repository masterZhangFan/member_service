package cn.gaozheng.terminal.controller;

import cn.gaozheng.sales.mapper.UserMapper;
import cn.gaozheng.sales.model.vo.base.ServiceStatus;
import cn.gaozheng.sales.service.ChargeService;
import cn.gaozheng.sales.service.ILoginService;
import cn.gaozheng.sales.utils.ExceptionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @ApiOperation(value = "测试")
    @GetMapping("/test")
    public Map test(String code,Integer payFor,Integer chargeId,Long userId){
        return chargeService.getOrders(code,payFor,chargeId,userId);
    }


//    @ApiOperation(value = "用户")
//    @GetMapping("/users")
//    public ServiceStatus<List<User>> users(){
//        try {
//            List<User> userList = userMapper.selectAll();
//            return new ServiceStatus(ServiceStatus.Status.Success,userList);
//        }catch (Exception e){
//            return new ServiceStatus(ServiceStatus.Status.Fail, ExceptionUtil.getExceptionDesc(e));
//        }
//    }
}

