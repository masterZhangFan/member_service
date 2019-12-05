package cn.gaozheng.sales.model.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tbl_delegate")
public class TblDelegate {
    /**
     * 代理ID
     */
    @Id
    @Column(name = "`user_id`")
    private Long userId;

    @Column(name = "`delegate_type_id`")
    private Integer delegateTypeId;


    @Column(name = "`business_license_pic`")
    private String businessLicensePic;

    @Column(name = "`id_card_front_pic`")
    private String idCardFrontPic;

    @Column(name = "`id_card_back_pic`")
    private String idCardBackPic;

    /**
     * 父级代理
     */
    @Column(name = "`parent_delegate_id`")
    private Long parentDelegateId;

    /**
     * 返现金额
     */
    @Column(name = "`cash_back_amount`")
    private Double cashBackAmount;

    /**
     * 是否启用/停用
     */
    @Column(name = "`delegate_enbale`")
    private Boolean delegateEnbale;



}