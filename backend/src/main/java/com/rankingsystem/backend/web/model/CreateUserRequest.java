package com.rankingsystem.backend.web.model;

import com.rankingsystem.backend.domain.security.Authority;
import com.rankingsystem.backend.domain.security.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    @NotBlank
    String username;

    @NotBlank
    String password;

    Set<String> roles;

}
