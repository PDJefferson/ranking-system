package com.rankingsystem.backend.web.controllers.api;


import com.rankingsystem.backend.domain.security.User;
import com.rankingsystem.backend.exceptions.UserAlreadyExistsException;
import com.rankingsystem.backend.security.JpaUserDetailsService;
import com.rankingsystem.backend.web.mappers.UserViewMapper;
import com.rankingsystem.backend.web.model.AuthRequest;
import com.rankingsystem.backend.web.model.CreateUserRequest;
import com.rankingsystem.backend.web.model.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;


@RequiredArgsConstructor
@Slf4j
@CrossOrigin
@RequestMapping("/api/v1/public/")
@RestController
public class AuthController {

    private final JwtEncoder jwtEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserViewMapper userViewMapper;
    private final JpaUserDetailsService userDetailsService;


    @PostMapping(path = "login",  consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDto> login(@Valid @RequestBody AuthRequest authRequest) {
        log.debug("Login request: {", authRequest + "}");
        try {
            Authentication authentication= authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            User user = (User) authentication.getPrincipal();

            Instant now = Instant.now();
            long expiry = 36000L;

            String scope = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(joining(" "));

            JwtClaimsSet claims = JwtClaimsSet.builder()
                    .issuer("rankingsystem.com")
                    .issuedAt(now)
                    .expiresAt(now.plusSeconds(expiry))
                    .subject(format("%s,%s", user.getId(), user.getUsername()))
                    .claim("roles", scope)
                    .build();

            String token = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
            UserDto userDto = userViewMapper.userToUserDto(user);
            userDto.setJwt(token);
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .body(userDto);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping(path= "register", consumes = "application/json")
    public ResponseEntity<UserDto> register(@RequestBody @Valid CreateUserRequest request) {
        try{
            userDetailsService.create(request);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (UserAlreadyExistsException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
