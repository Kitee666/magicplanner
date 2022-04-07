package pl.umk.mat.planner.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String HomePage() {
        return "/admin/home";
    }

    @GetMapping("/admin/inputy")
    public String InputPage() {
        return "/admin/inputy";
    }
}
