package cn.gaozheng.sales.service.impl;

import cn.gaozheng.sales.exception.SaleException;
import cn.gaozheng.sales.mapper.RbIncomeMapper;
import cn.gaozheng.sales.model.po.RbIncome;
import cn.gaozheng.sales.model.po.RbTree;
import cn.gaozheng.sales.model.vo.UserInfo;
import cn.gaozheng.sales.model.vo.member.MemberListParm;
import cn.gaozheng.sales.service.MemberService;
import cn.gaozheng.sales.service.RbTreeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    RbIncomeMapper rbIncomeMapper;
    @Autowired
    RbTreeService rbTreeService;

    @Override
    public PageInfo<UserInfo> memberInfos( MemberListParm req){
        List<UserInfo> userInfos = rbIncomeMapper.memberInfos(req);
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userInfos);
        return pageInfo;
    }
    @Override
    public UserInfo memberInfo(Long userId){
        MemberListParm memberListParm = new MemberListParm();
        memberListParm.setUserId(userId);
        List<UserInfo> userInfos = rbIncomeMapper.memberInfos(memberListParm);
        if (userInfos == null || userInfos.size() ==  0) {
            throw new SaleException("用户信息不存在");
        }
        return userInfos.get(0);
    }
    @Override
    public Boolean setMember(Integer rank,Long userId){
        RbIncome rbIncome = rbIncomeMapper.getUSerInfo(userId);
        if (rbIncome != null){
            rbIncome.setRank(rank);
            rbIncomeMapper.updateByPrimaryKey(rbIncome);
            return true;
        }
        return false;
    }
    @Override
    public List<UserInfo> memberInfoNotIncludeDelegate(MemberListParm req){
        String userNames =  "";
        if (req.getUserId() != null && req.getUserId() > 0 ){
            List<RbTree> rbTreeList1 =  rbTreeService.getBrTreeDirectly(req.getUserId());
            List<RbTree> rbTreeList2 =  rbTreeService.getBrTreeIndirect(req.getUserId());
            List<RbTree> rbTreeList = new ArrayList<>();
            if (rbTreeList1 != null && rbTreeList1.size() > 0 ){
                rbTreeList.addAll(rbTreeList1);
            }
            if (rbTreeList2 != null && rbTreeList2.size() > 0 ){
               rbTreeList.addAll(rbTreeList2);
            }
            for (RbTree rbTree :rbTreeList) {
                userNames+= "'"+rbTree.getUid()+"'";
                if (rbTreeList.indexOf(rbTree) != rbTreeList.size() - 1){
                    userNames+=",";
                }
            }
        }
        List<UserInfo> userInfos = rbIncomeMapper.memberInfoNotIncludeDelegate(userNames,req.getSearchText());
        return userInfos;
    }
}
