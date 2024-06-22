package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.example.domain.LoginUser;
import org.example.domain.User;
import org.example.service.UserDetailServiceImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DemoController {
    private final UserDetailServiceImpl userDetailService;

    @RequestMapping("/")
    public String root() {
        return "index";
    }

    @RequestMapping("/hello")
    public String hello(@AuthenticationPrincipal LoginUser loginUser) {
        System.out.println("login:" + loginUser);
        return "hello";
    }

    @RequestMapping("/login")
    public String login(@RequestBody User user) throws JsonProcessingException {
        return userDetailService.login(user);
    }

    @RequestMapping("/authed")
    public String authed() {
        return "authed";
    }

    @RequestMapping("/unauthed")
    public String unauthed(@AuthenticationPrincipal LoginUser loginUser) {
        return "unauthed";
    }
}
