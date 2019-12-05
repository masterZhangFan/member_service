package cn.gaozheng.terminal.controller;

import cn.gaozheng.sales.mapper.UserMapper;
import cn.gaozheng.sales.service.ILoginService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

