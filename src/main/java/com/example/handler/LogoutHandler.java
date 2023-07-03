package com.example.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogoutHandler implements LogoutSuccessHandler {

    final String format = "LogoutHandler => {}";

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
            
        log.info(format, authentication.toString());

        String role = authentication.getAuthorities().toArray()[0].toString();

        if(role.equals("ROLE_USER")) {
            response.sendRedirect(request.getContextPath() + "/login.do");
        }
        else if(role.equals("ROLE_ADMIN")) {
            response.sendRedirect(request.getContextPath() + "/admin/login.do");
        }
    }
    
}
