package cn.gaozheng.terminal.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
