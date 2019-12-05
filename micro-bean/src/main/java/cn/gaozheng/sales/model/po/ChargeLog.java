package cn.gaozheng.sales.model.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "charge_log")
public class ChargeLog {
    @Id
    @Column(name = "`log_id`")
    private Integer logId;

    @Column(name = "`way`")
    private Integer way;

    @Column(name = "`chargee_money_before`")
    private Long chargeeMoneyBefore;

    @Column(name = "`charger_money_before`")
    private Long chargerMoneyBefore;

    @Column(name = "`money`")
    private Long money;

    @Column(name = "`given`")
    private Long given;

    @Column(name = "`chargee_money_after`")
    private Long chargeeMoneyAfter;

    @Column(name = "`charger_money_after`")
    private Long chargerMoneyAfter;

    @Column(name = "`time`")
    private Date time;

    @Column(name = "`chargee_level`")
    private Integer chargeeLevel;

    @Column(name = "`chargee_id`")
    private Long chargeeId;

    @Column(name = "`chargee_name`")
    private String chargeeName;

    @Column(name = "`agent_id`")
    private Integer agentId;

    @Column(name = "`charger_level`")
    private Integer chargerLevel;

    @Column(name = "`charger_id`")
    private Integer chargerId;

    @Column(name = "`charger_name`")
    private String chargerName;

    @Column(name = "`operate_type`")
    private String operateType;

    @Column(name = "`remark`")
    private String remark;

    @Column(name = "`order_id`")
    private String orderId;

    @Column(name = "`chargee_cash_before`")
    private Long chargeeCashBefore;

    @Column(name = "`charger_cash_before`")
    private Long chargerCashBefore;

    @Column(name = "`chargee_cash_after`")
    private Long chargeeCashAfter;

    @Column(name = "`cash`")
    private Long cash;

    @Column(name = "`charger_cash_after`")
    private Long chargerCashAfter;

    /**
     * @return log_id
     */
    public Integer getLogId() {
        return logId;
    }

    /**
     * @param logId
     */
    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    /**
     * @return way
     */
    public Integer getWay() {
        return way;
    }

    /**
     * @param way
     */
    public void setWay(Integer way) {
        this.way = way;
    }

    /**
     * @return chargee_money_before
     */
    public Long getChargeeMoneyBefore() {
        return chargeeMoneyBefore;
    }

    /**
     * @param chargeeMoneyBefore
     */
    public void setChargeeMoneyBefore(Long chargeeMoneyBefore) {
        this.chargeeMoneyBefore = chargeeMoneyBefore;
    }

    /**
     * @return charger_money_before
     */
    public Long getChargerMoneyBefore() {
        return chargerMoneyBefore;
    }

    /**
     * @param chargerMoneyBefore
     */
    public void setChargerMoneyBefore(Long chargerMoneyBefore) {
        this.chargerMoneyBefore = chargerMoneyBefore;
    }

    /**
     * @return money
     */
    public Long getMoney() {
        return money;
    }

    /**
     * @param money
     */
    public void setMoney(Long money) {
        this.money = money;
    }

    /**
     * @return given
     */
    public Long getGiven() {
        return given;
    }

    /**
     * @param given
     */
    public void setGiven(Long given) {
        this.given = given;
    }

    /**
     * @return chargee_money_after
     */
    public Long getChargeeMoneyAfter() {
        return chargeeMoneyAfter;
    }

    /**
     * @param chargeeMoneyAfter
     */
    public void setChargeeMoneyAfter(Long chargeeMoneyAfter) {
        this.chargeeMoneyAfter = chargeeMoneyAfter;
    }

    /**
     * @return charger_money_after
     */
    public Long getChargerMoneyAfter() {
        return chargerMoneyAfter;
    }

    /**
     * @param chargerMoneyAfter
     */
    public void setChargerMoneyAfter(Long chargerMoneyAfter) {
        this.chargerMoneyAfter = chargerMoneyAfter;
    }

    /**
     * @return time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * @return chargee_level
     */
    public Integer getChargeeLevel() {
        return chargeeLevel;
    }

    /**
     * @param chargeeLevel
     */
    public void setChargeeLevel(Integer chargeeLevel) {
        this.chargeeLevel = chargeeLevel;
    }

    /**
     * @return chargee_id
     */
    public Long getChargeeId() {
        return chargeeId;
    }

    /**
     * @param chargeeId
     */
    public void setChargeeId(Long chargeeId) {
        this.chargeeId = chargeeId;
    }

    /**
     * @return chargee_name
     */
    public String getChargeeName() {
        return chargeeName;
    }

    /**
     * @param chargeeName
     */
    public void setChargeeName(String chargeeName) {
        this.chargeeName = chargeeName;
    }

    /**
     * @return agent_id
     */
    public Integer getAgentId() {
        return agentId;
    }

    /**
     * @param agentId
     */
    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    /**
     * @return charger_level
     */
    public Integer getChargerLevel() {
        return chargerLevel;
    }

    /**
     * @param chargerLevel
     */
    public void setChargerLevel(Integer chargerLevel) {
        this.chargerLevel = chargerLevel;
    }

    /**
     * @return charger_id
     */
    public Integer getChargerId() {
        return chargerId;
    }

    /**
     * @param chargerId
     */
    public void setChargerId(Integer chargerId) {
        this.chargerId = chargerId;
    }

    /**
     * @return charger_name
     */
    public String getChargerName() {
        return chargerName;
    }

    /**
     * @param chargerName
     */
    public void setChargerName(String chargerName) {
        this.chargerName = chargerName;
    }

    /**
     * @return operate_type
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * @param operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return order_id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * @return chargee_cash_before
     */
    public Long getChargeeCashBefore() {
        return chargeeCashBefore;
    }

    /**
     * @param chargeeCashBefore
     */
    public void setChargeeCashBefore(Long chargeeCashBefore) {
        this.chargeeCashBefore = chargeeCashBefore;
    }

    /**
     * @return charger_cash_before
     */
    public Long getChargerCashBefore() {
        return chargerCashBefore;
    }

    /**
     * @param chargerCashBefore
     */
    public void setChargerCashBefore(Long chargerCashBefore) {
        this.chargerCashBefore = chargerCashBefore;
    }

    /**
     * @return chargee_cash_after
     */
    public Long getChargeeCashAfter() {
        return chargeeCashAfter;
    }

    /**
     * @param chargeeCashAfter
     */
    public void setChargeeCashAfter(Long chargeeCashAfter) {
        this.chargeeCashAfter = chargeeCashAfter;
    }

    /**
     * @return cash
     */
    public Long getCash() {
        return cash;
    }

    /**
     * @param cash
     */
    public void setCash(Long cash) {
        this.cash = cash;
    }

    /**
     * @return charger_cash_after
     */
    public Long getChargerCashAfter() {
        return chargerCashAfter;
    }

    /**
     * @param chargerCashAfter
     */
    public void setChargerCashAfter(Long chargerCashAfter) {
        this.chargerCashAfter = chargerCashAfter;
    }
}