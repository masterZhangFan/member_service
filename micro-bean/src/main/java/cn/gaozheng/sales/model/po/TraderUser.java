package cn.gaozheng.sales.model.po;

import javax.persistence.*;

@Table(name = "trader_user")
public class TraderUser {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`agent_id`")
    private Integer agentId;

    @Column(name = "`trader_id`")
    private Integer traderId;

    @Column(name = "`user_name`")
    private String userName;

    @Column(name = "`field_id`")
    private Integer fieldId;

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
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
}