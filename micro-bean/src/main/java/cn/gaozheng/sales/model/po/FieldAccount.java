package cn.gaozheng.sales.model.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "field_account")
public class FieldAccount {
    @Id
    @Column(name = "`field_account_id`")
    private Integer fieldAccountId;

    @Column(name = "`field_id`")
    private Integer fieldId;

    @Column(name = "`balance`")
    private Long balance;

    @Column(name = "`discount`")
    private Integer discount;

    @Column(name = "`account_type`")
    private Byte accountType;

    @Column(name = "`quota`")
    private Long quota;

    @Column(name = "`money_id`")
    private Integer moneyId;

    @Column(name = "`price`")
    private Float price;

    /**
     * @return field_account_id
     */
    public Integer getFieldAccountId() {
        return fieldAccountId;
    }

    /**
     * @param fieldAccountId
     */
    public void setFieldAccountId(Integer fieldAccountId) {
        this.fieldAccountId = fieldAccountId;
    }

    /**
     * @return field_id
     */
    public Integer getFieldId() {
        return fieldId;
    }

    /**
     * @param fieldId
     */
    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    /**
     * @return balance
     */
    public Long getBalance() {
        return balance;
    }

    /**
     * @param balance
     */
    public void setBalance(Long balance) {
        this.balance = balance;
    }

    /**
     * @return discount
     */
    public Integer getDiscount() {
        return discount;
    }

    /**
     * @param discount
     */
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    /**
     * @return account_type
     */
    public Byte getAccountType() {
        return accountType;
    }

    /**
     * @param accountType
     */
    public void setAccountType(Byte accountType) {
        this.accountType = accountType;
    }

    /**
     * @return quota
     */
    public Long getQuota() {
        return quota;
    }

    /**
     * @param quota
     */
    public void setQuota(Long quota) {
        this.quota = quota;
    }

    /**
     * @return money_id
     */
    public Integer getMoneyId() {
        return moneyId;
    }

    /**
     * @param moneyId
     */
    public void setMoneyId(Integer moneyId) {
        this.moneyId = moneyId;
    }

    /**
     * @return price
     */
    public Float getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(Float price) {
        this.price = price;
    }
}