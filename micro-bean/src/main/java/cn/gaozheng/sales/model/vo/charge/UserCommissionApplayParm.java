package cn.gaozheng.sales.model.vo.charge;

import cn.gaozheng.sales.exception.SaleException;
import cn.gaozheng.sales.utils.EmptyUtil;
import lombok.Data;

@Data
public class UserCommissionApplayParm {
    private Integer amount;
    private String alipayAccount;
    private String alipayRealname;
    public void checkException(){
        if (amount == null || amount <=0){
            throw new SaleException("金额错误");
        }
        if (!EmptyUtil.isNotEmpty(alipayAccount)){
            throw new SaleException("支付宝账号错误");
        }
        if (!EmptyUtil.isNotEmpty(alipayRealname)){
            throw new SaleException("支付宝账号名称错误");
        }
    }
}
