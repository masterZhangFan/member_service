package cn.gaozheng.sales.mapper;

import cn.gaozheng.sales.common.SalesBaseMapper;
import cn.gaozheng.sales.model.po.TblPayOrder;
import org.apache.ibatis.annotations.Select;

public interface TblPayOrderMapper extends SalesBaseMapper<TblPayOrder> {
    @Select("select *from tbl_pay_order where pay_order = #{pay_order}")
    TblPayOrder getPayorder(String pay_order);
}