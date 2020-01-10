package cn.gaozheng.sales.model.po;

import cn.hutool.core.date.DateTime;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tbl_cash_back")
public class TblCashBack {
    @Id
    @Column(name = "`cash_back_id`")
    private Long cashBackId;

    @Column(name = "`cash_back_money`")
    private Double cashBackMoney;

    @Column(name = "`cash_back_from_user_id`")
    private Long cashBackFromUserId;

    @Column(name = "`cash_back_to_user_id`")
    private Long cashBackToUserId;

    /**
     * 1.一级会员返现 2.二级会员返现 3.代理返现
     */
    @Column(name = "`cash_back_type`")
    private Integer cashBackType;

    /**
     * 时间
     */
    @Column(name = "`cash_back_time`")
    private Date cashBackTime;


    /**
     * 时间
     */
    @Column(name = "`order_id`")
    private Long orderId;



    /**
     * @return cash_back_id
     */
    public Long getCashBackId() {
        return cashBackId;
    }

    /**
     * @param cashBackId
     */
    public void setCashBackId(Long cashBackId) {
        this.cashBackId = cashBackId;
    }

    /**
     * @return cash_back_money
     */
    public Double getCashBackMoney() {
        return cashBackMoney;
    }

    /**
     * @param cashBackMoney
     */
    public void setCashBackMoney(Double cashBackMoney) {
        this.cashBackMoney = cashBackMoney;
    }

    /**
     * @return cash_back_from_user_id
     */
    public Long getCashBackFromUserId() {
        return cashBackFromUserId;
    }

    /**
     * @param cashBackFromUserId
     */
    public void setCashBackFromUserId(Long cashBackFromUserId) {
        this.cashBackFromUserId = cashBackFromUserId;
    }

    /**
     * @return cash_back_to_user_id
     */
    public Long getCashBackToUserId() {
        return cashBackToUserId;
    }

    /**
     * @param cashBackToUserId
     */
    public void setCashBackToUserId(Long cashBackToUserId) {
        this.cashBackToUserId = cashBackToUserId;
    }

    /**
     * 获取1.一级会员返现 2.二级会员返现 3.代理返现
     *
     * @return cash_back_type - 1.一级会员返现 2.二级会员返现 3.代理返现
     */
    public Integer getCashBackType() {
        return cashBackType;
    }

    /**
     * 设置1.一级会员返现 2.二级会员返现 3.代理返现
     *
     * @param cashBackType 1.一级会员返现 2.二级会员返现 3.代理返现
     */
    public void setCashBackType(Integer cashBackType) {
        this.cashBackType = cashBackType;
    }

    public Date getCashBackTime() {
        return cashBackTime;
    }

    public void setCashBackTime( Date cashBackTime ) {
        this.cashBackTime = cashBackTime;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId( Long orderId ) {
        this.orderId = orderId;
    }
}