package cn.gaozheng.sales.service.impl;

import cn.gaozheng.sales.exception.SaleException;
import cn.gaozheng.sales.mapper.TblCashBackMapper;
import cn.gaozheng.sales.mapper.TblDelegateMapper;
import cn.gaozheng.sales.mapper.TblDelegateTypeMapper;
import cn.gaozheng.sales.mapper.UserMapper;
import cn.gaozheng.sales.model.po.TblDelegate;
import cn.gaozheng.sales.model.po.TblDelegateType;
import cn.gaozheng.sales.model.po.TblMemberSetting;
import cn.gaozheng.sales.model.po.User;
import cn.gaozheng.sales.model.vo.DelegateListM;
import cn.gaozheng.sales.model.vo.DelegateListParm;
import cn.gaozheng.sales.model.vo.Fan;
import cn.gaozheng.sales.model.vo.UserInfo;
import cn.gaozheng.sales.model.vo.base.EnumUtils;
import cn.gaozheng.sales.service.DelegateService;
import cn.gaozheng.sales.service.RbTreeService;
import cn.gaozheng.sales.service.SettingService;
import cn.gaozheng.sales.service.UserInfoService;
import cn.gaozheng.sales.utils.BeanUtils;
import cn.gaozheng.sales.utils.EmptyUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DelegateServiceImpl implements DelegateService {
    @Autowired
    TblDelegateMapper tblDelegateMapper;
    @Autowired
    TblDelegateTypeMapper tblDelegateTypeMapper;
    @Autowired
    TblCashBackMapper tblCashBackMapper;
    @Autowired
    SettingService settingService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RbTreeService rbTreeService;
    @Autowired
    UserInfoService userInfoService;

    @Override
     public List<TblDelegateType> getDelegateTypes(){
         return tblDelegateTypeMapper.selectAll();
     }
    @Override
    public List<TblDelegateType> getDelegateTypesByUserId(Long userId){
        List<TblDelegateType> tblDelegateTypes = tblDelegateTypeMapper.selectAll();
        DelegateListM delegateListM = this.delegate(userId);
        if (delegateListM == null){
            throw new SaleException("此用户不是代理");
        }
        List<TblDelegateType> results = new ArrayList<>();
        for (TblDelegateType item:tblDelegateTypes ) {
            if (item.getDelegateTypeLevel().compareTo(delegateListM.getDelegateTypeLevel())>0){
                TblDelegateType copy = new TblDelegateType();
                BeanUtils.copyPropertiesIgnoreNullValue(item,copy);
                results.add(item);
            }
        }
        return results;
    }
     @Override
    public PageInfo<DelegateListM> delegateList( DelegateListParm delegateListParm){
        List<DelegateListM> tblDelegates = tblDelegateMapper.delegateList(delegateListParm);
        PageInfo<DelegateListM> pageInfo = new PageInfo<>(tblDelegates);
         if (pageInfo.getList()!= null){
             for (DelegateListM item:pageInfo.getList()) {
                 List<Fan> f1 =  userInfoService.getDirectlyFanWithUserId(item.getUserId());
                 List<Fan> f2 = userInfoService.getIndirectFanWithUserId(item.getUserId());
                 List<Fan> f3 = userInfoService.getFissionFanWithUserId(item.getUserId());
                 int count = 0;
                 if (f1 != null) count=count+f1.size();
                 if (f2 != null) count=count+f1.size();
                 if (f3 != null) count=count+f1.size();
                 item.setFans(count);
                 Double cashBack =  tblCashBackMapper.cashBackMoneyOfDelegate(delegateListParm.getParentDelegateId(),item.getUserId());
                 if (cashBack == null) cashBack = 0.0;
                 item.setMoneyTotal(cashBack);
                 if (!EmptyUtil.isNotEmpty(item.getIcon())){
                     item.setIcon(EnumUtils.defaultProtrailt);
                 }
             }
         }
        return pageInfo;
    }
    @Override
    public List<DelegateListM> delegateListNotOnPage(Long userId){
        DelegateListParm delegateListParm = new DelegateListParm();
        delegateListParm.setParentDelegateId(userId);
        List<DelegateListM> tblDelegates = tblDelegateMapper.delegateList(delegateListParm);
        if (tblDelegates!= null){
            for (DelegateListM item:tblDelegates) {
                List<Fan> f1 =  userInfoService.getDirectlyFanWithUserId(item.getUserId());
                List<Fan> f2 = userInfoService.getIndirectFanWithUserId(item.getUserId());
                List<Fan> f3 = userInfoService.getFissionFanWithUserId(item.getUserId());
                int count = 0;
                if (f1 != null) count=count+f1.size();
                if (f2 != null) count=count+f1.size();
                if (f3 != null) count=count+f1.size();
                item.setFans(count);
                Double cashBack =  tblCashBackMapper.cashBackMoneyOfDelegate(delegateListParm.getParentDelegateId(),item.getUserId());
                if (cashBack == null) cashBack = 0.0;
                item.setMoneyTotal(cashBack);
                if (!EmptyUtil.isNotEmpty(item.getIcon())){
                    item.setIcon(EnumUtils.defaultProtrailt);
                }
            }
        }
        return tblDelegates;
    }
    @Override
    public DelegateListM delegate(Long userId){
        DelegateListParm delegateListParm = new DelegateListParm();
        delegateListParm.setUserId(userId);
        List<DelegateListM> tblDelegates = tblDelegateMapper.delegateList(delegateListParm);
        if (tblDelegates != null && tblDelegates.size() > 0){
            for (DelegateListM item:tblDelegates) {
                if (!EmptyUtil.isNotEmpty(item.getIcon())){
                    item.setIcon(EnumUtils.defaultProtrailt);
                }
            }
            return tblDelegates.get(0);
        }
        return null;
    }
    @Override
    public Boolean setDelegate(TblDelegate tblDelegate){
        this.checkDelegate(tblDelegate);
        TblDelegate extit = tblDelegateMapper.selectByPrimaryKey(tblDelegate.getUserId());
        if (extit == null){
            extit = new TblDelegate();
            BeanUtils.copyPropertiesIgnoreNullValue(tblDelegate,extit);
            tblDelegateMapper.insert(tblDelegate) ;
        }
        else {
            BeanUtils.copyPropertiesIgnoreNullValue(tblDelegate,extit);
            tblDelegateMapper.updateByPrimaryKey(tblDelegate) ;
        }

        return true;
    }
    /**
     * 获取某用户的代理
     * @param userId
     * @return
     */
    @Override
    public User getDelegateOfUser( Long userId){
        return delegateUser(userId);
    }

    /**
     * 父级代理
     * @param userId
     * @return
     */
    @Override
    public User fatherDelegate(Long userId){
        TblDelegate fatherDelegate =  tblDelegateMapper.getFatherDelegate(userId);
        if (fatherDelegate!=null){
            return userMapper.selectByPrimaryKey(fatherDelegate.getUserId());
        }
        return null;
    }
    private TblMemberSetting checkSetting(){
        TblMemberSetting tblMemberSetting = settingService.getSysConfig();
        if (tblMemberSetting == null){
            throw new SaleException("系统未设置");
        }
        return tblMemberSetting;
    }
    private void  checkDelegate(TblDelegate tblDelegate){
        if ((tblDelegate.getParentDelegateId()== null || tblDelegate.getParentDelegateId() == 0) && tblDelegate.getCashBackAmount() == null || tblDelegate.getCashBackAmount() < 0) {
            throw new SaleException("代理返现金额错误");
        }
        if (tblDelegate.getUserId() == null || tblDelegate.getUserId() < 1){
            throw new SaleException("请选择用户");
        }
        if (tblDelegate.getDelegateTypeId() == null || tblDelegate.getDelegateTypeId() < 1){
            throw new SaleException("请选择代理类型");
        }
        UserInfo userInfo = userInfoService.getUserInfo(tblDelegate.getUserId());
        if (userInfo == null){
            throw new SaleException("用户不存在");
        }
        if (!EnumUtils.MemberTypeJuniorSenior.equals(userInfo.getMemberLevel())){
            throw new SaleException("用户不是高级会员");
        }
        TblDelegateType tblDelegateType = this.checkDelegateType(tblDelegate.getDelegateTypeId());
        TblMemberSetting tblMemberSetting = this.checkSetting();
        double remainPrice = tblMemberSetting.getMemberPrice() - tblMemberSetting.getOnceLevelCashback()+tblMemberSetting.getSecondLevelCashback();
        if (tblDelegate.getParentDelegateId() != null && tblDelegate.getParentDelegateId() > 0)
        {
            TblDelegate fatherDelegate =  tblDelegateMapper.selectByPrimaryKey(tblDelegate.getParentDelegateId());
            if (fatherDelegate != null){
                TblDelegateType fatherDelegateType = this.checkDelegateType(fatherDelegate.getDelegateTypeId());
                if (tblDelegateType.getDelegateTypeLevel() <= fatherDelegateType.getDelegateTypeLevel()){
                    throw new SaleException("代理等级不能超过父级");
                }
                tblDelegate.setCashBackAmount(fatherDelegate.getCashBackAmount());
            }
            else {
                tblDelegate.setParentDelegateId(null);
            }
        }
        if (tblDelegate.getCashBackAmount() > remainPrice){
            throw new SaleException("返现金额不能超过"+remainPrice);
        }
    }
    private User delegateUser(Long userId){
        User user = rbTreeService.fatherTree(userId);
        if (user != null){
            TblDelegate delegate = tblDelegateMapper.selectByPrimaryKey(user.getUserId());
            if (delegate != null) {
                return user;
            }
            else {
                return delegateUser(user.getUserId());
            }
        }
        else {
            return null;
        }
    }
    private TblDelegateType  checkDelegateType(Integer delegateTypeId){
        TblDelegateType tblDelegateType =  tblDelegateTypeMapper.selectByPrimaryKey(delegateTypeId);
        if (tblDelegateType == null){
            throw new SaleException("代理类型不存在");
        }
        return tblDelegateType;
    }

}
