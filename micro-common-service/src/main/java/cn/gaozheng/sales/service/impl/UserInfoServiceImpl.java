package cn.gaozheng.sales.service.impl;

import ch.qos.logback.core.util.TimeUtil;
import cn.gaozheng.sales.exception.SaleException;
import cn.gaozheng.sales.mapper.*;
import cn.gaozheng.sales.model.po.*;
import cn.gaozheng.sales.model.vo.*;
import cn.gaozheng.sales.model.vo.base.EnumUtils;
import cn.gaozheng.sales.service.DelegateService;
import cn.gaozheng.sales.service.RbTreeService;
import cn.gaozheng.sales.service.UserInfoService;
import cn.gaozheng.sales.utils.EmptyUtil;
import cn.gaozheng.sales.utils.TimeUtils;
import cn.gaozheng.util.RandomUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Console;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    RbTreeService rbTreeService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    TblDelegateMapper tblDelegateMapper;
    @Autowired
    RbIncomeMapper rbIncomeMapper;
    @Autowired
    DelegateService delegateService;
    @Autowired
    TraderUserMapper traderUserMapper;
    @Autowired
    VFieldMapper vFieldMapper;
    @Override
    public List<Fan> getDirectlyFanWithUserId(Long userId){
        List<Fan> fans = new ArrayList<>();
        List<RbTree> rbTreeList = rbTreeService.getBrTreeDirectly(userId);
        if (rbTreeList != null && rbTreeList.size() > 0 ){
            String userIds = rbTreeService.getUnames(rbTreeList);
            fans = userMapper.getFans(userIds);
        }
        if (fans!= null){
            for (Fan item:fans) {
                if(!EmptyUtil.isNotEmpty(item.getIcon())){
                    item.setIcon(EnumUtils.defaultProtrailt);
                }
            }
        }
        return fans;
    }
    @Override
    @Transactional(rollbackFor ={SQLException.class, RuntimeException.class})
    public void genUser(Integer count,String start,String end){
        User maxUser = userMapper.getMaxUser();
        String userName = maxUser.getUserName();
        Long filedId = maxUser.getUserId();
//        String userName = "65535";

//        Integer filedId = 653;
        Integer userNameInt =  Integer.parseInt(userName);
        int countInsert = 0;
        while (true){
            if (countInsert > count){
                return;
            }
            String phone = RandomUtil.getTel();
            List<User> exits = userMapper.getUsersByPhone(phone);
            if(exits != null && exits.size() >0){
                continue;
            }
            userNameInt++;
            filedId++;

            Date createTime = RandomUtil.randomDate(start,end);
            System.out.printf("电话:"+phone+";filedId:"+filedId.toString()+";time:"+ TimeUtils.format(createTime,"yyyy-MM-dd HH:mm:ss")+";user_name"+userName);

            User user = new User();
            user.setMobile(phone);
            user.setPhone(phone);
            user.setUserName(userNameInt.toString());
            user.setRegPwd("678901");
            user.setUserType("1");
            user.setLongName(phone);
            user.setWlkBind(0);
            user.setWlkCheckIdcard(0);
            user.setZfPwd("TEST");
            user.setFieldId(Integer.parseInt(filedId.toString()));
            user.setCreateTime(createTime);
            user.setActiveTime(createTime);
            user.setValidDate(TimeUtils.parseDate("2030-01-01 00:00:00","yyyy-MM-dd HH:mm:ss"));
            user.setNickname(phone);
            userMapper.insert(user);

            VField vField = new VField();
            vField.setAgentId(1);
            vField.setFieldDesc("1");
            vField.setPstnNum("");
            vField.setFieldName(userNameInt.toString());
            vField.setFeeTableId(2);
            vField.setFieldType("1");
            vField.setFeeTableId(2);
            vField.setCreateTime(createTime);
            vField.setActiveTime(createTime);
            vFieldMapper.insert(vField);
            userMapper.updateUserPageCount();
            countInsert++;
        }

    }
    public List<Fan> getIndirectFanWithUserId(Long userId){
        List<Fan> fans = new ArrayList<>();
        List<RbTree> rbTreeList = rbTreeService.getBrTreeIndirect(userId);
        if (rbTreeList != null && rbTreeList.size() > 0 ){
            String userIds = rbTreeService.getUnames(rbTreeList);
            fans = userMapper.getFans(userIds);
        }
        if (fans!= null){
            for (Fan item:fans) {
                if(!EmptyUtil.isNotEmpty(item.getIcon())){
                    item.setIcon(EnumUtils.defaultProtrailt);
                }
            }
        }
        return fans;
    }
    @Override
    public List<Fan> getFissionFanWithUserId(Long userId){
        List<Fan> fans = new ArrayList<>();
        List<RbTree> rbTreeList = rbTreeService.getBrTreeFission(userId);
        if (rbTreeList != null && rbTreeList.size() > 0 ){
            String userIds = rbTreeService.getUnames(rbTreeList);
            fans = userMapper.getFans(userIds);
        }
        if (fans!= null){
            for (Fan item:fans) {
                if(!EmptyUtil.isNotEmpty(item.getIcon())){
                    item.setIcon(EnumUtils.defaultProtrailt);
                }
            }
        }
        return fans;
    }
    @Override
    public UserInfo getUserInfo( Long userId){
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null){
            throw new SaleException("用户不存在");
        }
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(user,userInfo);
        userInfo.setDelegate(getDelegateInfo(user.getUserId()));
        userInfo.setMemberLevel(this.getMemberInfo(user.getUserId()));
        userInfo.setCash(getSettlementPrice(userId));
        if(!EmptyUtil.isNotEmpty(userInfo.getIcon())){
            userInfo.setIcon(EnumUtils.defaultProtrailt);
        }
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
        DelegateListM delegateListM = delegateService.delegate(userId);
        DelegateInfo delegateInfo = null;
        if (delegateListM != null){

            delegateInfo = new DelegateInfo();
            cn.gaozheng.sales.utils.BeanUtils.copyPropertiesIgnoreNullValue(delegateListM,delegateInfo);
        }
        return delegateInfo;
    }
    private Integer getMemberInfo(Long userId){
        RbIncome rbIncome = rbIncomeMapper.getUSerInfo(userId);
        if (rbIncome == null){
            throw new SaleException("会员信息不存在");
        }
        return rbIncome.getRank();
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
