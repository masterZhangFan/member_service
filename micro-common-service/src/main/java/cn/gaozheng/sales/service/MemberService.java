package cn.gaozheng.sales.service;

import cn.gaozheng.sales.model.po.TblDelegate;
import cn.gaozheng.sales.model.po.TblDelegateType;
import cn.gaozheng.sales.model.vo.DelegateListM;
import cn.gaozheng.sales.model.vo.UserInfo;
import cn.gaozheng.sales.model.vo.member.MemberListParm;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MemberService {

    PageInfo<UserInfo> memberInfos( MemberListParm req);

    List<UserInfo> memberInfoNotIncludeDelegate(MemberListParm req);

    UserInfo memberInfo(Integer userId);

}
