package cn.gaozheng.sales.model.vo;

import lombok.Data;

@Data
public class Fan {
    private Integer userId;
    private String nickname;
    private String typeOfMembership;
    private Double memberCashBack;
    private Double delegateCashBack;
    private String icon;
}
