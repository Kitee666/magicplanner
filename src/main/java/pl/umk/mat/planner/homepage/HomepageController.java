package pl.umk.mat.planner.homepage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

///TO JEST PRZYKŁADOWY CONTROLLER DLA HOMEPAGE, PODRZUCĘ JESZCZE TEMPLATKE
@Controller
public class HomepageController {

    @GetMapping("/")
    public String homePage() {
        return "index";
    }
}
