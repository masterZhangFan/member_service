package cn.gaozheng.sales.model.vo.base;

import cn.gaozheng.sales.exception.SalesException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel

public class PageRequestModel {

    @ApiModelProperty(value = "查询页")
    private Integer page_index;
    @ApiModelProperty(value = "页数量")
    private Integer page_number;

    public void checkParmThrowException(){
        if (this.page_index == null || this.page_index <1)
        {
            throw new SalesException("page_index 参数 错误");
        }
        if (this.page_number == null || this.page_number <1)
        {
            throw new SalesException("page_number 参数 错误");
        }
    }
    public Integer getPage_index() {
        return page_index;
    }

    public void setPage_index(Integer page_index) {
        this.page_index = page_index;
    }

    public Integer getPage_number() {
        return page_number;
    }

    public void setPage_number(Integer page_number) {
        this.page_number = page_number;
    }
}
