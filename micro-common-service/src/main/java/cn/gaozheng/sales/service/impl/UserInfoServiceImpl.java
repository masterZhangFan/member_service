package cn.gaozheng.sales.service.impl;

import cn.gaozheng.sales.mapper.*;
import cn.gaozheng.sales.model.po.RbTree;
import cn.gaozheng.sales.model.po.TblDelegate;
import cn.gaozheng.sales.model.po.TblMember;
import cn.gaozheng.sales.model.po.User;
import cn.gaozheng.sales.model.vo.Banlance;
import cn.gaozheng.sales.model.vo.DelegateInfo;
import cn.gaozheng.sales.model.vo.Fan;
import cn.gaozheng.sales.model.vo.UserInfo;
import cn.gaozheng.sales.model.vo.base.EnumUtils;
import cn.gaozheng.sales.service.RbTreeService;
import cn.gaozheng.sales.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    RbTreeService rbTreeService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    TblMemberMapper tblMemberMapper;
    @Autowired
    TblDelegateMapper tblDelegateMapper;
    @Override
    public List<Fan> getDirectlyFanWithUserId(Long userId){
        List<Fan> fans = new ArrayList<>();
        List<RbTree> rbTreeList = rbTreeService.getBrTreeDirectly(userId);
        if (rbTreeList != null && rbTreeList.size() > 0 ){
            String userIds = rbTreeService.getUnames(rbTreeList);

            fans = userMapper.getFans(userIds);
        }
        return fans;
    }
    public List<Fan> getIndirectFanWithUserId(Long userId){
        List<Fan> fans = new ArrayList<>();
        List<RbTree> rbTreeList = rbTreeService.getBrTreeIndirect(userId);
        if (rbTreeList != null && rbTreeList.size() > 0 ){
            String userIds = rbTreeService.getUnames(rbTreeList);
            fans = userMapper.getFans(userIds);
        }
        return fans;
    }
    @Override
    public UserInfo getUserInfo( Long userId){
        User user = userMapper.selectByPrimaryKey(userId);
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(user,userInfo);
        userInfo.setDelegate(getDelegateInfo(user.getUserId()));
        userInfo.setMemberLevel(this.getMemberInfo(user.getUserId()));
        userInfo.setCash(getSettlementPrice(userId));
        Banlance banlance = getBanlance(userId);
        userInfo.setShoppingBalance(banlance.getShoppingBalance());
        userInfo.setCallBalance(banlance.getCallBalance());
        List<Fan> fanList1 = this.getDirectlyFanWithUserId(userId);
        List<Fan> fanList2 = this.getIndirectFanWithUserId(userId);
        Integer fansNumber = fanList1!= null?fanList1.size():0;
        fansNumber+= fanList2!= null?fanList2.size():0;
        userInfo.setFansNumber(fansNumber);
        return userInfo;
    }
    private DelegateInfo getDelegateInfo( Long userId){
        TblDelegate tblDelegate = tblDelegateMapper.selectByPrimaryKey(userId);
        DelegateInfo delegateInfo = null;
        if (tblDelegate != null){
            delegateInfo = new DelegateInfo();
            cn.gaozheng.sales.utils.BeanUtils.copyPropertiesIgnoreNullValue(tblDelegate,delegateInfo);
        }
        return delegateInfo;
    }
    private Integer getMemberInfo(Long userId){
        TblMember tblMember = tblMemberMapper.selectByPrimaryKey(userId);
        if (tblMember != null) return EnumUtils.MemberTypeJuniorSenior;
        return EnumUtils.MemberTypeJunior;
    }
    private Double getSettlementPrice(Long userId){
        Banlance banlance  = userMapper.getAllSettlementPrice(userId);
        if (banlance != null && banlance.getCash() != null) return  banlance.getCash();
        return 0.0;
    }
    private Banlance getBanlance(Long userId){
        Banlance banlance = userMapper.getBanlance(userId);
        if (banlance == null )
        {
            banlance = new Banlance();
            banlance.setShoppingBalance(0.0);
            banlance.setCallBalance(0.0);
            banlance.setCash(0.0);
        }
        return banlance;
    }
}
