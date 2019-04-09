package github.io.volong.controller;/**
 * @time 2019-03-29
 */

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @time 2019-03-29
 */

@RestController
public class UserController {

    @RequestMapping("/hello")
    @Cacheable(value = "helloCache")
    public String hello(String name) {
        System.out.println("没有走缓存！");
        return "hello " + name;
    }
}
