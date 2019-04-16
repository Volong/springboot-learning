package github.io.volong.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @time 2019-04-16
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testGetUser() {

        String user = userService.getUser();

        Assert.assertEquals("yhl", user);
    }

    @Test(expected = NullPointerException.class)
    public void testExcepton() {
        userService = null;
        userService.getUser();
    }


    @Test
    public void testAssert() {

        // 验证结果是否为空
        Assert.assertNotNull(userService.getUser());

        // 验证结果是否相等
        Assert.assertEquals("yhl", userService.getUser());

        // 验证条件是否成立
        Assert.assertFalse(1 + 1 > 2);

        // 验证对象是否相等
        Assert.assertNotSame(new UserService(), userService);

        String[] expectedOutput = {"apple", "mango", "grape"};
        String[] actualOutput = {"apple", "mango", "grape"};

        // 验证数组是否相同
        Assert.assertArrayEquals(expectedOutput, actualOutput);
    }
}
