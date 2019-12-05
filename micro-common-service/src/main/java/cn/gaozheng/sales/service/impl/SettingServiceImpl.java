package cn.gaozheng.sales.service.impl;

import cn.gaozheng.sales.mapper.TblMemberSettingMapper;
import cn.gaozheng.sales.model.po.TblMemberSetting;
import cn.gaozheng.sales.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingServiceImpl implements SettingService {
    @Autowired
    TblMemberSettingMapper tblMemberSettingMapper;
    public boolean  sysConfig( TblMemberSetting tblMemberSetting){
        List<TblMemberSetting> tblMemberSettingList = tblMemberSettingMapper.selectAll();
        if (tblMemberSettingList!=null){
            for (TblMemberSetting item:tblMemberSettingList) {
                tblMemberSettingMapper.delete(item);
            }
        }
        tblMemberSettingMapper.insert(tblMemberSetting);
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
