package cn.gaozheng.sales.mapper;


import cn.gaozheng.sales.common.SalesBaseMapper;
import cn.gaozheng.sales.model.po.RbIncome;
import cn.gaozheng.sales.model.vo.UserInfo;
import cn.gaozheng.sales.model.vo.member.MemberListParm;
import cn.gaozheng.sales.provider.UserProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface RbIncomeMapper extends SalesBaseMapper<RbIncome> {
    @SelectProvider(type = UserProvider.class,method = "memberInfos")
    List<UserInfo> memberInfos( MemberListParm req);

    @SelectProvider(type = UserProvider.class,method = "memberInfoNotIncludeDelegate")
    List<UserInfo> memberInfoNotIncludeDelegate(String userNames,String searchText);

    @Select("SELECT\n" +
            "\tt1.*\n" +
            "FROM\n" +
            "\trb_income t1\n" +
            "JOIN `user` t2  ON t1.uid = t2.user_name\n" +
            "WHERE t2.user_id = #{user_id}")
    RbIncome getUSerInfo(Long user_id);
}