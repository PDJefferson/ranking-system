package com.rankingsystem.backend.security;

import com.rankingsystem.backend.domain.security.Role;
import com.rankingsystem.backend.domain.security.User;
import com.rankingsystem.backend.exceptions.UserAlreadyExistsException;
import com.rankingsystem.backend.repositories.security.RoleRepository;
import com.rankingsystem.backend.repositories.security.UserRepository;
import com.rankingsystem.backend.web.mappers.UserEditMapper;
import com.rankingsystem.backend.web.mappers.UserViewMapper;
import com.rankingsystem.backend.web.model.CreateUserRequest;
import com.rankingsystem.backend.web.model.UpdateUserRequest;
import com.rankingsystem.backend.web.model.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserViewMapper userViewMapper;


    private final UserEditMapper userEditMapper;
    private final RoleRepository  roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public UserDto create(CreateUserRequest request) {
        Role playerRole = roleRepository.findByRoleName("PLAYER").orElseThrow();
        log.debug("check if playerRole exists: " + playerRole.getRoleName());
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("username exists!");
        }

        User user = userEditMapper.create(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of(playerRole));
        user = userRepository.save(user);
        return userViewMapper.userToUserDto(user);
    }

    @Transactional
    public UserDto update(Integer id, UpdateUserRequest request) {
        User user = userRepository.getReferenceById(id);
        userEditMapper.update(request, user);

        user = userRepository.save(user);

        return userViewMapper.userToUserDto(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Getting user info via JPA");
        return userRepository.findByUsername(username).orElseThrow(() -> {
            return new UsernameNotFoundException("Username: " + username + " not found");
        });
    }
}
