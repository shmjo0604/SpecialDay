package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.handler.LoginSuccessHandler;
import com.example.handler.LogoutHandler;
import com.example.service.SecurityAdminServiceImpl;
import com.example.service.SecurityServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired SecurityServiceImpl userLoginService;
    @Autowired SecurityAdminServiceImpl adminLoginService;

    @Bean
    @Order(value = 1)
    public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception {

        // 아래 두 주소만 필터 => 이 두 주소에 한해서만 여기서 filter -> 나머지 주소는 아래 filterchain에서 filter
        http.antMatcher("/admin/login.do")
            .antMatcher("/admin/loginaction.do")
            .authorizeRequests().anyRequest().authenticated().and();

        // 로그인 처리
        http.formLogin()
            .loginPage("/admin/login.do")
            .loginProcessingUrl("/admin/loginaction.do")
            .usernameParameter("id")
            .passwordParameter("password")
            .defaultSuccessUrl("/admin/home.do")
            .permitAll();

        http.userDetailsService(adminLoginService);

        return http.build();
    } 

    @Bean
    @Order(value = 2)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        /* 권한 설정 */
        http.authorizeRequests()
            .antMatchers("/class/insert.do").authenticated()
            .antMatchers("/class/update.do").authenticated()
            .antMatchers("/class/basket.do").authenticated()
            .antMatchers("/apply", "/apply/*").authenticated()
            .antMatchers("/member/join.do").permitAll()
            .antMatchers("/member/joinsuccess.do").permitAll()
            .antMatchers("/member", "/member/*").authenticated()
            .antMatchers("/classunit", "/classunit/*").authenticated()
            .antMatchers("/community/insert.do").authenticated()
            .antMatchers("/admin/login.do").permitAll()
            .antMatchers("/admin/insert.do").permitAll()
            .antMatchers("/admin", "/admin/*").hasAnyAuthority("ROLE_ADMIN")
            .anyRequest().permitAll();

        http.exceptionHandling().accessDeniedPage("/403error.do");

        /* 로그인 처리 */
        http.formLogin()
            .loginPage("/login.do")
            .loginProcessingUrl("/loginaction.do")
            .usernameParameter("id")
            .passwordParameter("password")
            .successHandler(new LoginSuccessHandler())
            .permitAll();
        

        /* 로그아웃 처리 */
        http.logout()
            .logoutUrl("/logout.do")
            .logoutSuccessHandler(new LogoutHandler())
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .permitAll();

        http.userDetailsService(userLoginService);
        
        http.csrf().ignoringAntMatchers("/api/**");

        return http.build();
    }

    // 정적 자원에 sping security filter 규칙을 적용하지 않도록 설정 -> resources/static은 security를 적용 받지 않음
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    // 회원 가입에서 사용했던 암호화 알고리즘 설정, 로그인에서 같은 것을 적용해야 로그인이 가능
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
