package com.yabloko.commandLineRunner;

import com.yabloko.models.User;
import com.yabloko.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/*
* By implementing the CommandLineRunner, the run() method of the
* MyRunner class will be executed after the application starts.
* */

// скрипт для ХЭШирования паролей - можно реализовать строго через SQL ???
@Component
@Profile("test") /* @Profile("!test") @Profile("autorun") */
public class PasswordRunner implements CommandLineRunner {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
        List<User> users = userService.findAll();
        for (User user: users) {
            String password = user.getPassword();
            user.setPassword(passwordEncoder.encode(password));
            userService.save(user);
        }
    }
}


