package cn.gaozheng.sales.service;

import cn.gaozheng.sales.model.po.TblMemberSetting;

public interface SettingService {
    boolean  sysConfig(TblMemberSetting tblMemberSetting);

    TblMemberSetting  getSysConfig();
}
