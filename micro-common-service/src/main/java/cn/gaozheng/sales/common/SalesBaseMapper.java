package cn.gaozheng.sales.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface SalesBaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
