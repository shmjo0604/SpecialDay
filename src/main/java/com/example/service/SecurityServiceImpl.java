package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.dto.LoginUser;
import com.example.dto.Member;
import com.example.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SecurityServiceImpl implements UserDetailsService {

    final String format = "SecurityServiceImpl => {}";

    @Autowired MemberMapper mMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info(format, username);

        Member ret = mMapper.selectMemberOne(username);

        if(ret != null && ret.getChk() == 1) {

            String[] strRole = {"ROLE_USER"};
            Collection<GrantedAuthority> role = AuthorityUtils.createAuthorityList(strRole);
            return new LoginUser(ret.getId(), ret.getPassword(), role, "nickname");

        }
        else {
            return User.builder()
                    .username("_")
                    .password("_")
                    .roles("_")
                    .build();
        }

    }
    
}
