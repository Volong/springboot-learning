package github.io.volong;

import github.io.volong.domain.UserSexEnum;
import github.io.volong.entity.UserEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;
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

    @Test
    public void testList() {
        ListOperations<String, String> listOperations = redisTemplate.opsForList();
        listOperations.leftPush("list", "yi");
        listOperations.leftPush("list", "hai");
        listOperations.leftPush("list", "long");

        List<String> list1 = listOperations.range("list", 0, 2);
        for (String s : list1) {
            System.out.println("key:" + s);
        }

        String list = listOperations.leftPop("list");
        Assert.assertEquals("long", list);

    }

    @Test
    public void testSet() {
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();

        setOperations.add("set", "yi");
        setOperations.add("set", "hai");
        setOperations.add("set", "long");

        Set<String> set = setOperations.members("set");

        for (String s : set) {
            System.out.println("value:" + s);
        }
    }


    @Test
    public void testDifference() {
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();

        setOperations.add("set1", "yi");
        setOperations.add("set1", "hai");
        setOperations.add("set1", "long");

        setOperations.add("set2", "yi");
        setOperations.add("set2", "volong");

        // set1 中与 set2 中不同的数据
        Set<String> difference = setOperations.difference("set1", "set2");

        for (String s : difference) {
            System.out.println("diff:" + s);
        }
    }

    @Test
    public void testUnion() {
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();

        setOperations.add("set1", "yi");
        setOperations.add("set2", "hai");
        setOperations.add("set2", "long");

        Set<String> union = setOperations.union("set1", "set2");

        for (String s : union) {
            System.out.println("union:" + s);
        }
    }

    @Test
    public void testZset() {

        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();

        zSetOperations.add("zset", "yi", 1);
        zSetOperations.add("zset", "hai", 2);
        zSetOperations.add("zset", "long", 3);
        zSetOperations.add("zset", "volong", 4);

        Set<String> zset = zSetOperations.range("zset", 0, 3);

        for (String s : zset) {
            System.out.println("value:" + s);
        }

        System.out.println("===========================================");

        // 获取 score 在 0~3 之间的数据
        Set<String> zset1 = zSetOperations.rangeByScore("zset", 0, 3);
        for (String s : zset1) {
            System.out.println("value:" + s);
        }
    }
}
