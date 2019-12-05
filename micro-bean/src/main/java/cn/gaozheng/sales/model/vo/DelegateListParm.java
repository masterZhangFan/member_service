package cn.gaozheng.sales.model.vo;

import cn.gaozheng.sales.model.vo.base.PageRequestModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DelegateListParm extends PageRequestModel {
    //代理类型
    private Integer delegateTypeId;
    //代理ID
    private Long userId;
    //父级代理
    private Long parentDelegateId;

    private String searchText;

}
