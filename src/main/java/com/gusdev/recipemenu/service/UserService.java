package com.gusdev.recipemenu.service;

import com.gusdev.recipemenu.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findById(Long id);
    User findUserByJwt(String jwt);

}
