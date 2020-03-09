package cn.gaozheng.sales.service.impl;

import cn.gaozheng.sales.mapper.RbTreeMapper;
import cn.gaozheng.sales.mapper.TblDelegateMapper;
import cn.gaozheng.sales.mapper.UserMapper;
import cn.gaozheng.sales.model.po.RbTree;
import cn.gaozheng.sales.model.po.TblDelegate;
import cn.gaozheng.sales.model.po.User;
import cn.gaozheng.sales.service.RbTreeService;
import cn.gaozheng.sales.utils.EmptyUtil;
import org.apache.commons.lang3.StringUtils;
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
        getChildrenBrTree(rbTreeList,results,1);
        return results;
    }
    public List<RbTree> getBrTreeFission(Long userId){
        List<RbTree> rbTreeList =  this.getBrTreeIndirect(userId);//直属粉丝
        List<RbTree> results = new ArrayList<>();
        getChildrenBrTree(rbTreeList,results,999);
        return results;
    }
    @Override
    public User fatherTree(Long userId){
        User user = userMapper.selectByPrimaryKey(userId);
        User fatherUser = rbTreeMapper.fatherTree(user.getUserName());
        return fatherUser;
    }
    @Override
    public void genRbTree(String uids,Integer puid){
        String[] uidSplits = StringUtils.split(uids,",");
        for (String item:uidSplits) {
            RbTree rbTree =  rbTreeMapper.getWithUid(item);
            if (rbTree == null){
               User user = userMapper.getWithUserName(item);
               if (user != null){
                   rbTree = new RbTree();
                   rbTree.setPuid(puid);
                   rbTree.setUid(Integer.parseInt(item));
                   rbTree.setTime(user.getCreateTime());
                   rbTreeMapper.insert(rbTree);
               }
            }
        }
    }
    private void getChildrenBrTree(List<RbTree> rbTrees,List<RbTree> allRbTree,int level){
        List<RbTree>  result =  getRbTreeWithLevel(getUnames(rbTrees));
        if (result != null){
            allRbTree.addAll(result);
        }
        level--;
        if (level > 0 && result != null && result.size() >0){
            List<RbTree> nextRbTree =  rbTreeMapper.removeDelegateTrees(getUnames(result));
            getChildrenBrTree(nextRbTree,allRbTree,level);
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
