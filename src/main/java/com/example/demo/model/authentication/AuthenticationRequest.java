package com.example.demo.model.authentication;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AuthenticationRequest {
    private String username;
    private String password;
    private String name;
}
