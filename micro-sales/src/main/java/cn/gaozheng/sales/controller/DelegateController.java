package cn.gaozheng.sales.controller;

import cn.gaozheng.sales.model.po.TblDelegate;
import cn.gaozheng.sales.model.po.TblDelegateType;
import cn.gaozheng.sales.model.vo.DelegateListM;
import cn.gaozheng.sales.model.vo.DelegateListParm;
import cn.gaozheng.sales.model.vo.Fan;
import cn.gaozheng.sales.model.vo.LoginModel;
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
    public ServiceStatus<PageInfo<DelegateListM>> delegateList( DelegateListParm delegateListParm){
        try {
            PageInfo<DelegateListM> delegateListMPageInfo  = delegateService.delegateList(delegateListParm);
            return new ServiceStatus(ServiceStatus.Status.Success,delegateListMPageInfo);
        }catch (Exception e){
            return new ServiceStatus(ServiceStatus.Status.Fail, ExceptionUtil.getExceptionDesc(e));
        }
    }
    @ApiOperation(value = "代理信息")
    @GetMapping("/delegateInfo")
    public ServiceStatus<DelegateListM> delegateList(Long userId){
        try {
            DelegateListM  delegateInfo  = delegateService.delegate(userId);
            return new ServiceStatus(ServiceStatus.Status.Success,delegateInfo);
        }catch (Exception e){
            return new ServiceStatus(ServiceStatus.Status.Fail, ExceptionUtil.getExceptionDesc(e));
        }
    }
    @ApiOperation(value = "添加代理")
    @PostMapping("/setDelegate")
    public ServiceStatus<Boolean> addDelegate(TblDelegate tblDelegate){
        try {
            Boolean result  = delegateService.setDelegate(tblDelegate);
            return new ServiceStatus(ServiceStatus.Status.Success,result);
        }catch (Exception e){
            return new ServiceStatus(ServiceStatus.Status.Fail, ExceptionUtil.getExceptionDesc(e));
        }
    }
    @ApiOperation(value = "代理详情")
    @PostMapping("/delegate")
    public ServiceStatus<Boolean> delegate(@RequestBody TblDelegate tblDelegate){
        try {
            Boolean result  = delegateService.setDelegate(tblDelegate);
            return new ServiceStatus(ServiceStatus.Status.Success,result);
        }catch (Exception e){
            return new ServiceStatus(ServiceStatus.Status.Fail, ExceptionUtil.getExceptionDesc(e));
        }
    }
    @ApiOperation(value = "代理类型")
    @GetMapping("/getDelegateTypes")
    public ServiceStatus<List<TblDelegateType> > getDelegateTypes(){
        try {
            List<TblDelegateType> tblDelegateTypes  = delegateService.getDelegateTypes();
            return new ServiceStatus(ServiceStatus.Status.Success,tblDelegateTypes);
        }catch (Exception e){
            return new ServiceStatus(ServiceStatus.Status.Fail, ExceptionUtil.getExceptionDesc(e));
        }
    }
}
