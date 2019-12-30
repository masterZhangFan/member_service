package cn.gaozheng.sales.provider;

import cn.gaozheng.sales.model.vo.DelegateListParm;
import cn.gaozheng.sales.model.vo.UserInfo;
import cn.gaozheng.sales.model.vo.member.MemberListParm;
import cn.gaozheng.sales.utils.EmptyUtil;

import java.util.List;

public class UserProvider {
    public String getFans(String names){
        String sql = "SELECT\n" +
                "\tincome.rank,\n" +
                "\tincome. STATUS,\n" +
                "\tus.user_id,"+
                "\tus.nickname,\n" +
                "\tus.long_name,\n" +
                "\tus.icon,\n" +
                "\tus.create_time,\n" +
                "\tIFNULL(t1.cash_back_money,0) as member_cash_back,\n" +
                "\tIFNULL(t2.cash_back_money,0)as delegate_cash_back\n" +
                "FROM\n" +
                "\t`user` AS us\n" +
                "LEFT JOIN rb_income AS income ON income.uid = us.user_name\n" +
                "LEFT JOIN(\n" +
                "\tSELECT cash_back_from_user_id,SUM(cash_back_money) as cash_back_money FROM tbl_cash_back  WHERE cash_back_type = 1  GROUP BY cash_back_from_user_id\n" +
                ")\n" +
                "t1 on t1.cash_back_from_user_id = us.user_id\n" +
                "LEFT JOIN(\n" +
                "\tSELECT cash_back_from_user_id,SUM(cash_back_money) as cash_back_money FROM tbl_cash_back  WHERE cash_back_type = 2   GROUP BY cash_back_from_user_id\n" +
                ")\n" +
                "t2 on t2.cash_back_from_user_id = us.user_id\n" +
                "WHERE\n" +
                "\tus.user_name IN (" +names+")"+"\n"+
                "ORDER BY\n" +
                "\tus.create_time DESC\n" +
                "LIMIT 0,\n" +
                " 9999;\n";
        return sql;
    }
    public String memberInfos( MemberListParm req){
        String sql = "SELECT\n" +
                "\tt1.*, t_member.rank as member_level ,IFNULL(t2.cash, 0) as cash,t_user.nickname,t_user.phone\n" +
                "FROM\n" +
                "\trb_income t_member\n" +
                "JOIN `user` t_user ON t_user.user_name = t_member.uid\n" +
                "LEFT JOIN (\n" +
                "\tSELECT\n" +
                "\t\tus.user_id,\n" +
                "\t\tSUM(\n" +
                "\t\t\tCONVERT (\n" +
                "\t\t\t\tfa.balance / 1000000,\n" +
                "\t\t\t\tDECIMAL (15, 2)\n" +
                "\t\t\t)\n" +
                "\t\t) AS call_balance,\n" +
                "\t\tSUM(fa.price) AS shopping_balance\n" +
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
        if (req.getMemberLevel()!= null && req.getMemberLevel() >0){
            sql+="\tand t_member.rank =" + req.getMemberLevel();
        }
        if (req.getUserId()!= null && req.getUserId() >0 ){
            sql+= "\tand t_user.user_id =  "+ req.getUserId();
        }
        if (EmptyUtil.isNotEmpty(req.getSearchText())){
            sql+= "\tand t_user.phone like  '%"+ req.getSearchText()+"%'";
        }
        return sql;
    }

    public String memberInfoNotIncludeDelegate(String userNames,String searchText){
        String sql = "SELECT\n" +
                "\tt1.*, t_member.rank as member_level ,IFNULL(t2.cash, 0) as cash,t_user.nickname,t_user.phone\n" +
                "FROM\n" +
                "\trb_income t_member\n" +
                "JOIN `user` t_user ON t_user.user_name = t_member.uid\n" +
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
                ") AS T2 ON T1.user_id = T2.user_id and T2.user_id not in(SELECT user_id from tbl_delegate)\n" +
                "WHERE 1 = 1 \n" ;
       if (EmptyUtil.isNotEmpty(searchText)){
            sql+= "\tand t_user.phone like  '%"+ searchText+"%'";
        }
        if (EmptyUtil.isNotEmpty(userNames)){
            sql+= "\tand t_user.user_name in("+userNames+")";
        }
        return sql;
    }
    public String getChildrenTrees(String unames){
        String sql =  "select uid from rb_tree where puid in ("+unames+") AND uid not in (SELECT user_id from tbl_delegate)";
        return sql;
    }


}
