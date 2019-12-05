package cn.gaozheng.sales.model.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tbl_admin")
public class TblAdmin {
    @Id
    @Column(name = "`admin_id`")
    private Integer adminId;

    @Column(name = "`admin_name`")
    private String adminName;

    @Column(name = "`admin_password`")
    private String adminPassword;

    @Column(name = "`admin_token`")
    private String adminToken;

    /**
     * @return admin_id
     */
    public Integer getAdminId() {
        return adminId;
    }

    /**
     * @param adminId
     */
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    /**
     * @return admin_name
     */
    public String getAdminName() {
        return adminName;
    }

    /**
     * @param adminName
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    /**
     * @return admin_password
     */
    public String getAdminPassword() {
        return adminPassword;
    }

    /**
     * @param adminPassword
     */
    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    /**
     * @return admin_token
     */
    public String getAdminToken() {
        return adminToken;
    }

    /**
     * @param adminToken
     */
    public void setAdminToken(String adminToken) {
        this.adminToken = adminToken;
    }
}