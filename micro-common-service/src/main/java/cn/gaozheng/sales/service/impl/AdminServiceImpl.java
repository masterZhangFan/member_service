package cn.gaozheng.sales.service.impl;

import cn.gaozheng.sales.exception.SaleException;
import cn.gaozheng.sales.mapper.TblAdminMapper;
import cn.gaozheng.sales.model.po.TblAdmin;
import cn.gaozheng.sales.service.AdminService;
import cn.gaozheng.sales.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private TblAdminMapper tblAdminMapper;

    @Override
    public TblAdmin login(TblAdmin tblAdmin){
        if (!EmptyUtil.isNotEmpty(tblAdmin.getAdminName())){
            throw new SaleException("请输入用户名");
        }
        if (!EmptyUtil.isNotEmpty(tblAdmin.getAdminPassword())){
            throw new SaleException("请输入密码");
        }
        tblAdmin =  tblAdminMapper.getAdminWith(tblAdmin.getAdminName(),tblAdmin.getAdminPassword());
        if (tblAdmin == null)
        {
            throw new SaleException("用户名或密码错误");
        }
        tblAdmin.setAdminPassword(null);
        return tblAdmin;
    }

}
