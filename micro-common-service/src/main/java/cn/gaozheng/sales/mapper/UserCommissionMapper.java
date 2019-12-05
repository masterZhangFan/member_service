package cn.gaozheng.sales.mapper;

import cn.gaozheng.sales.common.SalesBaseMapper;
import cn.gaozheng.sales.model.po.UserCommission;
import org.apache.ibatis.annotations.Select;

public interface UserCommissionMapper extends SalesBaseMapper<UserCommission> {
    @Select("SELECT * FROM `user_commission`  WHERE uid = #{uid}")
    UserCommission getUserCommission(String uid);
}