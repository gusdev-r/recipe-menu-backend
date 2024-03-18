package com.gusdev.recipemenu.controller;

import com.gusdev.recipemenu.domain.User;
import com.gusdev.recipemenu.respository.UserRepository;
import com.gusdev.recipemenu.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/users/")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public User findUserByJwt(@RequestHeader ("Authorization") String jwt) {
        User user = userService.findUserByJwt(jwt);
        return user;
    }
}
