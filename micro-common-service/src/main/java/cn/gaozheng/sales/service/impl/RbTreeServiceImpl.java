package cn.gaozheng.sales.service.impl;

import cn.gaozheng.sales.mapper.RbTreeMapper;
import cn.gaozheng.sales.mapper.TblDelegateMapper;
import cn.gaozheng.sales.mapper.UserMapper;
import cn.gaozheng.sales.model.po.RbTree;
import cn.gaozheng.sales.model.po.TblDelegate;
import cn.gaozheng.sales.model.po.User;
import cn.gaozheng.sales.service.RbTreeService;
import cn.gaozheng.sales.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RbTreeServiceImpl implements RbTreeService {
    @Autowired
    RbTreeMapper rbTreeMapper;
    @Autowired
    TblDelegateMapper tblDelegateMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public List<RbTree> getRbTreeWithLevel(String unames){
        if (EmptyUtil.isNotEmpty(unames)){
            return rbTreeMapper.getChildrenTrees(unames);
        }
        return null;
    }
    @Override
    public List<RbTree> getBrTreeDirectly(Long userId){
        User user = userMapper.selectByPrimaryKey(userId);
        List<RbTree>  rbTreeList =  getRbTreeWithLevel(user.getUserName());
        return rbTreeList;
    }
    public List<RbTree> getBrTreeIndirect(Long userId){
        List<RbTree> rbTreeList =  this.getBrTreeDirectly(userId);//直属粉丝
        List<RbTree> results = new ArrayList<>();
        TblDelegate tblDelegate = tblDelegateMapper.selectByPrimaryKey(userId);
        if (rbTreeList != null){
            getChildrenBrTree(rbTreeList,results,tblDelegate!= null?99999:1);
        }
        return results;
    }
    @Override
    public User fatherTree(Long userId){
        User user = userMapper.selectByPrimaryKey(userId);
        User fatherUser = rbTreeMapper.fatherTree(user.getUserName());
        return fatherUser;
    }

    private void getChildrenBrTree(List<RbTree> rbTrees,List<RbTree> allRbTree,int level){
        List<RbTree>  result =   getRbTreeWithLevel(getUnames(rbTrees));
        if (result != null){
            allRbTree.addAll(result);
        }
        level--;
        if (level > 0 && result != null && result.size() >0){
            getChildrenBrTree(result,allRbTree,level);
        }
        return ;
    }
    @Override
    public String getUnames(List<RbTree> rbTrees){
        String userNames = "";
        for (RbTree rbTree:rbTrees) {
            userNames += rbTree.getUid();
            if (rbTrees.indexOf(rbTree) != rbTrees.size() - 1 ){
                userNames+=",";
            }
        }
        return userNames;
    }
}
