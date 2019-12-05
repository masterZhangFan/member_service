package cn.gaozheng.sales.model.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tbl_charge")
public class TblCharge {
    @Id
    @Column(name = "`charge_id`")
    private Integer chargeId;

    @Column(name = "`charge_amount`")
    private Double chargeAmount;

    @Column(name = "`call_amount`")
    private Double callAmount;

    @Column(name = "`shopping_amount`")
    private Double shoppingAmount;

    @Column(name = "`charge_desc`")
    private String chargeDesc;

    /**
     * @return charge_id
     */
    public Integer getChargeId() {
        return chargeId;
    }

    /**
     * @param chargeId
     */
    public void setChargeId(Integer chargeId) {
        this.chargeId = chargeId;
    }

    /**
     * @return charge_amount
     */
    public Double getChargeAmount() {
        return chargeAmount;
    }

    /**
     * @param chargeAmount
     */
    public void setChargeAmount(Double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    /**
     * @return call_amount
     */
    public Double getCallAmount() {
        return callAmount;
    }

    /**
     * @param callAmount
     */
    public void setCallAmount(Double callAmount) {
        this.callAmount = callAmount;
    }

    /**
     * @return shopping_amount
     */
    public Double getShoppingAmount() {
        return shoppingAmount;
    }

    /**
     * @param shoppingAmount
     */
    public void setShoppingAmount(Double shoppingAmount) {
        this.shoppingAmount = shoppingAmount;
    }

    /**
     * @return charge_desc
     */
    public String getChargeDesc() {
        return chargeDesc;
    }

    /**
     * @param chargeDesc
     */
    public void setChargeDesc(String chargeDesc) {
        this.chargeDesc = chargeDesc;
    }
}