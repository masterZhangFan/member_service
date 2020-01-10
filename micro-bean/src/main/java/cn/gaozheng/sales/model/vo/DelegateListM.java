package cn.gaozheng.sales.model.vo;

import cn.gaozheng.sales.model.po.TblDelegate;
import lombok.Data;

@Data
public class DelegateListM extends TblDelegate {
    //代理类型名称
    private String delegateTypeName;
    //代理类型水平
    private Integer delegateTypeLevel;
    //购物余额(元)
    private Double shoppingBalance;
    //话费
    private Double callBalance;
    //可提现金额
    private Double cash;
    //电话
    private String phone;
    //昵称
    private String nickname;
    /*icon*/
    private String icon;
    /*返现金额*/
    private Double moneyTotal;
    /*总的粉丝数*/
    private Integer fans;
}
