package cn.gaozheng.sales.model.vo.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class PageResponseModel {
    @ApiModelProperty(value = "当前页,起始页1")
    private Integer page_index;
    @ApiModelProperty(value = "总页")
    private Integer page_totol;

    public Integer getPage_index() {
        return page_index;
    }

    public void setPage_index(Integer page_index) {
        this.page_index = page_index;
    }

    public Integer getPage_totol() {
        return page_totol;
    }

    public void setPage_totol(Integer page_totol) {
        this.page_totol = page_totol;
    }
}
