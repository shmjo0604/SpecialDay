package com.example.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"password"})
public class LoginUser extends User {

    private String id;
    private String password;
    private Collection<GrantedAuthority> authorities;
    private String nickname;
    
    public LoginUser(String username, String password, Collection<GrantedAuthority> authorities, String nickname) {
        super(username, password, authorities);
        this.id = username;
        this.password = password;
        this.authorities = authorities;
        this.nickname = nickname;
    }
    
}
