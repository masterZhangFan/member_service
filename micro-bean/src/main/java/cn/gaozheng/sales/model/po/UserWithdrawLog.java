package cn.gaozheng.sales.model.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "user_withdraw_log")
public class UserWithdrawLog {
    @Id
    @Column(name = "`rec_id`")
    private Integer recId;

    @Column(name = "`log_id`")
    private Integer logId;

    /**
     * 账单记录ID
     */
    @Column(name = "`uid`")
    private String uid;

    /**
     * 提现金额
     */
    @Column(name = "`bill`")
    private BigDecimal bill;

    /**
     * 账号类型（alipay/wepay/unionpay）
     */
    @Column(name = "`to_type`")
    private String toType;

    /**
     * 账号/卡号
     */
    @Column(name = "`to_account`")
    private String toAccount;

    /**
     * 真实姓名/户名
     */
    @Column(name = "`to_realname`")
    private String toRealname;

    /**
     * 银联卡开户行
     */
    @Column(name = "`to_bank`")
    private String toBank;

    /**
     * 发起者备注
     */
    @Column(name = "`submit_remark`")
    private String submitRemark;

    /**
     * 审核者备注
     */
    @Column(name = "`audit_remark`")
    private String auditRemark;

    /**
     * 提交时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 审核时间
     */
    @Column(name = "`audit_time`")
    private Date auditTime;

    /**
     * 审核状态：1审核通过，2待审，3驳回
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
     * 获取账单记录ID
     *
     * @return uid - 账单记录ID
     */
    public String getUid() {
        return uid;
    }

    /**
     * 设置账单记录ID
     *
     * @param uid 账单记录ID
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * 获取提现金额
     *
     * @return bill - 提现金额
     */
    public BigDecimal getBill() {
        return bill;
    }

    /**
     * 设置提现金额
     *
     * @param bill 提现金额
     */
    public void setBill(BigDecimal bill) {
        this.bill = bill;
    }

    /**
     * 获取账号类型（alipay/wepay/unionpay）
     *
     * @return to_type - 账号类型（alipay/wepay/unionpay）
     */
    public String getToType() {
        return toType;
    }

    /**
     * 设置账号类型（alipay/wepay/unionpay）
     *
     * @param toType 账号类型（alipay/wepay/unionpay）
     */
    public void setToType(String toType) {
        this.toType = toType;
    }

    /**
     * 获取账号/卡号
     *
     * @return to_account - 账号/卡号
     */
    public String getToAccount() {
        return toAccount;
    }

    /**
     * 设置账号/卡号
     *
     * @param toAccount 账号/卡号
     */
    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    /**
     * 获取真实姓名/户名
     *
     * @return to_realname - 真实姓名/户名
     */
    public String getToRealname() {
        return toRealname;
    }

    /**
     * 设置真实姓名/户名
     *
     * @param toRealname 真实姓名/户名
     */
    public void setToRealname(String toRealname) {
        this.toRealname = toRealname;
    }

    /**
     * 获取银联卡开户行
     *
     * @return to_bank - 银联卡开户行
     */
    public String getToBank() {
        return toBank;
    }

    /**
     * 设置银联卡开户行
     *
     * @param toBank 银联卡开户行
     */
    public void setToBank(String toBank) {
        this.toBank = toBank;
    }

    /**
     * 获取发起者备注
     *
     * @return submit_remark - 发起者备注
     */
    public String getSubmitRemark() {
        return submitRemark;
    }

    /**
     * 设置发起者备注
     *
     * @param submitRemark 发起者备注
     */
    public void setSubmitRemark(String submitRemark) {
        this.submitRemark = submitRemark;
    }

    /**
     * 获取审核者备注
     *
     * @return audit_remark - 审核者备注
     */
    public String getAuditRemark() {
        return auditRemark;
    }

    /**
     * 设置审核者备注
     *
     * @param auditRemark 审核者备注
     */
    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }

    /**
     * 获取提交时间
     *
     * @return create_time - 提交时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置提交时间
     *
     * @param createTime 提交时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取审核时间
     *
     * @return audit_time - 审核时间
     */
    public Date getAuditTime() {
        return auditTime;
    }

    /**
     * 设置审核时间
     *
     * @param auditTime 审核时间
     */
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    /**
     * 获取审核状态：1审核通过，2待审，3驳回
     *
     * @return status - 审核状态：1审核通过，2待审，3驳回
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置审核状态：1审核通过，2待审，3驳回
     *
     * @param status 审核状态：1审核通过，2待审，3驳回
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}