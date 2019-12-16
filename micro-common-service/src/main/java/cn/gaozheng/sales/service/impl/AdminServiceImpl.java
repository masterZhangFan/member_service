package cn.gaozheng.sales.service.impl;

import cn.gaozheng.sales.exception.SaleException;
import cn.gaozheng.sales.mapper.TblAdminMapper;
import cn.gaozheng.sales.model.po.TblAdmin;
import cn.gaozheng.sales.service.AdminService;
import cn.gaozheng.sales.service.TokenUtilsServer;
import cn.gaozheng.sales.utils.EmptyUtil;
import cn.gaozheng.sales.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private TblAdminMapper tblAdminMapper;
    @Autowired
    TokenUtilsServer tokenUtilsServer;

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
        String token = tokenUtilsServer.generateTokenString(tblAdmin.getAdminId(), TimeUtils.formatDateString(new Date()));
        tblAdmin.setAdminPassword(null);
        tblAdmin.setAdminToken(token);
        tblAdmin.setLoginTime(new Date());
        tblAdminMapper.updateByPrimaryKey(tblAdmin);
        return tblAdmin;
    }

}
