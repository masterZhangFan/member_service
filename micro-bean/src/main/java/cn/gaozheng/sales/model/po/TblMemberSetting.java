package cn.gaozheng.sales.model.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tbl_member_setting")
public class TblMemberSetting {

    /*会员价格*/
    @Id
    @Column(name = "`setting_id`")
    private Integer settingId;
    /*会员价格*/
    @Column(name = "`member_price`")
    private Double memberPrice;

    /*一级级返现*/
    @Column(name = "`once_level_cashback`")
    private Double onceLevelCashback;

    /*二级返现*/
    @Column(name = "`second_level_cashback`")
    private Double secondLevelCashback;

    /*话费返现*/
    @Column(name = "`call_amount_back`")
    private Float callAmountBack;

    /*话费返现*/
    @Column(name = "`shopping_amount_back`")
    private Double shoppingAmountBack;

    /*高级会员权益及规则*/
    @Column(name = "`senior_member_rules`")
    private String seniorMemberRules;

    /*会员益及规则*/
    @Column(name = "`member_rules`")
    private String memberRules;

    /*代理权益及规则*/
    @Column(name = "`delegate_rules`")
    private String delegateRules;

    /*税率*/
    @Column(name = "`tax_rate`")
    private Integer taxRate;

}