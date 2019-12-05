package cn.gaozheng.sales.service;

import cn.gaozheng.sales.model.po.RbTree;
import cn.gaozheng.sales.model.po.User;

import java.util.List;

public interface RbTreeService {
    /**/
    List<RbTree> getRbTreeWithLevel( String unames);
    /*直属的*/
    List<RbTree> getBrTreeDirectly(Long userId);
    /*间接粉丝*/
    List<RbTree> getBrTreeIndirect(Long userId);
    /*根据数组获取用户ID*/
    String getUnames(List<RbTree> rbTrees);
    //父节点
    User fatherTree( Long userId);
}
