package workshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
public class SettingsController {

    private static final String POSTS_PER_PAGE = "postsPerPage";
    private static Integer POSTS_PER_PAGE_DEFAULT = 5;

    @GetMapping
    @ResponseBody
    public Integer set(HttpSession session, HttpServletResponse response) {
        Integer value = (Integer) session.getAttribute(POSTS_PER_PAGE);
        if (value == null) {
            value = POSTS_PER_PAGE_DEFAULT;
            session.setAttribute(POSTS_PER_PAGE, value);
        }
        response.addCookie(new Cookie("myCookie", LocalDateTime.now().toString()));
        return value;
    }
}
