package com.rankingsystem.backend.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateUserRequest {

    @NotBlank
    String username;

    Set<String> roles;

}
