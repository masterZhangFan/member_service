package cn.gaozheng.sales.mapper;

import cn.gaozheng.sales.common.SalesBaseMapper;
import cn.gaozheng.sales.model.po.TblAdmin;
import org.apache.ibatis.annotations.Select;

public interface TblAdminMapper extends SalesBaseMapper<TblAdmin> {
    @Select("select *from tbl_admin where admin_name = #{admin_name} and admin_password =  #{admin_password}")
    TblAdmin getAdminWith(String admin_name,String admin_password);
}