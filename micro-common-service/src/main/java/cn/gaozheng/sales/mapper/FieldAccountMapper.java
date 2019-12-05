package cn.gaozheng.sales.mapper;

import cn.gaozheng.sales.common.SalesBaseMapper;
import cn.gaozheng.sales.model.po.FieldAccount;
import org.apache.ibatis.annotations.Select;

public interface FieldAccountMapper extends SalesBaseMapper<FieldAccount> {
    @Select("select *from field_account where field_id = #{field_id}")
    FieldAccount getFieldAccountWithUserId(Long userId);
}