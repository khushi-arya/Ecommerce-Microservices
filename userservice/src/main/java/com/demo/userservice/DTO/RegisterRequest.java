package com.demo.userservice.DTO;

import com.demo.userservice.Enum.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
}
