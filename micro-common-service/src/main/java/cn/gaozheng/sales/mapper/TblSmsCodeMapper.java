package cn.gaozheng.sales.mapper;

import cn.gaozheng.sales.common.SalesBaseMapper;
import cn.gaozheng.sales.model.po.TblSmsCode;
import org.apache.ibatis.annotations.Select;

public interface TblSmsCodeMapper extends SalesBaseMapper<TblSmsCode> {
    @Select("SELECT\n" +
            "\t*\n" +
            "FROM\n" +
            "\ttbl_sms_code\n" +
            "WHERE\n" +
            "\tsms_send_time IN (\n" +
            "\t\tSELECT\n" +
            "\t\t\tMAX(sms_send_time)\n" +
            "\t\tFROM\n" +
            "\t\t\ttbl_sms_code\n" +
            "\t\tWHERE\n" +
            "\t\t\tphone_number = #{phoneNumber}\n" +
            "\t)\n" +
            "AND phone_number = #{phoneNumber} limit 1")
    TblSmsCode latestSmsCode(String phoneNumber);
}