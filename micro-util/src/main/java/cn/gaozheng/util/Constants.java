package cn.gaozheng.util;

import java.math.BigDecimal;

/**
 * @author Cheng Bo
 * @version 1.0
 */
public interface Constants {

    /**
     * 手机号码
     * */
    String PATTERN_PHONE_NUMBER = "0?(13|14|15|18|17|19)[0-9]{9}";

    /**
     * 手机号码，3-4位区号，7-8位直播号码，1－4位分机号
     */
    String PATTERN_PHONE_NUMBER_PLUS = "((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)";

    /**
     * 车牌号正则
     */
    String PATTERN_VEHICLE_NUMBER = "([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}(([0-9]" +
            "{5}[DF])|(DF[0-9]{4})))|([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳]{1})";

    int DEFAULT_PAGE_MIN = 1;
    int DEFAULT_PAGE_MAX = 100;

    Integer STATUS_WAITING = 1;
    Integer STATUS_CALLING = 2;
    Integer STATUS_CALLING_TIME_OUT = 3;

    BigDecimal BD_HUNDRED = BigDecimal.valueOf(100);

    String DEFAULT_DATA_SOURCE = "dataSource";
    String DRUID_DATA_SOURCE_1 = "druidDataSource_1";

    String BILL_LADING_HISTORY_TABLE_PREFIX = "tbl_bill_lading_history_";

    String REFUND_TABLE_PREFIX = "tbl_charge_refund";
    String REFUND_HISTORY_TABLE_PREFIX = "tbl_charge_refund_";

    String COMMON_SEPARATOR = "-";
    String COMMON_SEPARATOR_1 = ",";

    double WEIGHT_OF_PAGE = 0.05;
    int AUDIT_DAY_BY_MONTH = 25;

}
