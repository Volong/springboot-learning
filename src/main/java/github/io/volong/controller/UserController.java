package github.io.volong.controller;/**
 * @time 2019-03-29
 */

import github.io.volong.entity.User;
import github.io.volong.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.midi.Soundbank;
import java.util.List;

/**
 * @time 2019-03-29
 */

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * helloCache 为缓存的名称，value 为返回值。
     * 如果没有从缓存中查询到对应的数据，则继续执行。
     *
     * @param name
     * @return
     */
    @Cacheable(value = "helloCache")
    @RequestMapping("/hello")
    public String hello(String name) {
        System.out.println("没有走缓存！");
        return "hello " + name;
    }

    /**
     *
     * condition: 触发条件。
     * 只有满足条件的才会走缓存。默认表示所有都走缓存，支持 SpEL
     *
     * @param name
     * @return
     */
    @Cacheable(value = "condition", condition = "#name.length() <= 4")
    @RequestMapping("/condition")
    public String condition(String name) {
        System.out.println("没有走缓存");
        return "hello" + name;
    }

    /**
     *
     * key: 缓存的 key。
     * 为空则按照方法的参数进行组合，如果指定，则需要按照 SpEL 表达式进行编写
     *
     * @param nickname
     * @return
     */
    @Cacheable(value = "usersCache", key = "#nickname", condition = "#nickname.length() >= 6")
    @RequestMapping("/getUsers")
    public List<User> getUsers(String nickname) {
        List<User> byNickname = userRepository.findByNickname(nickname);
        System.out.println("执行了数据库操作");
        return byNickname;
    }


    @CachePut(value = "userCache", key = "#nickname")
    @RequestMapping("/getPutUsers")
    public List<User> getPutUsers(String nickname) {
        List<User> byNickname = userRepository.findByNickname(nickname);
        System.out.println("执行了数据库操作");
        return byNickname;
    }

    /**
     * allEntries 为 boolean 类型，表示是否需要
     *
     * @param nickname
     * @return
     */
    @CacheEvict(value = "usersCache", allEntries = true)
    @RequestMapping("/allEntries")
    public List<User> allEntries(String nickname) {
        List<User> byNickname = userRepository.findByNickname(nickname);
        System.out.println("执行了数据库操作");
        return byNickname;
    }


}
