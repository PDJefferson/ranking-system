package com.rankingsystem.backend.web.mappers;


import com.rankingsystem.backend.domain.security.Role;
import com.rankingsystem.backend.domain.security.User;
import com.rankingsystem.backend.web.model.CreateUserRequest;
import com.rankingsystem.backend.web.model.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;

import java.util.HashSet;
import java.util.Set;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@RequiredArgsConstructor
@Mapper(componentModel = "spring")
public abstract class UserEditMapper {

    @Mapping(source="roles", target = "roles", qualifiedByName = "stringToRole")
    public abstract User create(CreateUserRequest userEditRequest);

    @BeanMapping(nullValueCheckStrategy = ALWAYS, nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(source = "roles", target = "roles", qualifiedByName = "stringToRole")
    public abstract void update(UpdateUserRequest request, @MappingTarget User user);

    @Named("stringToRole")
    protected Set<Role> stringToRole(Set<String> roles) {
        return new HashSet<>();
    }
}
