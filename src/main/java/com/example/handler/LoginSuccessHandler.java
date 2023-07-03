package com.example.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    final String format = "LoginSuccessHandler => {}";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        HttpSession httpSession = request.getSession();

        String url = (String)httpSession.getAttribute("url");

        log.info(format, url);

        String role = authentication.getAuthorities().toArray()[0].toString();

        log.info(format, role);

        if(role.equals("ROLE_USER")) {
            if(url == null) {
                response.sendRedirect(request.getContextPath() + "/home.do");
            }
            else {
                response.sendRedirect(request.getContextPath() + url);
            }
        }

    }

}
