package com.example.demo.model.authentication;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
public class AuthenticationResponse {

    private Long id;
    private String token;
    private final String type = "Bearer";
    private String name;
    private String username;
    private Collection<? extends GrantedAuthority> roles;

    public AuthenticationResponse(Long id, String token, String name, String username, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.token = token;
        this.name = name;
        this.username = username;
        this.roles = roles;
    }

}
