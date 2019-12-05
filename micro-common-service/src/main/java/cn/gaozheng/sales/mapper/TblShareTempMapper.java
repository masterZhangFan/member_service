package cn.gaozheng.sales.mapper;

import cn.gaozheng.sales.common.SalesBaseMapper;
import cn.gaozheng.sales.model.po.TblShareTemp;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TblShareTempMapper extends SalesBaseMapper<TblShareTemp> {
    @Select("select *from tbl_share_temp where share_temp_type = #{share_temp_type}")
    List<TblShareTemp> shareTempList(Integer share_temp_type);
}