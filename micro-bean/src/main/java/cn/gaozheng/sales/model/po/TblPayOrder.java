package cn.gaozheng.sales.model.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tbl_pay_order")
public class TblPayOrder {
    /**
     * 支付单号
     */
    @Column(name = "`pay_order_id`")
    private Long payOrderId;

    /**
     * 支付单号
     */
    @Id
    @Column(name = "`pay_order`")
    private String payOrder;

    /**
     * 支付类型:1.微信公众号
     */
    @Column(name = "`pay_type`")
    private Integer payType;

    /**
     * 支付金额
     */
    @Column(name = "`pay_money`")
    private Double payMoney;

    /**
     * 支付去向 1 会员 2.充值
     */
    @Column(name = "`pay_for`")
    private Integer payFor;

    /**
     * 是否支付成功
     */
    @Column(name = "`is_pay_success`")
    private Boolean isPaySuccess;

    /**
     * 成功失败信息
     */
    @Column(name = "`failure_msg`")
    private String failureMsg;


    @Column(name = "`shopping_amount`")
    private Double shoppingAmount;

    @Column(name = "`call_amount`")
    private Float callAmount;

    /**
     * 创建订单时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 支付时间
     */
    @Column(name = "`pay_time`")
    private Date payTime;

    /**
     * 用户ID
     */
    @Column(name = "`user_id`")
    private Long userId;

    /**
     * 付款人
     */
    @Column(name = "`payer`")
    private String payer;

    /**
     * 收款人
     */
    @Column(name = "`payee`")
    private String payee;
}