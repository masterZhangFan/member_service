package cn.gaozheng.sales.service.impl;

import cn.gaozheng.sales.mapper.TblMemberSettingMapper;
import cn.gaozheng.sales.model.po.TblMemberSetting;
import cn.gaozheng.sales.service.SettingService;
import cn.gaozheng.sales.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingServiceImpl implements SettingService {
    @Autowired
    TblMemberSettingMapper tblMemberSettingMapper;
    public boolean  sysConfig( TblMemberSetting tblMemberSetting){
        TblMemberSetting exit = null;
        List<TblMemberSetting> tblMemberSettingList = tblMemberSettingMapper.selectAll();
        if (tblMemberSettingList!=null && tblMemberSettingList.size()>1){
            exit = tblMemberSettingList.get(0);
        }
        if (exit == null) exit = new TblMemberSetting();
        BeanUtils.copyPropertiesIgnoreNullValue(tblMemberSetting,exit);
        if (exit.getSettingId() == null)  tblMemberSettingMapper.insert(tblMemberSetting);
        else {
            tblMemberSettingMapper.updateByPrimaryKey(exit);
        }
        return true;
    }
    @Override
    public TblMemberSetting  getSysConfig(){
        TblMemberSetting result = null;
        List<TblMemberSetting> tblMemberSettingList = tblMemberSettingMapper.selectAll();
        if (tblMemberSettingList != null && tblMemberSettingList.size() > 0){
            result = tblMemberSettingList.get(0);
        }
        return result;
    }
}
