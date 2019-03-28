package github.io.volong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExampleController {

    @RequestMapping("/example")
    public String example(ModelMap map) {
        map.addAttribute("userName", "Volong");
        return "example";
    }
}
