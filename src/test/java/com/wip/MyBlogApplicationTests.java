package com.wip;

import com.wip.utils.Commons;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBlogApplicationTests {

    @Test
    public void contextLoads() {
        String str = "123456";
        String str2 = str.substring(1);
        System.out.println(str2);
    }

}
