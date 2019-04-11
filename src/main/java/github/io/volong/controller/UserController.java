package github.io.volong.controller;/**
 * @time 2019-03-29
 */

import github.io.volong.entity.User;
import github.io.volong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * allEntries 为 boolean 类型，表示是否需要清除缓存中的所有元素。
     *
     * 如下的示例将会清除名为 usersCache 的缓存中所有的元素
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

    /**
     * 清除操作默认是在对应方法成功执行之后触发的，即方法如果因为抛出异常而未能成功返回时也不会触发清除操作。
     *
     * beforeInvocation 可以改变触发清除操作的时间，当我们指定该属性值为 true 时，Spring 会在调用该方法之前清除缓存中的指定元素。
     *
     */
    @RequestMapping("/beforeInvocation")
    @CacheEvict(value="usersCache", allEntries=true, beforeInvocation=true)
    public void beforeInvocation() {
        throw new RuntimeException("test beforeInvocation");
    }

    @RequestMapping(value = "/setSession")
    public Map<String, Object> setSession (HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        request.getSession().setAttribute("message", request.getRequestURL());
        map.put("request Url", request.getRequestURL());
        return map;
    }

    @RequestMapping(value = "/getSession")
    public Object getSession (HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("message"));
        return map;
    }

    @RequestMapping(value = "/login")
    public String login (HttpServletRequest request,String userName,String password) {
        User user = userRepository.findByUserName(userName);
        if (user != null && user.getPassword().equals(password)) {
            request.getSession().setAttribute("user", user);
            return "success";
        }
        return "failure";
    }

    @RequestMapping("/index")
    public String index (HttpServletRequest request) {
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            return "login";
        }
        return "index";
    }
}
