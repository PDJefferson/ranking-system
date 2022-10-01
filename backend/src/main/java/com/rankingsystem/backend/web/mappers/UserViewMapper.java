package com.rankingsystem.backend.web.mappers;

import com.rankingsystem.backend.domain.security.User;
import com.rankingsystem.backend.web.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserViewMapper {

    @Mapping(source = "id", target = "id")
    UserDto userToUserDto(User user);

    List<UserDto> usersToUserDtoList(List<User> users);


}
