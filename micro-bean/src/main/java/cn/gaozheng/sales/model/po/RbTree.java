package cn.gaozheng.sales.model.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "rb_tree")
public class RbTree {
    @Id
    @Column(name = "`uid`")
    private Integer uid;

    @Column(name = "`puid`")
    private Integer puid;

    @Column(name = "`time`")
    private Date time;

    /**
     * @return uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * @return puid
     */
    public Integer getPuid() {
        return puid;
    }

    /**
     * @param puid
     */
    public void setPuid(Integer puid) {
        this.puid = puid;
    }

    /**
     * @return time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }
}