package pl.umk.mat.planner.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

///TO JEST PRZYKŁADOWY CONTROLLER DLA HOMEPAGE, PODRZUCĘ JESZCZE TEMPLATKE
@Controller
public class HomepageController {
    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

}
