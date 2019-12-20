package cn.gaozheng.sales.provider;

import cn.gaozheng.sales.model.vo.DelegateListParm;
import cn.gaozheng.sales.utils.EmptyUtil;

public class DelegateProvider {
    public String delegateList( DelegateListParm delegateListParm){
        String sql = "SELECT\n" +
                "\tt1.*, IFNULL(t2.cash, 0) as cash,t_user.nickname,t_user.phone,t_delegate.*,t_delegate_type.*\n" +
                "FROM\n" +
                "\ttbl_delegate t_delegate\n" +
                "JOIN `user` t_user ON t_user.user_id = t_delegate.user_id\n" +
                "JOIN tbl_delegate_type t_delegate_type ON t_delegate.delegate_type_id = t_delegate_type.delegate_type_id\n" +
                "LEFT JOIN (\n" +
                "\tSELECT\n" +
                "\t\tus.user_id,\n" +
                "\t\tSUM(\n" +
                "\t\t\tCONVERT (\n" +
                "\t\t\t\tfa.balance / 1000000,\n" +
                "\t\t\t\tDECIMAL (15, 2)\n" +
                "\t\t\t)\n" +
                "\t\t) AS shopping_balance,\n" +
                "\t\tSUM(fa.price) AS call_balance\n" +
                "\tFROM\n" +
                "\t\t`user` AS us,\n" +
                "\t\tfield_account AS fa,\n" +
                "\t\tv_field AS vf\n" +
                "\tWHERE\n" +
                "\t\tus.field_id = vf.field_id\n" +
                "\tAND us.field_id = fa.field_id\n" +
                "\tGROUP BY\n" +
                "\t\tus.user_id\n" +
                ") AS T1 ON T1.user_id = t_user.user_id\n" +
                "LEFT JOIN (\n" +
                "\tSELECT\n" +
                "\t\tt2.user_id,\n" +
                "\t\tSUM(IFNULL(total, settlement)) AS cash\n" +
                "\tFROM\n" +
                "\t\t`user_commission` t1\n" +
                "\tJOIN `user` t2 ON t1.uid = t2.user_name\n" +
                "\tGROUP BY\n" +
                "\t\tt2.user_id\n" +
                ") AS T2 ON T1.user_id = T2.user_id\n" +
                "WHERE 1 = 1 \n" ;
        if (delegateListParm.getDelegateTypeId()!= null && delegateListParm.getDelegateTypeId() > 0){
            sql+= " AND t_delegate_type.delegate_type_id = " +delegateListParm.getDelegateTypeId();
        }
        if (delegateListParm.getParentDelegateId() != null && delegateListParm.getParentDelegateId() > 0){
            sql+= " AND t_delegate.parent_delegate_id = " +delegateListParm.getParentDelegateId();
        }
        if (delegateListParm.getUserId() != null && delegateListParm.getUserId() > 0){
            sql+= " AND t_delegate.user_id = " +delegateListParm.getUserId();
        }
        if (EmptyUtil.isNotEmpty(delegateListParm.getSearchText())){
            String like = "'%"+delegateListParm.getSearchText()+"%'";
            sql+= " AND (t_user.mobile like "+ like+" or t_user.long_name like "+ like+" OR phone like "+like+")\n";
        }
        return sql;
    }
}
