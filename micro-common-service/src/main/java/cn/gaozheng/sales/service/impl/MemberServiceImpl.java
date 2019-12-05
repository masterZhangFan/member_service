package cn.gaozheng.sales.service.impl;

import cn.gaozheng.sales.exception.SaleException;
import cn.gaozheng.sales.mapper.TblMemberMapper;
import cn.gaozheng.sales.model.vo.UserInfo;
import cn.gaozheng.sales.model.vo.member.MemberListParm;
import cn.gaozheng.sales.service.MemberService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    TblMemberMapper tblMemberMapper;

    @Override
    public PageInfo<UserInfo> memberInfos( MemberListParm req){
        List<UserInfo> userInfos = tblMemberMapper.memberInfos(req);
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userInfos);
        return pageInfo;
    }
    @Override
    public UserInfo memberInfo(Integer userId){
        MemberListParm memberListParm = new MemberListParm();
        memberListParm.setUserId(userId);
        List<UserInfo> userInfos = tblMemberMapper.memberInfos(memberListParm);
        if (userInfos == null || userInfos.size() ==  0) {
            throw new SaleException("用户信息不存在");
        }
        return userInfos.get(0);
    }
    @Override
    public List<UserInfo> memberInfoNotIncludeDelegate(MemberListParm req){
        List<UserInfo> userInfos = tblMemberMapper.memberInfos(req);
       return userInfos;
    }
}
