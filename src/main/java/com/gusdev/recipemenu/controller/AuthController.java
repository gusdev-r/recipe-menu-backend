package com.gusdev.recipemenu.controller;

import com.gusdev.recipemenu.config.JwtProvider;
import com.gusdev.recipemenu.domain.User;
import com.gusdev.recipemenu.dtos.request.LoginRequest;
import com.gusdev.recipemenu.infra.exception.UserNotFoundException;
import com.gusdev.recipemenu.infra.exception.enums.ErrorCode;
import com.gusdev.recipemenu.dtos.response.AuthResponse;
import com.gusdev.recipemenu.respository.UserRepository;
import com.gusdev.recipemenu.service.CustomUserDetailsService;
import com.gusdev.recipemenu.service.UserServiceImplementation;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;

public class AuthController {
    private UserRepository userRepository;
    private UserServiceImplementation userService;
    private CustomUserDetailsService customUserDetailsService;
    private JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, CustomUserDetailsService customUserDetailsService,
                          JwtProvider jwtProvider, PasswordEncoder passwordEncoder, UserServiceImplementation userService) {
        this.userRepository = userRepository;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }
    @PostMapping(path = "/signup")
    public AuthResponse createUser(@RequestBody User user) {

        String email = user.getEmail();
        String password = user.getPassword();
        String fullName = user.getFullName();
        String cpf = user.getCpf();

        User isExistEmail = userRepository.findByEmail(email);
        if (Objects.nonNull(isExistEmail)) {
            throw new UserNotFoundException(ErrorCode.WA0001.getCode(), ErrorCode.WA0001.getMessage());
        }
        User userCreated = new User();
        userCreated.setEmail(email);
        userCreated.setPassword(password);
        userCreated.setFullName(fullName);
        userCreated.setCpf(cpf);

        var savedUSer = userService.save(user); // he uses repository here
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        AuthResponse response = new AuthResponse();
        response.setJwt(token);
        response.setMessage("Signup success");
        return response;
    }
    public AuthResponse signingHandler(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);
        AuthResponse response = new AuthResponse();
        response.setJwt(token);
        response.setMessage("Signup success");
        return response;
    }
    @PostMapping(path = "/signing")
    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        if(Objects.isNull(userDetails)) {
            throw new UserNotFoundException(ErrorCode.WA0001.getCode(), ErrorCode.WA0001.getMessage());
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new UserNotFoundException(ErrorCode.ON0001.getCode(), ErrorCode.ON0001.getMessage());
        }
    return new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
    }
}
