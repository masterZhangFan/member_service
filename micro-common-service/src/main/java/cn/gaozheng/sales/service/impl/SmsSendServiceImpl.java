package cn.gaozheng.sales.service.impl;

import cn.gaozheng.sales.exception.SaleException;
import cn.gaozheng.sales.mapper.TblSmsCodeMapper;
import cn.gaozheng.sales.model.po.TblSmsCode;
import cn.gaozheng.sales.service.SmsSendService;
import cn.gaozheng.sales.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SmsSendServiceImpl implements SmsSendService {
    @Autowired
    TblSmsCodeMapper tblSmsCodeMapper;

    @Override
    public void insertSmsInfo(String phoneNumber,String code){
        TblSmsCode tblSmsCode = new TblSmsCode();
        tblSmsCode.setPhoneNumber(phoneNumber);
        tblSmsCode.setSmsCode(code);
        tblSmsCode.setSmsSendTime(new Date());
        tblSmsCodeMapper.insert(tblSmsCode);
    }
    private static String codeMsg  = "验证码错误";
    public boolean checkSmsCode(String phoneNumbe,String code){
        TblSmsCode tblSmsCode = tblSmsCodeMapper.latestSmsCode(phoneNumbe);
        if (tblSmsCode == null){
            throw new SaleException(codeMsg);
        }
        if (!tblSmsCode.getSmsCode().equals(code)){
            throw new SaleException(codeMsg);
        }
        long nd = 1000 * 24 * 60 * 60;

        long nh = 1000 * 60 * 60;

        long nm = 1000 * 60;

        long diff = new Date().getTime() - tblSmsCode.getSmsSendTime().getTime();
        long min =  diff % nd % nh / nm;
        if (min>=5){
            throw new SaleException("验证码已过期");
        }
        return true;
    }
}
