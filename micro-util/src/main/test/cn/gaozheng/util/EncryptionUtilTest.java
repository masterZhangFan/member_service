package cn.gaozheng.util;

import org.testng.Assert;
import org.testng.anno tions.Test;

public class EncryptionUtilTest {

    @Test
    public void testEncryptMD5() {
        Assert.assertEquals(EncryptionUtil.encryptMD5("123456"), "e10adc3949ba59abbe56e057f20f883e");
    }

    @Test
    public void testEncryptSHA256() {
        Assert.assertEquals(EncryptionUtil.encryptSHA256("123456"), "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92");
    }
}