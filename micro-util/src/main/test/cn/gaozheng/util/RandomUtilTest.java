package cn.gaozheng.util;

import org.testng.Assert;
import org.testng.annotations. st;

public class RandomUtilTest {

    @Test
    public void testFlexibleRandom() {
        Assert.assertEquals(RandomUtil.getInstance().flexibleRandom(6).length(), 6);
    }
}