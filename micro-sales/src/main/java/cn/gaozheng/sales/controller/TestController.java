package cn.gaozheng.sales.controller;

import cn.gaozheng.sales.mapper.UserMapper;
import cn.gaozheng.sales.model.po.User;
import cn.gaozheng.sales.model.vo.base.ServiceStatus;
import cn.gaozheng.sales.service.ILoginService;
import cn.gaozheng.sales.utils.ExceptionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
//    @ApiOperation(value = "测试")
//    @GetMapping("/test")
//    public ServiceStatus test(){
//        try {
//            return new ServiceStatus(ServiceStatus.Status.Success,testService.getTables());
//        }catch (Exception e){
//            return new ServiceStatus(ServiceStatus.Status.Fail, ExceptionUtil.getExceptionDesc(e));
//        }
//    }


    @ApiOperation(value = "用户")
    @GetMapping("/users")
    public ServiceStatus<List<User>> users(){
        try {
            List<User> userList = userMapper.selectAll();
            return new ServiceStatus(ServiceStatus.Status.Success,userList);
        }catch (Exception e){
            return new ServiceStatus(ServiceStatus.Status.Fail, ExceptionUtil.getExceptionDesc(e));
        }
    }
}

