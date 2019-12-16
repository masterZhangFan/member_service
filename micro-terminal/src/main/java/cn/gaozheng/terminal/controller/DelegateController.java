package cn.gaozheng.terminal.controller;

import cn.gaozheng.mini.util.Constants;
import cn.gaozheng.sales.model.po.TblDelegate;
import cn.gaozheng.sales.model.po.TblDelegateType;
import cn.gaozheng.sales.model.vo.DelegateListM;
import cn.gaozheng.sales.model.vo.UserInfo;
import cn.gaozheng.sales.service.DelegateService;
import cn.gaozheng.sales.service.TokenUtilsServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Api(description = "代理")
@RequestMapping("/delegate")
public class DelegateController {

    @Autowired
    DelegateService delegateService;
    @Autowired
    TokenUtilsServer tokenUtilsServer;

    @ApiOperation(value = "代理列表")
    @GetMapping("/delegateList")
    public List<DelegateListM> delegateList( HttpServletRequest request){
        return delegateService.delegateListNotOnPage(tokenUtilsServer.uid(request));
    }
    @ApiOperation(value = "代理信息")
    @GetMapping("/delegateInfo")
    public DelegateListM delegateInfo(HttpServletRequest request){
        DelegateListM  delegateInfo  = delegateService.delegate(tokenUtilsServer.uid(request));
        return delegateInfo;
    }
    @ApiOperation(value = "添加代理")
    @PostMapping("/setDelegate")
    public Boolean setDelegate(@RequestBody TblDelegate tblDelegate,HttpServletRequest request){
        Boolean result  = delegateService.setDelegate(tblDelegate);
        tblDelegate.setParentDelegateId(tokenUtilsServer.uid(request));
        return result;
    }
    @ApiOperation(value = "代理类型")
    @GetMapping("/getDelegateTypes")
    public List<TblDelegateType> getDelegateTypes(HttpServletRequest request){
        return  delegateService.getDelegateTypesByUserId(tokenUtilsServer.uid(request));
    }
}
