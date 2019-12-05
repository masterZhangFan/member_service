package cn.gaozheng.sales.model.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tbl_delegate_type")
public class TblDelegateType {
    @Id
    @Column(name = "`delegate_type_id`")
    private Integer delegateTypeId;

    @Column(name = "`delegate_type_name`")
    private String delegateTypeName;

    /**
     * 一级、二级、三级
     */
    @Column(name = "`delegate_type_level`")
    private Integer delegateTypeLevel;

}