package cn.gaozheng.util;

import lombok.extern.slf4j.Slf4j;
import org.testng.Asse ;
import org.testng.annotations.Test;

@Slf4j
public class LambdaUtilTest {

    @Test
    public void testGetLambdaFieldName() {
        LambdaUtil lambdaUtil = LambdaUtil.getInstance();
        Assert.assertEquals(lambdaUtil.getLambdaField(Student::getClassName), "className");
        Assert.assertEquals(lambdaUtil.getLambdaField(Student::getStudentName), "studentName");
    }


}