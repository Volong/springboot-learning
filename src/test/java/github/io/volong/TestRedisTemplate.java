package github.io.volong;

import github.io.volong.domain.UserSexEnum;
import github.io.volong.entity.UserEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @time 2019-04-09
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRedisTemplate {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testString() {

        redisTemplate.opsForValue().set("name", "yihailong");

        Assert.assertEquals("yihailong", redisTemplate.opsForValue().get("name"));
    }

    @Test
    public void testObj() throws InterruptedException {

        UserEntity entity = new UserEntity("yihailong", "1234", UserSexEnum.MAN);
        redisTemplate.opsForValue().set("expire", entity, 100, TimeUnit.MILLISECONDS);

        Thread.sleep(1000);

        Assert.assertFalse(redisTemplate.hasKey("expire"));
    }

    @Test
    public void testDelete() {
        redisTemplate.opsForValue().set("deletekey", "yihailong");

        Assert.assertTrue(redisTemplate.hasKey("deletekey"));

        redisTemplate.delete("deletekey");

        Assert.assertFalse(redisTemplate.hasKey("deletekey"));
    }



}
