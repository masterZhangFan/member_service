package cn.gaozheng.sales.service;

import cn.gaozheng.sales.model.po.TblCharge;
import cn.gaozheng.sales.model.po.TblChargeConfig;
import cn.gaozheng.sales.model.po.UserCommissionSet;
import cn.gaozheng.sales.model.vo.charge.UserCommissionApplayParm;
import cn.gaozheng.sales.model.vo.charge.UserCommssionSetParm;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ChargeService {
    List<TblCharge> chargeList();
    Map orders( HttpServletRequest request, String code,Integer payFor,Integer chargeId,Integer userId);

    Boolean chargeSuccess(String payOrder);

    TblChargeConfig getChargeConfig();

    UserCommissionSet getUserCommissionSet(Long userId);

    Boolean setUserCommissionSet( UserCommssionSetParm userCommissionSet, Long userId);

    Boolean userCommissionApplay( UserCommissionApplayParm userCommissionApplayParm,Long userId);

}
