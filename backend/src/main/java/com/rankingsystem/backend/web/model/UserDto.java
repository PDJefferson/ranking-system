package com.rankingsystem.backend.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDto {

    String jwt;

    Integer id;

    String username;

}
