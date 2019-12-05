package cn.gaozheng.sales.mapper;

import cn.gaozheng.sales.common.SalesBaseMapper;
import cn.gaozheng.sales.model.po.TblCharge;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TblChargeMapper extends SalesBaseMapper<TblCharge> {
    @Select("SELECT *FROM tbl_charge ORDER BY charge_amount")
    List<TblCharge> chargeList();

}