package cn.gaozheng.mini.util;

import com.beust.jcommander.internal.Lists;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

import static cn.gaozheng.util.Constants.AUDIT_DAY_BY_MONTH;

public class DateUtilTest {

    @Test
    public void testGetHourList() {
        Assert.assertEquals(String.format("%02d", 1), "01");
    }

    @Test
    public void testSumOfList() {
//        Assert.assertEquals(Stream.<TblOrder>empty().mapToDouble(TblOrder::getWeight).sum(), NumberUtil.DOUBLE_ZERO);
    }

    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][] {
                { 2019, 5, 27, 2019, 9 ,22, new Integer[]{6, 7, 8, 9} },
                { 2019, 4, 22, 2019, 10 , 27, new Integer[]{4, 5, 6, 7, 8, 9, 10, 11} },
                { 2018, 12, 28, 2019, 4 , 21, new Integer[]{1, 2, 3, 4} },
                { 2018, 12, 28, 2019, 4 , 27, new Integer[]{1, 2, 3, 4, 5} },
        };
}

    @Test(dataProvider = "data-provider")
    public void testBetweenByCustomSplitMonth(Integer sYear, Integer sMonth, Integer sDay,
                                              Integer eYear, Integer eMonth, Integer eDay, Integer[] expect) {
        LocalDate start = LocalDate.of(sYear, sMonth, sDay);
        LocalDate end =  LocalDate.of(eYear, eMonth, eDay);
        List<Integer> expectResult = Lists.newArrayList(expect);
        List<Integer> result = DateUtil.betweenByCustomSplitMonth(start, end, AUDIT_DAY_BY_MONTH);
        Assert.assertEquals(result, expectResult);
    }
}