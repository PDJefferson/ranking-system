package com.rankingsystem.backend.domain.security;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String permission;

    //referencing the property authorities where we have declared the dependencies for joining tables
    @ManyToMany(mappedBy = "authorities")
    private Set<Role> roles;
}
