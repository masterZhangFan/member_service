package cn.gaozheng.sales.service;

import cn.gaozheng.sales.model.po.TblDelegate;
import cn.gaozheng.sales.model.po.TblDelegateType;
import cn.gaozheng.sales.model.po.User;
import cn.gaozheng.sales.model.vo.DelegateListM;
import cn.gaozheng.sales.model.vo.DelegateListParm;
import com.github.pagehelper.PageInfo;


import java.util.List;

public interface DelegateService {
    List<TblDelegateType> getDelegateTypes();

    List<TblDelegateType> getDelegateTypesByUserId(Long userId);

    Boolean setDelegate(TblDelegate tblDelegate);

    PageInfo<DelegateListM> delegateList( DelegateListParm delegateListParm);

    List<DelegateListM> delegateListNotOnPage(Long userId);

    DelegateListM delegate(Long userId);

    /**
     * 获取某用户的代理
     * @param userId
     * @return
     */
    User getDelegateOfUser(Long userId);

    /**
     * 获取某个用户的父级代理
     * @param userId
     * @return
     */
    User fatherDelegate(Long userId);


}
