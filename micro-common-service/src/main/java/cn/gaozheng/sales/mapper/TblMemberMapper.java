package cn.gaozheng.sales.mapper;

import cn.gaozheng.sales.common.SalesBaseMapper;
import cn.gaozheng.sales.model.po.TblMember;
import cn.gaozheng.sales.model.vo.UserInfo;
import cn.gaozheng.sales.model.vo.member.MemberListParm;
import cn.gaozheng.sales.provider.UserProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface TblMemberMapper extends SalesBaseMapper<TblMember> {

    @SelectProvider(type = UserProvider.class,method = "memberInfos")
    List<UserInfo> memberInfos( MemberListParm req);

    @SelectProvider(type = UserProvider.class,method = "memberInfoNotIncludeDelegate")
    List<UserInfo> memberInfoNotIncludeDelegate( MemberListParm req);
}