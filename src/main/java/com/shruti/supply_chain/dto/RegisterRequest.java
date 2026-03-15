package com.shruti.supply_chain.dto;

import com.shruti.supply_chain.model.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
}