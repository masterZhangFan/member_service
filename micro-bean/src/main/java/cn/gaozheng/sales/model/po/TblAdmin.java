package cn.gaozheng.sales.model.po;

import cn.hutool.db.DaoTemplate;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
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

    @Column(name = "`login_time`")
    private Date loginTime;

}