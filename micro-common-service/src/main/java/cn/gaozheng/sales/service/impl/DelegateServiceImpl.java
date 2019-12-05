package cn.gaozheng.sales.service.impl;

import cn.gaozheng.sales.exception.SaleException;
import cn.gaozheng.sales.mapper.TblDelegateMapper;
import cn.gaozheng.sales.mapper.TblDelegateTypeMapper;
import cn.gaozheng.sales.mapper.UserMapper;
import cn.gaozheng.sales.model.po.TblDelegate;
import cn.gaozheng.sales.model.po.TblDelegateType;
import cn.gaozheng.sales.model.po.TblMemberSetting;
import cn.gaozheng.sales.model.po.User;
import cn.gaozheng.sales.model.vo.DelegateListM;
import cn.gaozheng.sales.model.vo.DelegateListParm;
import cn.gaozheng.sales.service.DelegateService;
import cn.gaozheng.sales.service.RbTreeService;
import cn.gaozheng.sales.service.SettingService;
import cn.gaozheng.sales.utils.BeanUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DelegateServiceImpl implements DelegateService {
    @Autowired
    TblDelegateMapper tblDelegateMapper;
    @Autowired
    TblDelegateTypeMapper tblDelegateTypeMapper;
    @Autowired
    SettingService settingService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RbTreeService rbTreeService;

    @Override
     public List<TblDelegateType> getDelegateTypes(){
         return tblDelegateTypeMapper.selectAll();
     }
     @Override
    public PageInfo<DelegateListM> delegateList( DelegateListParm delegateListParm){
        List<DelegateListM> tblDelegates = tblDelegateMapper.delegateList(delegateListParm);
        PageInfo<DelegateListM> pageInfo = new PageInfo<>(tblDelegates);
        return pageInfo;
    }
    @Override
    public List<DelegateListM> delegateListNotOnPage(Long userId){
        DelegateListParm delegateListParm = new DelegateListParm();
        delegateListParm.setParentDelegateId(userId);
        List<DelegateListM> tblDelegates = tblDelegateMapper.delegateList(delegateListParm);
        return tblDelegates;
    }
    @Override
    public DelegateListM delegate(Long userId){
        DelegateListParm delegateListParm = new DelegateListParm();
        delegateListParm.setUserId(userId);
        List<DelegateListM> tblDelegates = tblDelegateMapper.delegateList(delegateListParm);
        if (tblDelegates == null || tblDelegates.size() == 0){
            throw new SaleException("无代理信息");
        }
        return tblDelegates.get(0);
    }
    @Override
    public Boolean setDelegate(TblDelegate tblDelegate){
        this.checkDelegate(tblDelegate);
        TblDelegate extit = tblDelegateMapper.selectByPrimaryKey(tblDelegate.getUserId());
        if (extit != null){
            tblDelegateMapper.deleteByPrimaryKey(extit);
        }
        else {
            extit = new TblDelegate();
        }
        BeanUtils.copyPropertiesIgnoreNullValue(tblDelegate,extit);
        return tblDelegateMapper.insert(tblDelegate) >0 ;
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
        TblDelegate fatherDelegate =  tblDelegateMapper.selectByPrimaryKey(userId);
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
        if (tblDelegate.getCashBackAmount() == null || tblDelegate.getCashBackAmount() < 0) {
            throw new SaleException("代理返现金额错误");
        }
        if (tblDelegate.getUserId() == null || tblDelegate.getUserId() < 1){
            throw new SaleException("请选择用户");
        }
        if (tblDelegate.getDelegateTypeId() == null || tblDelegate.getDelegateTypeId() < 1){
            throw new SaleException("请选择代理类型");
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
                remainPrice= fatherDelegate.getCashBackAmount();
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
