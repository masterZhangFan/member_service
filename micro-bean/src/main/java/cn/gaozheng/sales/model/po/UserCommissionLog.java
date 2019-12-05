package cn.gaozheng.sales.model.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "user_commission_log")
public class UserCommissionLog {
    @Id
    @Column(name = "`rec_id`")
    private Integer recId;

    /**
     * 用户uid
     */
    @Column(name = "`uid`")
    private String uid;

    /**
     * 账单类型：1入账，2申请体现
     */
    @Column(name = "`type`")
    private Integer type;

    /**
     * 账单描述
     */
    @Column(name = "`describe`")
    private String describe;

    /**
     * 本次账单结算金额
     */
    @Column(name = "`bill`")
    private BigDecimal bill;

    /**
     * 变动前金额
     */
    @Column(name = "`old_commission`")
    private BigDecimal oldCommission;

    /**
     * 出账时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 状态 1：有效（type=1）/审核通过（type=2）；2：待审核（type=2）；3：驳回（type=2）
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * @return rec_id
     */
    public Integer getRecId() {
        return recId;
    }

    /**
     * @param recId
     */
    public void setRecId(Integer recId) {
        this.recId = recId;
    }

    /**
     * 获取用户uid
     *
     * @return uid - 用户uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * 设置用户uid
     *
     * @param uid 用户uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * 获取账单类型：1入账，2申请体现
     *
     * @return type - 账单类型：1入账，2申请体现
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置账单类型：1入账，2申请体现
     *
     * @param type 账单类型：1入账，2申请体现
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取账单描述
     *
     * @return describe - 账单描述
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * 设置账单描述
     *
     * @param describe 账单描述
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    /**
     * 获取本次账单结算金额
     *
     * @return bill - 本次账单结算金额
     */
    public BigDecimal getBill() {
        return bill;
    }

    /**
     * 设置本次账单结算金额
     *
     * @param bill 本次账单结算金额
     */
    public void setBill(BigDecimal bill) {
        this.bill = bill;
    }

    /**
     * 获取变动前金额
     *
     * @return old_commission - 变动前金额
     */
    public BigDecimal getOldCommission() {
        return oldCommission;
    }

    /**
     * 设置变动前金额
     *
     * @param oldCommission 变动前金额
     */
    public void setOldCommission(BigDecimal oldCommission) {
        this.oldCommission = oldCommission;
    }

    /**
     * 获取出账时间
     *
     * @return create_time - 出账时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置出账时间
     *
     * @param createTime 出账时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取状态 1：有效（type=1）/审核通过（type=2）；2：待审核（type=2）；3：驳回（type=2）
     *
     * @return status - 状态 1：有效（type=1）/审核通过（type=2）；2：待审核（type=2）；3：驳回（type=2）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 1：有效（type=1）/审核通过（type=2）；2：待审核（type=2）；3：驳回（type=2）
     *
     * @param status 状态 1：有效（type=1）/审核通过（type=2）；2：待审核（type=2）；3：驳回（type=2）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}