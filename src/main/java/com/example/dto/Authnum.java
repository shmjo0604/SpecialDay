package com.example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "authnum")
public class Authnum {
    
    private String email;
    private String authnum;

}
