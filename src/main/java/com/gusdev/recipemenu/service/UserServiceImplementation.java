package com.gusdev.recipemenu.service;

import com.gusdev.recipemenu.config.JwtProvider;
import com.gusdev.recipemenu.domain.User;
import com.gusdev.recipemenu.infra.exception.JwtTokenException;
import com.gusdev.recipemenu.infra.exception.UserNotFoundException;
import com.gusdev.recipemenu.infra.exception.enums.ErrorCode;
import com.gusdev.recipemenu.respository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static com.gusdev.recipemenu.utils.Utility.LOGGER;


@Service
public class UserServiceImplementation implements UserService {
    private UserRepository userRepository;
    private JwtProvider jwtProvider;
    public UserServiceImplementation(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;

    }

    public User save(User user) {
        return userRepository.save(user);
    }
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException(ErrorCode.WA0001.getMessage(), ErrorCode.WA0001.getCode());
        }
        return user;
    }
    @Override
    public User findById(Long id) {
        var userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            LOGGER.info("This recipe is empty - RecipeServiceImplementation");
            throw new UserNotFoundException(ErrorCode.WA0001.getMessage(), ErrorCode.WA0001.getCode());
        }
        return userOptional.get();
    }

    @Override
    public User findUserByJwt(String jwt) {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        if (Objects.isNull(email)) {
            LOGGER.warn("Invalid email search by token");
            throw new JwtTokenException(ErrorCode.ON0002.getCode(), ErrorCode.ON0002.getMessage());
        }
        User user = userRepository.findByEmail(email);
        if (Objects.isNull(user)) {
            LOGGER.warn("User not found by param email: '{}'", email);
            throw new UserNotFoundException(ErrorCode.WA0001.getCode(), ErrorCode.WA0001.getMessage());
        }
        return user;
    }
}
