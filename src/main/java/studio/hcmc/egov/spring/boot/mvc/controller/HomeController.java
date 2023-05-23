package studio.hcmc.egov.spring.boot.mvc.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import studio.hcmc.egov.spring.boot.mvc.domain.user.UserDomain;

@Controller
public class HomeController {
    /**
     * 홈 View
     */
    @RequestMapping(
            value = "/home.do",
            method = RequestMethod.GET
    )
    public String homeView(
            Model model,
            HttpSession session
    ) {
        final var user = (UserDomain) session.getAttribute("user");
        if (user == null) {
            // 사용자 정보가 세션에 존재하지 않음: 로그인 필요
            return "redirect:/user/signIn.do";
        }

        model.addAttribute("user", user);

        return "home";
    }
}
