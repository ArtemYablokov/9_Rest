package com.yabloko.security.details;

import com.yabloko.models.User;
import com.yabloko.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// ДЛЯ ЛОГИНА РЕАЛИЗОВАН МЕТОД
@Service
public class UserDetailsProviderImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    // реализация загрузки через ЛОГИН как и авторизации тк ЛОГИН менять нельзя
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService.findByLogin(login);
        if (user != null)
            return new UserDetailsImpl(user);
        else
            throw new IllegalArgumentException("No user found");
    }
}