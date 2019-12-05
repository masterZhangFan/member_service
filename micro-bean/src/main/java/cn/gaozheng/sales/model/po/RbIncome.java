package cn.gaozheng.sales.model.po;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "rb_income")
public class RbIncome {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`uid`")
    private String uid;

    @Column(name = "`rank`")
    private Integer rank;

    /**
     * 付款笔数
     */
    @Column(name = "`payCount`")
    private Integer paycount;

    /**
     * 当天的佣金
     */
    @Column(name = "`commission`")
    private BigDecimal commission;

    /**
     * 昨天付款笔数
     */
    @Column(name = "`yesterday_payCount`")
    private Integer yesterdayPaycount;

    /**
     * 昨天的佣金
     */
    @Column(name = "`yesterday_commission`")
    private BigDecimal yesterdayCommission;

    /**
     * 当月的佣金
     */
    @Column(name = "`thismonth_balance`")
    private BigDecimal thismonthBalance;

    @Column(name = "`thismonth_jiesuan_balance`")
    private BigDecimal thismonthJiesuanBalance;

    /**
     * 上个月的佣金
     */
    @Column(name = "`lastmonth_balance`")
    private BigDecimal lastmonthBalance;

    @Column(name = "`lastmonth_jiesuan_balance`")
    private BigDecimal lastmonthJiesuanBalance;

    /**
     * 记录每晚12点脚本统计一次的时间
     */
    @Column(name = "`update_date1`")
    private Integer updateDate1;

    /**
     * 记录每月1号脚本统计一次的时间
     */
    @Column(name = "`update_date2`")
    private Integer updateDate2;

    /**
     * 记录每天统计本月与上个月的结算订单的时间
     */
    @Column(name = "`update_date3`")
    private Integer updateDate3;

    @Column(name = "`status`")
    private Integer status;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * @return rank
     */
    public Integer getRank() {
        return rank;
    }

    /**
     * @param rank
     */
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    /**
     * 获取付款笔数
     *
     * @return payCount - 付款笔数
     */
    public Integer getPaycount() {
        return paycount;
    }

    /**
     * 设置付款笔数
     *
     * @param paycount 付款笔数
     */
    public void setPaycount(Integer paycount) {
        this.paycount = paycount;
    }

    /**
     * 获取当天的佣金
     *
     * @return commission - 当天的佣金
     */
    public BigDecimal getCommission() {
        return commission;
    }

    /**
     * 设置当天的佣金
     *
     * @param commission 当天的佣金
     */
    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    /**
     * 获取昨天付款笔数
     *
     * @return yesterday_payCount - 昨天付款笔数
     */
    public Integer getYesterdayPaycount() {
        return yesterdayPaycount;
    }

    /**
     * 设置昨天付款笔数
     *
     * @param yesterdayPaycount 昨天付款笔数
     */
    public void setYesterdayPaycount(Integer yesterdayPaycount) {
        this.yesterdayPaycount = yesterdayPaycount;
    }

    /**
     * 获取昨天的佣金
     *
     * @return yesterday_commission - 昨天的佣金
     */
    public BigDecimal getYesterdayCommission() {
        return yesterdayCommission;
    }

    /**
     * 设置昨天的佣金
     *
     * @param yesterdayCommission 昨天的佣金
     */
    public void setYesterdayCommission(BigDecimal yesterdayCommission) {
        this.yesterdayCommission = yesterdayCommission;
    }

    /**
     * 获取当月的佣金
     *
     * @return thismonth_balance - 当月的佣金
     */
    public BigDecimal getThismonthBalance() {
        return thismonthBalance;
    }

    /**
     * 设置当月的佣金
     *
     * @param thismonthBalance 当月的佣金
     */
    public void setThismonthBalance(BigDecimal thismonthBalance) {
        this.thismonthBalance = thismonthBalance;
    }

    /**
     * @return thismonth_jiesuan_balance
     */
    public BigDecimal getThismonthJiesuanBalance() {
        return thismonthJiesuanBalance;
    }

    /**
     * @param thismonthJiesuanBalance
     */
    public void setThismonthJiesuanBalance(BigDecimal thismonthJiesuanBalance) {
        this.thismonthJiesuanBalance = thismonthJiesuanBalance;
    }

    /**
     * 获取上个月的佣金
     *
     * @return lastmonth_balance - 上个月的佣金
     */
    public BigDecimal getLastmonthBalance() {
        return lastmonthBalance;
    }

    /**
     * 设置上个月的佣金
     *
     * @param lastmonthBalance 上个月的佣金
     */
    public void setLastmonthBalance(BigDecimal lastmonthBalance) {
        this.lastmonthBalance = lastmonthBalance;
    }

    /**
     * @return lastmonth_jiesuan_balance
     */
    public BigDecimal getLastmonthJiesuanBalance() {
        return lastmonthJiesuanBalance;
    }

    /**
     * @param lastmonthJiesuanBalance
     */
    public void setLastmonthJiesuanBalance(BigDecimal lastmonthJiesuanBalance) {
        this.lastmonthJiesuanBalance = lastmonthJiesuanBalance;
    }

    /**
     * 获取记录每晚12点脚本统计一次的时间
     *
     * @return update_date1 - 记录每晚12点脚本统计一次的时间
     */
    public Integer getUpdateDate1() {
        return updateDate1;
    }

    /**
     * 设置记录每晚12点脚本统计一次的时间
     *
     * @param updateDate1 记录每晚12点脚本统计一次的时间
     */
    public void setUpdateDate1(Integer updateDate1) {
        this.updateDate1 = updateDate1;
    }

    /**
     * 获取记录每月1号脚本统计一次的时间
     *
     * @return update_date2 - 记录每月1号脚本统计一次的时间
     */
    public Integer getUpdateDate2() {
        return updateDate2;
    }

    /**
     * 设置记录每月1号脚本统计一次的时间
     *
     * @param updateDate2 记录每月1号脚本统计一次的时间
     */
    public void setUpdateDate2(Integer updateDate2) {
        this.updateDate2 = updateDate2;
    }

    /**
     * 获取记录每天统计本月与上个月的结算订单的时间
     *
     * @return update_date3 - 记录每天统计本月与上个月的结算订单的时间
     */
    public Integer getUpdateDate3() {
        return updateDate3;
    }

    /**
     * 设置记录每天统计本月与上个月的结算订单的时间
     *
     * @param updateDate3 记录每天统计本月与上个月的结算订单的时间
     */
    public void setUpdateDate3(Integer updateDate3) {
        this.updateDate3 = updateDate3;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}