package cn.gaozheng.sales.mapper;

import cn.gaozheng.sales.common.SalesBaseMapper;
import cn.gaozheng.sales.model.po.User;
import cn.gaozheng.sales.model.vo.Banlance;
import cn.gaozheng.sales.model.vo.Fan;
import cn.gaozheng.sales.provider.UserProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper extends SalesBaseMapper<User> {
    @Select("select *from user where long_name = #{phoneNumber}")
    User getUserWithPhoneNumber(String phoneNumber);

    @Select("select *from user where wx_open_id = #{openId}")
    User getUserWithOpenId(String openId);

    @Update("update user set wx_open_id  = null where wx_open_id  = #{openId}")
    Integer clearOpenId(String openId);

    @Select("SELECT SUM(IFNULL(total,0)-IFNULL(settlement,0)-IFNULL(auditing,0)) as cash FROM `user_commission` t1 JOIN `user` t2\n" +
            "on t1.uid =  t2.user_name\n" +
            "WHERE t2.user_id = #{userId}")
    Banlance getAllSettlementPrice(Long userId);


    @Select("select vf.agent_id,\n" +
            " convert(fa.balance/1000000,decimal(15,2)) as call_balance,\n" +
            " us.create_time,\n" +
            " us.valid_date,\n" +
            " fa.price as shopping_balance\n" +
            "FROM\n" +
            "\t`user` AS us,\n" +
            "\tfield_account AS fa,\n" +
            "\tv_field AS vf\n" +
            "WHERE\n" +
            "\tus.field_id = vf.field_id\n" +
            "AND us.field_id = fa.field_id\n" +
            "AND us.user_id = #{userId}\n" +
            "LIMIT 1")
    Banlance getBanlance(Long userId);

    @SelectProvider(type = UserProvider.class,method = "getFans")
    List<Fan> getFans(String names);

    @Update("UPDATE `rb_income` SET rank='2' WHERE `uid` = #{uid}")
    Integer setMemberLevel(String uid);
}