package cn.gaozheng.terminal.controller;

import cn.gaozheng.sales.model.po.TblShareTemp;
import cn.gaozheng.sales.model.vo.Fan;
import cn.gaozheng.sales.model.vo.ShareInstance;
import cn.gaozheng.sales.model.vo.base.ServiceStatus;
import cn.gaozheng.sales.service.ShareTempService;
import cn.gaozheng.sales.service.TokenUtilsServer;
import cn.gaozheng.sales.utils.ExceptionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Api(description = "分享模板")
@RequestMapping("/shareTemp")
public class ShareTempController {

    @Autowired
    ShareTempService shareTempService;
    @Autowired
    TokenUtilsServer tokenUtilsServer;

    @ApiOperation(value = "分享模板")
    @GetMapping("/shareTemps")
    public List<TblShareTemp> shareTemps(Integer shareTempType){
        List<TblShareTemp> tblShareTempList = shareTempService.shareTemps(shareTempType);
        return tblShareTempList;
    }
    @ApiOperation(value = "分享信息")
    @GetMapping("/shareInfo")
    public ShareInstance shareInfo(HttpServletRequest request){
        ShareInstance shareInstance = shareTempService.shareInfo(tokenUtilsServer.uid(request));
        return shareInstance;
    }
    @ApiOperation(value = "分享信息")
    @GetMapping("/shareInstance")
    public ShareInstance shareInstance(Long userId,Long shareTempId){
        ShareInstance shareInstance = shareTempService.shareInstance(userId,shareTempId);
        return shareInstance;
    }
}
