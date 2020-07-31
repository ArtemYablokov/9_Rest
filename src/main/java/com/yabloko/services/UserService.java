package com.yabloko.services;

import com.yabloko.forms.UserForm;
import com.yabloko.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> findAll();
    public User findById(Long userId) ;
    public User findByLogin(String login) ;
    public void signUp(UserForm userForm);
    void save(User user);
}
