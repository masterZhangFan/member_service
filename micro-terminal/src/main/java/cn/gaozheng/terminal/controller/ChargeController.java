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
import cn.gaozheng.sales.service.TokenUtilsServer;
import cn.gaozheng.sales.utils.ExceptionUtil;
import cn.gaozheng.sales.wechart.WXPayUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@Api(description = "充值")
@RequestMapping("/charge")
public class ChargeController {
    @Autowired
    ChargeService chargeService;
    @Autowired
    TokenUtilsServer tokenUtilsServer;

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
    public UserCommissionSet  getUserCommissionSet(HttpServletRequest request){
        return chargeService.getUserCommissionSet(tokenUtilsServer.uid(request));
    }
    @ApiOperation(value = "提现配置设置")
    @PostMapping("/setUserCommissionSet")
    public Boolean  setUserCommissionSet(HttpServletRequest request,@RequestBody UserCommssionSetParm userCommssionSetParm){
        return chargeService.setUserCommissionSet(userCommssionSetParm,tokenUtilsServer.uid(request));
    }
    @ApiOperation(value = "提现申请")
    @PostMapping("/userCommissionApplay")
    public Boolean  userCommissionApplay(HttpServletRequest request,@RequestBody UserCommissionApplayParm userCommissionApplayParm){

        return chargeService.userCommissionApplay(userCommissionApplayParm,tokenUtilsServer.uid(request));
    }
    @ApiOperation(value = "下单预支付")
    @GetMapping("/orders")
    public ServiceStatus orders(HttpServletRequest request,String code, Integer payFor, Integer chargeId){
        try {
            Map orderInfo = chargeService.orders(request,code,payFor,chargeId,tokenUtilsServer.uid(request));
            return new ServiceStatus(ServiceStatus.Status.Success,orderInfo);
        }catch (Exception e){
            return new ServiceStatus(ServiceStatus.Status.Fail, ExceptionUtil.getExceptionDesc(e));
        }
    }
    @ApiOperation(value = "支付回调")
    @PostMapping("/notify")
    public String notify(HttpServletRequest request, HttpServletResponse response){
        // System.out.println("微信支付成功,微信发送的callback信息,请注意修改订单信息");
        InputStream is = null;
        try {
            is = request.getInputStream();// 获取请求的流信息(这里是微信发的xml格式所有只能使用流来读)
            String xml = WXPayUtil.InputStream2String(is);
            Map<String, String> notifyMap = WXPayUtil.xmlToMap(xml);// 将微信发的xml转map
            System.out.println("微信返回给回调函数的信息为："+xml);
            if (notifyMap.get("result_code").equals("SUCCESS")) {
                String ordersSn = notifyMap.get("out_trade_no");// 商户订单号
                String amountpaid = notifyMap.get("total_fee");// 实际支付的订单金额:单位 分
                BigDecimal amountPay = (new BigDecimal(amountpaid).divide(new BigDecimal("100"))).setScale(2);// 将分转换成元-实际支付金额:元
                /*
                 * 以下是自己的业务处理------仅做参考 更新order对应字段/已支付金额/状态码
                 */
                chargeService.chargeSuccess(ordersSn);
                System.out.println("===notify===回调方法已经被调！！！");

            }

            // 告诉微信服务器收到信息了，不要在调用回调action了========这里很重要回复微信服务器信息用流发送一个xml即可
            response.getWriter().write("<xml><return_code><![CDATA[SUCCESS]]></return_code></xml>");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
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
