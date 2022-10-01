package com.rankingsystem.backend.web.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthRequest {

    @NotNull
    String username;

    @NotNull
    String password;
}
