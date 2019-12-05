package cn.gaozheng.terminal.controller;

import cn.gaozheng.mini.util.Constants;
import cn.gaozheng.sales.model.po.TblCharge;
import cn.gaozheng.sales.model.po.TblChargeConfig;
import cn.gaozheng.sales.model.po.UserCommissionSet;
import cn.gaozheng.sales.model.vo.UserInfo;
import cn.gaozheng.sales.model.vo.base.ServiceStatus;
import cn.gaozheng.sales.model.vo.charge.UserCommissionApplayParm;
import cn.gaozheng.sales.model.vo.charge.UserCommssionSetParm;
import cn.gaozheng.sales.service.ChargeService;
import cn.gaozheng.sales.utils.ExceptionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@Api(description = "充值")
@RequestMapping("/charge")
public class ChargeController {
    @Autowired
    ChargeService chargeService;

    @ApiOperation(value = "充值列表")
    @GetMapping("/chargeList")
    public List<TblCharge>  chargeList(){
        return chargeService.chargeList();
    }
    @ApiOperation(value = "支付配置")
    @GetMapping("/payConfig")
    public TblChargeConfig  payConfig(){
        return chargeService.getChargeConfig();
    }
    @ApiOperation(value = "提现配置获取")
    @GetMapping("/getUserCommissionSet")
    public UserCommissionSet  getUserCommissionSet(HttpSession session){
        UserInfo client = (UserInfo) session.getAttribute(Constants.SESSION_USER_CACHE_KEY);
        return chargeService.getUserCommissionSet(client.getUserId());
    }
    @ApiOperation(value = "提现配置设置")
    @PostMapping("/setUserCommissionSet")
    public Boolean  setUserCommissionSet(@RequestBody UserCommssionSetParm userCommssionSetParm,HttpSession session){
        UserInfo client = (UserInfo) session.getAttribute(Constants.SESSION_USER_CACHE_KEY);
        return chargeService.setUserCommissionSet(userCommssionSetParm,client.getUserId());
    }
    @ApiOperation(value = "提现申请")
    @PostMapping("/userCommissionApplay")
    public Boolean  userCommissionApplay( @RequestBody UserCommissionApplayParm userCommissionApplayParm, HttpSession session){
        UserInfo client = (UserInfo) session.getAttribute(Constants.SESSION_USER_CACHE_KEY);
        return chargeService.userCommissionApplay(userCommissionApplayParm,client.getUserId());
    }
    @ApiOperation(value = "下单预支付")
    @GetMapping("/orders")
    public ServiceStatus orders(HttpSession session,HttpServletRequest httpServletRequest,String code, Integer payFor, Integer chargeId, Integer userId){
        try {
            Map orderInfo = chargeService.orders(httpServletRequest,code,payFor,chargeId,userId);
            return new ServiceStatus(ServiceStatus.Status.Success,orderInfo);
        }catch (Exception e){
            return new ServiceStatus(ServiceStatus.Status.Fail, ExceptionUtil.getExceptionDesc(e));
        }
    }
    @ApiOperation(value = "下单成功")
    @GetMapping("/chargeSuccess")
    public ServiceStatus chargeSuccess(String payOrder){
        try {
            return new ServiceStatus(ServiceStatus.Status.Success, chargeService.chargeSuccess(payOrder));
        }catch (Exception e){
            return new ServiceStatus(ServiceStatus.Status.Fail, ExceptionUtil.getExceptionDesc(e));
        }
    }
}
