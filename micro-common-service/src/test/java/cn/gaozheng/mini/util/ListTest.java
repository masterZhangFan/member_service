package cn.gaozheng.mini.util;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cheng Bo
 * @version 1.0
 */
@Slf4j
public class ListTest {

    @Test
    public void listTest() {
        List<String> list1 = new ArrayList<String>();
        list1.add("A");
        list1.add("B");
        list1.add("C");

        List<String> list2 = new ArrayList<String>();
        list2.add("C");
        list2.add("B");
        list2.add("D");
        // 差集
        list1.removeAll(list2);
        log.info("{}", list1);
        Lists.newArrayList(list1);
    }

}
