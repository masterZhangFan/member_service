package cn.gaozheng.sales.model.po;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "user_commission")
public class UserCommission {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`agent_id`")
    private Integer agentId;

    @Column(name = "`trader_id`")
    private Integer traderId;

    @Column(name = "`uid`")
    private String uid;

    /**
     * 可结算佣金总额
     */
    @Column(name = "`total`")
    private BigDecimal total;

    /**
     * 已结算佣金总额
     */
    @Column(name = "`settlement`")
    private BigDecimal settlement;

    /**
     * 审核中的金额
     */
    @Column(name = "`auditing`")
    private BigDecimal auditing;

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
     * @return trader_id
     */
    public Integer getTraderId() {
        return traderId;
    }

    /**
     * @param traderId
     */
    public void setTraderId(Integer traderId) {
        this.traderId = traderId;
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
     * 获取可结算佣金总额
     *
     * @return total - 可结算佣金总额
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * 设置可结算佣金总额
     *
     * @param total 可结算佣金总额
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * 获取已结算佣金总额
     *
     * @return settlement - 已结算佣金总额
     */
    public BigDecimal getSettlement() {
        return settlement;
    }

    /**
     * 设置已结算佣金总额
     *
     * @param settlement 已结算佣金总额
     */
    public void setSettlement(BigDecimal settlement) {
        this.settlement = settlement;
    }

    /**
     * 获取审核中的金额
     *
     * @return auditing - 审核中的金额
     */
    public BigDecimal getAuditing() {
        return auditing;
    }

    /**
     * 设置审核中的金额
     *
     * @param auditing 审核中的金额
     */
    public void setAuditing(BigDecimal auditing) {
        this.auditing = auditing;
    }
}