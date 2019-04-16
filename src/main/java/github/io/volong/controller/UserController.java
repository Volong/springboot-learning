package github.io.volong.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @time 2019-04-16
 */
@RestController
public class UserController {

    @RequestMapping(value = "/user", produces = "application/json")
    public String getUser() {
        return "yhl";
    }
}
