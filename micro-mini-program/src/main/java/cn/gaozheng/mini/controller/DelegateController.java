package cn.gaozheng.mini.controller;

import cn.gaozheng.mini.util.Constants;
import cn.gaozheng.sales.model.po.TblDelegate;
import cn.gaozheng.sales.model.po.TblDelegateType;
import cn.gaozheng.sales.model.vo.DelegateListM;
import cn.gaozheng.sales.model.vo.DelegateListParm;
import cn.gaozheng.sales.model.vo.UserInfo;
import cn.gaozheng.sales.model.vo.base.ServiceStatus;
import cn.gaozheng.sales.service.DelegateService;
import cn.gaozheng.sales.utils.ExceptionUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Api(description = "代理")
@RequestMapping("/delegate")
public class DelegateController {

    @Autowired
    DelegateService delegateService;

    @ApiOperation(value = "代理列表")
    @GetMapping("/delegateList")
    public List<DelegateListM> delegateList(HttpSession session){
        UserInfo client = (UserInfo) session.getAttribute(Constants.SESSION_USER_CACHE_KEY);
        return delegateService.delegateListNotOnPage(client.getUserId());
    }
    @ApiOperation(value = "代理信息")
    @GetMapping("/delegateInfo")
    public DelegateListM delegateInfo(HttpSession session){
        UserInfo client = (UserInfo) session.getAttribute(Constants.SESSION_USER_CACHE_KEY);
        DelegateListM  delegateInfo  = delegateService.delegate(client.getUserId());
        return delegateInfo;
    }
    @ApiOperation(value = "添加代理")
    @PostMapping("/setDelegate")
    public Boolean addDelegate(@RequestBody TblDelegate tblDelegate,HttpSession session){
        Boolean result  = delegateService.setDelegate(tblDelegate);
        UserInfo client = (UserInfo) session.getAttribute(Constants.SESSION_USER_CACHE_KEY);
        tblDelegate.setParentDelegateId(client.getUserId());
        return result;
    }
    @ApiOperation(value = "代理类型")
    @GetMapping("/getDelegateTypes")
    public List<TblDelegateType> getDelegateTypes(){
        return  delegateService.getDelegateTypes();
    }
}
