package cn.gaozheng.sales.model.vo;

import lombok.Data;

@Data
public class Banlance {
    //购物余额(元)
    private Double shoppingBalance;
    //话费
    private Double callBalance;
    //可提现金额
    private Double cash;
}
