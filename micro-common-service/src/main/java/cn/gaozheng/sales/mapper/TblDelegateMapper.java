package cn.gaozheng.sales.mapper;

import cn.gaozheng.sales.common.SalesBaseMapper;
import cn.gaozheng.sales.model.po.TblDelegate;
import cn.gaozheng.sales.model.vo.DelegateListM;
import cn.gaozheng.sales.model.vo.DelegateListParm;
import cn.gaozheng.sales.model.vo.UserInfo;
import cn.gaozheng.sales.provider.DelegateProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface TblDelegateMapper extends SalesBaseMapper<TblDelegate> {
    @SelectProvider(type = DelegateProvider.class,method = "delegateList")
    List<DelegateListM> delegateList( DelegateListParm delegateListParm);

    @Select("SELECT *FROM tbl_delegate WHERE user_id  in(SELECT parent_delegate_id FROM tbl_delegate WHERE tbl_delegate.user_id = 10 )LIMIT 1 ")
    TblDelegate getFatherDelegate(Long user_id);


}