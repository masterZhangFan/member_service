package cn.gaozheng.mini.util;

import cn.gaozheng.sales.DurationType;
import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static cn.gaozheng.util.Constants.AUDIT_DAY_BY_MONTH;

/**
 * DateUtil
 *
 * @author Cheng Bo
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtil {

    private static List<String> hourList = null;

    public static List<String> betweenByDuration(LocalDate start, LocalDate end, DurationType durationType) {
        Preconditions.checkNotNull(start);
        Preconditions.checkNotNull(end);
        Preconditions.checkNotNull(durationType);
        if (durationType.equals(DurationType.SINGLE_HOUR)) {
            return getHourList();
        }

        List<String> resultList = new LinkedList<>();
        while (start.compareTo(end) <= 0) {
            resultList.add(cn.gaozheng.util.DateUtil.getInstance().format(start, durationType.getNativeFormat()));
            switch (durationType) {
                case DAY:
                    start = start.plusDays(1);
                    break;
                case WEEK:
                    start = start.plusWeeks(1);
                    break;
                case MONTH:
                    start = start.plusMonths(1);
                    break;
                case YEAR:
                    start = start.plusYears(1);
                    break;
            }
        }
        return resultList;
    }
    public static List<Integer> betweenByCustomSplitMonth(LocalDateTime start, LocalDateTime end, int splitDay) {
        return betweenByCustomSplitMonth(start.toLocalDate(), end.toLocalDate(), splitDay);
    }

    public static List<Integer> betweenByCustomSplitMonth(LocalDate start, LocalDate end, int splitDay) {
        Preconditions.checkNotNull(start);
        Preconditions.checkNotNull(end);
        List<Integer> resultList = new LinkedList<>();

        if (start.getDayOfMonth() <= splitDay) {
            resultList.add((start.getMonthValue()));
        }
        start = start.plusMonths(1);

        while (start.getMonth().compareTo(end.getMonth()) <= 0) {
            resultList.add(start.getMonthValue());
            start = start.plusMonths(1);
        }

        if (end.getDayOfMonth() > splitDay) {
            resultList.add(end.plusMonths(1).getMonthValue());
        }
        return resultList;
    }

    public static int getCustomYear(LocalDate localDate) {
        if (localDate.getMonthValue() == 12 && localDate.getDayOfMonth() > AUDIT_DAY_BY_MONTH) {
            return localDate.getYear() + 1;
        }
        return localDate.getYear();
    }

    public static int getCustomYear(LocalDateTime localDateTime) {
        return getCustomYear(localDateTime.toLocalDate());
    }

    private static List<String> getHourList() {
        if (CollectionUtils.isEmpty(hourList)) {
            hourList = new LinkedList<>();
            for (int i = 0; i < 24; i++) {
                hourList.add(String.format("%02d", i));
            }
        }
        return hourList;
    }


}
