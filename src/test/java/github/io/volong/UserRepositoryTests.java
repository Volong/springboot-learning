package github.io.volong;

import github.io.volong.domain.User;
import github.io.volong.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

    @Resource
    private UserRepository userRepository;

    @Test
    public void testRepository() {

        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(new Date());

        // userRepository.save(new User("aa", "aa@126.com", "aa", "aa123456", formattedDate));
        // userRepository.save(new User("bb", "bb@126.com", "bb", "bb123456", formattedDate));
        // userRepository.save(new User("cc", "cc@126.com", "cc", "cc123456", formattedDate));

        Assert.assertEquals(3, userRepository.findAll().size());
        Assert.assertEquals("bb", userRepository.findByUserNameOrEmail("bb", "bb@126.com").getNickName());

        User aa1 = userRepository.findByUserName("aa1");
        Assert.assertNull(aa1);

    }

    /**
     * 基本查询方法，这些都是 JPA 内置的
     */
    @Test
    public void testBaseQuery() {

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        User user=new User("ff", "ff123456","ff@126.com", "ff",  formattedDate);

        List<User> all = userRepository.findAll();
        Assert.assertTrue(all.size() > 0);

        Optional<User> one = userRepository.findOne(Example.of(user));
        Assert.assertFalse(one.isPresent());

        User save = userRepository.save(user);
        Assert.assertNotNull(save);

        userRepository.delete(user);

        long count = userRepository.count();
        Assert.assertEquals(3, count);

        boolean exists = userRepository.exists(Example.of(user));
        Assert.assertFalse(exists);
    }


    /**
     * 分页查询
     */
    @Test
    public void testPageQuery() {

        /*
          如果将 page 从 1 开始赋值，可能会导致异常 : [Page 2 of 1 containing UNKNOWN instances](https://jira.spring.io/browse/DATAJPA-225)
          因为 page 是从 0 开始计数的，page = 1 表示第二页，但是如果数据少于两页，则会报此异常
         */
        int page = 0, size = 10;
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<User> all = userRepository.findALL(pageable);

        System.out.println("all:" + all);

        System.out.println(all.toString());

        Page<User> testName = userRepository.findByNickName("testName", pageable);
        List<User> content1 = testName.getContent();
        System.out.println(content1);

    }
}
