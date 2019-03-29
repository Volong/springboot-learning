package github.io.volong.controller;/**
 * @time 2019-03-29
 */

import github.io.volong.domain.User;
import github.io.volong.domain.UserParam;
import github.io.volong.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 *
 * @time 2019-03-29
 */

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/add")
    public String add (@Valid UserParam userParam, BindingResult bindingResult, ModelMap modelMap) {

        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            String errorMsg = "";
            for (ObjectError error : allErrors) {
                errorMsg = errorMsg + error.getDefaultMessage()+ " ";
            }
            modelMap.addAttribute("errorMsg", errorMsg);
            return "user/userAdd";
        }

        User user = userRepository.findByUserName(userParam.getUserName());

        if (user != null) {
            modelMap.addAttribute("errorMsg", "用户已存在");
            return "user/userAdd";
        }

        User user1 = new User();
        BeanUtils.copyProperties(userParam, user1);
        user1.setRegTime(new Date());
        userRepository.save(user1);
        return "redirect:/list";
    }
}
