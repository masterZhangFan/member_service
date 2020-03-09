package cn.gaozheng.sales.model.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "v_field")
public class VField {
    @Id
    @Column(name = "`field_id`")
    private Integer fieldId;

    @Column(name = "`agent_id`")
    private Integer agentId;

    @Column(name = "`pstn_num`")
    private String pstnNum;

    @Column(name = "`field_name`")
    private String fieldName;

    @Column(name = "`field_desc`")
    private String fieldDesc;

    @Column(name = "`enable_flag`")
    private String enableFlag;

    @Column(name = "`field_type`")
    private String fieldType;

    @Column(name = "`fee_table_id`")
    private Integer feeTableId;

    @Column(name = "`create_time`")
    private Date createTime;

    @Column(name = "`active_time`")
    private Date activeTime;

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
     * @return pstn_num
     */
    public String getPstnNum() {
        return pstnNum;
    }

    /**
     * @param pstnNum
     */
    public void setPstnNum(String pstnNum) {
        this.pstnNum = pstnNum;
    }

    /**
     * @return field_name
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * @param fieldName
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * @return field_desc
     */
    public String getFieldDesc() {
        return fieldDesc;
    }

    /**
     * @param fieldDesc
     */
    public void setFieldDesc(String fieldDesc) {
        this.fieldDesc = fieldDesc;
    }

    /**
     * @return enable_flag
     */
    public String getEnableFlag() {
        return enableFlag;
    }

    /**
     * @param enableFlag
     */
    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    /**
     * @return field_type
     */
    public String getFieldType() {
        return fieldType;
    }

    /**
     * @param fieldType
     */
    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    /**
     * @return fee_table_id
     */
    public Integer getFeeTableId() {
        return feeTableId;
    }

    /**
     * @param feeTableId
     */
    public void setFeeTableId(Integer feeTableId) {
        this.feeTableId = feeTableId;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return active_time
     */
    public Date getActiveTime() {
        return activeTime;
    }

    /**
     * @param activeTime
     */
    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }
}