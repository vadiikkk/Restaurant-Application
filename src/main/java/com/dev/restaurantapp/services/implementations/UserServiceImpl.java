package com.dev.restaurantapp.services.implementations;

import com.dev.restaurantapp.entities.User;
import com.dev.restaurantapp.exceptions.RawCannotBeNullException;
import com.dev.restaurantapp.exceptions.NameAlreadyExistsException;
import com.dev.restaurantapp.repositories.UserRepository;
import com.dev.restaurantapp.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private PasswordEncoder encoder;

    @Override
    public User Register(User user) {
        try {
            user.setPassword(encoder.encode(user.getPassword()));
            return userRepository.save(user);
        } catch (IllegalArgumentException exception) {
            throw new RawCannotBeNullException(exception.getMessage());
        } catch (RuntimeException exception) {
            throw new NameAlreadyExistsException(exception.getMessage());
        }
    }
}
