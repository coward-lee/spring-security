package org.example.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.LoginUser;
import org.example.domain.User;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    public JwtFilter (){
        System.out.println("xxxxxxxxx");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("JwtFilterJwtFilterJwtFilterJwtFilter start");
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication =  context.getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof LoginUser) {

            filterChain.doFilter(request,response);
            return;
        }

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!StringUtils.hasLength(token)){
            filterChain.doFilter(request,response);
            return;
        }
        User user = new ObjectMapper().readValue(token.replace("Bearer",""), User.class);
        LoginUser loginUser = new LoginUser(user);
        System.out.println(loginUser);
        // 添加验证信息状态，到spring security context过程的验证过程中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,null);
        context.setAuthentication(authenticationToken);


        filterChain.doFilter(request,response);
    }
}
