package github.io.volong.controller;/**
 * @time 2019-03-29
 */

import github.io.volong.domain.User;
import github.io.volong.domain.UserParam;
import github.io.volong.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.awt.print.Pageable;
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

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/userAdd";
    }

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

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size) {

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        PageRequest pageRequest = new PageRequest(page, size, sort);

        Page<User> list = userRepository.findList(pageRequest);
        model.addAttribute("users", list);
        return "user/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model,Long id) {
        User user=userRepository.findById(id);
        model.addAttribute("user", user);
        return "user/userEdit";
    }

    @RequestMapping("edit")
    public String edit(@Valid UserParam userParam, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            String errorMsg = "";
            for (ObjectError error : allErrors) {
                errorMsg = errorMsg + error.getCode() + "-" + error.getDefaultMessage();
            }

            model.addAttribute("errorMsg", errorMsg);
            model.addAttribute("user", userParam);
            return "user/userEdit";
        }

        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        user.setRegTime(new Date());
        userRepository.save(user);
        return "redirect:/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        userRepository.delete(id);
        return "redirect:/list";
    }
}
