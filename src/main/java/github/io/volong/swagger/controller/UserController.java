package github.io.volong.swagger.controller;

import github.io.volong.swagger.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口", description = "提供与用户相关的 Rest API")
public class UserController {

    @PostMapping("/add")
    @ApiOperation("新增用户接口")
    public boolean addUser(@RequestBody User user) {
        return false;
    }

    @GetMapping("/find/{id}")
    public User findById(@PathVariable("id") int id) {
        return new User();
    }

    @PutMapping("/update")
    public boolean update(@RequestBody User user) {
        return true;
    }

    @ApiIgnore // 这个接口不在 Swagger 中展示
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") int id) {
        return true;
    }
}