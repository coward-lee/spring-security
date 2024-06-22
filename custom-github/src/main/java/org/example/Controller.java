package org.example;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {
    @RequestMapping("/login/oauth2/code/github")
    public ResponseEntity<String> str(@RequestParam("code") String code,
                      @RequestParam("state") String state) {
        String url = "https://github.com/login/oauth/access_token";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url, GithubConfig.of(code, state), String.class);

        return stringResponseEntity;
    }

    @RequestMapping("")
    public String index() {
        return "index";
    }
}
