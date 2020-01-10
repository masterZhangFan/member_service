package cn.gaozheng.sales.mapper;

import cn.gaozheng.sales.common.SalesBaseMapper;
import cn.gaozheng.sales.model.po.TblCashBack;
import org.apache.ibatis.annotations.Select;

public interface TblCashBackMapper extends SalesBaseMapper<TblCashBack> {
    @Select("SELECT SUM(cash_back_money) FROM tbl_cash_back WHERE cash_back_to_user_id =  #{fatherUserId} and cash_back_type = 3 and order_id in(\n" +
            "SELECT order_id from tbl_cash_back WHERE cash_back_to_user_id = #{cash_back_to_user_id} and cash_back_type =3\n" +
            ")\n")
    Double cashBackMoneyOfDelegate(Long fatherUserId,Long cash_back_to_user_id);
}