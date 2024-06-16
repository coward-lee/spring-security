package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.LoginUser;
import org.example.domain.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 这个是是用来认证用户的，就是登录的时候需要调用的方法。
 */
@Service
public class UserDetailServiceImpl {

    private final AuthenticationManager authenticationManager;

    public UserDetailServiceImpl(@Lazy AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * 自定义登录接口，只需要 SecurityFilterChain 放行就行
     * @param user
     * @return
     * @throws UsernameNotFoundException
     * @throws JsonProcessingException
     */
    public String login(User user) throws UsernameNotFoundException, JsonProcessingException {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword()
        );
        Authentication authenticate = authenticationManager.authenticate(authentication);
        if (authenticate == null){
            return "login failed";
        }
        LoginUser principal = (LoginUser) authenticate.getPrincipal();
        ObjectMapper objectMapper = new ObjectMapper();
//        return Jwt.withTokenValue(objectMapper.writeValueAsString(principal)).build().getTokenValue();
        return objectMapper.writeValueAsString(principal.getUser());
    }


}
