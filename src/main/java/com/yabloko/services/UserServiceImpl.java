package com.yabloko.services;


import com.yabloko.forms.UserForm;
import com.yabloko.models.Role;
import com.yabloko.models.State;
import com.yabloko.models.User;
import com.yabloko.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent())
            return optionalUser.get();
        else
            throw new IllegalArgumentException("no user with " + userId + "id");
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.getByLogin(login);
    }

    public void signUp(UserForm userForm) {
        User user = User.from(userForm);
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        user.setRole(Role.USER);
        user.setState(State.ACTIVE);
        userRepository.save(user);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}