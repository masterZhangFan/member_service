package cn.gaozheng.sales.mapper;

import cn.gaozheng.sales.common.SalesBaseMapper;
import cn.gaozheng.sales.model.po.RbTree;
import cn.gaozheng.sales.model.po.User;
import cn.gaozheng.sales.provider.UserProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface RbTreeMapper extends SalesBaseMapper<RbTree> {

    @SelectProvider(type = UserProvider.class,method = "getChildrenTrees")
    List<RbTree> getChildrenTrees(String unames);

    @Select("SELECT\n" +
            "\tt2.*\n" +
            "FROM\n" +
            "\trb_tree t1\n" +
            "JOIN `user` t2 ON t1.puid = t2.user_name\n" +
            "WHERE\n" +
            "\tt1.uid =  #{userName}")
    User fatherTree( String userName);
}