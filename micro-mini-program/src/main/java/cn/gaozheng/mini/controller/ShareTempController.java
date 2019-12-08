package cn.gaozheng.mini.controller;

import cn.gaozheng.sales.model.po.TblShareTemp;
import cn.gaozheng.sales.model.vo.Fan;
import cn.gaozheng.sales.model.vo.base.ServiceStatus;
import cn.gaozheng.sales.service.ShareTempService;
import cn.gaozheng.sales.utils.ExceptionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(description = "分享模板")
@RequestMapping("/shareTemp")
public class ShareTempController {

//    @Autowired
//    ShareTempService shareTempService;
//
//    @ApiOperation(value = "分享模板")
//    @GetMapping("/shareTemps")
//    public ServiceStatus<List<Fan>> shareTemps(Integer shareTempType){
//        try {
//            List<TblShareTemp> tblShareTempList = shareTempService.shareTemps(shareTempType);
//            return new ServiceStatus(ServiceStatus.Status.Success,tblShareTempList);
//        }catch (Exception e){
//            return new ServiceStatus(ServiceStatus.Status.Fail, ExceptionUtil.getExceptionDesc(e));
//        }
//    }

}
