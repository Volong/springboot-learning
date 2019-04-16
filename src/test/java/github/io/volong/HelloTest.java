package github.io.volong;

import org.junit.Assert;
import org.junit.Test;

/**
 * @time 2019-04-16
 *
 * 简单测试，不需要依赖 SpringBoot
 */
public class HelloTest {

    @Test
    public void hello(){
        String str = "hello";
        Assert.assertEquals("hello", str);
    }
}
