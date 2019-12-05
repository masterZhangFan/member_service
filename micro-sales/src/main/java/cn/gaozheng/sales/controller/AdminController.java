package cn.gaozheng.sales.controller;

import cn.gaozheng.sales.model.po.TblAdmin;
import cn.gaozheng.sales.model.vo.LoginModel;
import cn.gaozheng.sales.model.vo.base.ServiceStatus;
import cn.gaozheng.sales.service.AdminService;
import cn.gaozheng.sales.utils.ExceptionUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/login")
    public ServiceStatus login( @RequestBody TblAdmin tblAdmin) {
        try {
            return new ServiceStatus(ServiceStatus.Status.Success,adminService.login(tblAdmin));
        }catch (Exception e){
            return new ServiceStatus(ServiceStatus.Status.Fail, ExceptionUtil.getExceptionDesc(e));
        }
    }
}
