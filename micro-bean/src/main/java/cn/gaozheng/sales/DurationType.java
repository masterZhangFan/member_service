package cn.gaozheng.sales;

import cn.gaozheng.util.DateUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * DurationType
 *
 * @author Cheng Bo
 * @version 1.0
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum DurationType {
    DAY(1,"%Y-%m-%d",  DateUtil.DEFAULT_FORMAT_PATTERN_DAY),
    WEEK(2, "%Y-%u", DateUtil.DEFAULT_FORMAT_PATTERN_WEEK),
    MONTH(3, "%Y-%m", DateUtil.DEFAULT_FORMAT_PATTERN_MONTH),
    YEAR(4, "%Y", DateUtil.DEFAULT_FORMAT_PATTERN_YEAR),

    SINGLE_HOUR(5, "%H", StringUtils.EMPTY);

    private int value;

    private String sqlFormat;

    private String nativeFormat;

}
