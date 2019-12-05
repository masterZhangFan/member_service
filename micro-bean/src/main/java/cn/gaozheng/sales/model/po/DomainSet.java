package cn.gaozheng.sales.model.po;

import javax.persistence.*;
import java.util.Date;

@Table(name = "domain_set")
public class DomainSet {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`agent_id`")
    private Integer agentId;

    @Column(name = "`domain_url`")
    private String domainUrl;

    @Column(name = "`access_token`")
    private String accessToken;

    @Column(name = "`addtime`")
    private Date addtime;

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
     * @return domain_url
     */
    public String getDomainUrl() {
        return domainUrl;
    }

    /**
     * @param domainUrl
     */
    public void setDomainUrl(String domainUrl) {
        this.domainUrl = domainUrl;
    }

    /**
     * @return access_token
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @param accessToken
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * @return addtime
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * @param addtime
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}