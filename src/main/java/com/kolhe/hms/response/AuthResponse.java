package com.kolhe.hms.response;


import com.kolhe.hms.model.tenant.EnumRole;
import lombok.Data;

@Data
public class AuthResponse {
    private String message;
    private String jwt;
    private EnumRole role;
}
