package com.example.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UrlFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {

            String contextPath = request.getContextPath(); // -> /ROOT
            String path = request.getServletPath(); // -> /item/selectone.do
            String query = request.getQueryString(); // no=10 (? 없음)

            // log.info("UrlFilter => {}, {}, {}", contextPath, path, query);

            HttpSession httpSession = request.getSession();

            if (!path.contains("login") && !path.contains("logout") && !path.contains("join") && !path.contains("image")) {

                if (query == null) {
                    httpSession.setAttribute("url", path);
                } else {
                    httpSession.setAttribute("url", path + "?" + query);
                }

            }

            //log.info("UrlFilter => {}", httpSession.getAttribute("url"));
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
