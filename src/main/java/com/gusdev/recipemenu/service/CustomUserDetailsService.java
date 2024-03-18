package com.gusdev.recipemenu.service;

import com.gusdev.recipemenu.infra.exception.UserNotFoundException;
import com.gusdev.recipemenu.infra.exception.enums.ErrorCode;
import com.gusdev.recipemenu.respository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
//using our own generated token to use, i.e, the spring doesn't generate the jwt token automatically
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(username);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException(ErrorCode.WA0002.getCode(), ErrorCode.WA0002.getMessage());
        }
        List<GrantedAuthority> authorityList = new ArrayList<>();
        return new User(user.getEmail(), user.getPassword(), authorityList);
    }
}
