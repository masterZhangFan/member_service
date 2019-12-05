package cn.gaozheng.sales.model.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tbl_member")
public class TblMember {
    @Id
    @Column(name = "`member_id`")
    private Integer memberId;

    /**
     * 所属代理
     */
    @Column(name = "`delegate_id`")
    private Integer delegateId;

    /**
     * @return member_id
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * @param memberId
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取所属代理
     *
     * @return delegate_id - 所属代理
     */
    public Integer getDelegateId() {
        return delegateId;
    }

    /**
     * 设置所属代理
     *
     * @param delegateId 所属代理
     */
    public void setDelegateId(Integer delegateId) {
        this.delegateId = delegateId;
    }
}