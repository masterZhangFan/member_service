package cn.gaozheng.sales.controller;


import cn.gaozheng.sales.model.po.TblMemberSetting;
import cn.gaozheng.sales.model.vo.Fan;
import cn.gaozheng.sales.model.vo.base.ServiceStatus;
import cn.gaozheng.sales.service.SettingService;
import cn.gaozheng.sales.utils.ExceptionUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(description = "系统设置")
@RequestMapping("/setting")
public class SettingController {
    @Autowired
    SettingService settingService;

    @ApiOperation(value = "基础设置")
    @PostMapping("/sysConfig")
    public ServiceStatus<Boolean> sysConfig(@RequestBody TblMemberSetting tblMemberSetting ){
        try {
            Boolean result  = settingService.sysConfig(tblMemberSetting);
            return new ServiceStatus(ServiceStatus.Status.Success,result);
        }catch (Exception e){
            return new ServiceStatus(ServiceStatus.Status.Fail, ExceptionUtil.getExceptionDesc(e));
        }
    }

    @ApiOperation(value = "获取基础设置")
    @GetMapping("/getSysConfig")
    public ServiceStatus<TblMemberSetting> getSysConfig(){
        try {
            TblMemberSetting result  = settingService.getSysConfig();
            return new ServiceStatus(ServiceStatus.Status.Success,result);
        }catch (Exception e){
            return new ServiceStatus(ServiceStatus.Status.Fail, ExceptionUtil.getExceptionDesc(e));
        }
    }
}
