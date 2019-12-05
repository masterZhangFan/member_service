package cn.gaozheng.sales.mapper;

import cn.gaozheng.sales.common.SalesBaseMapper;
import cn.gaozheng.sales.model.po.UserCommissionSet;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

public interface UserCommissionSetMapper extends SalesBaseMapper<UserCommissionSet> {
    @Select("SELECT * FROM `user_commission_set` WHERE uid = #{uid} limit 1")
    UserCommissionSet getWithUid(String uid);
}