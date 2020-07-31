package com.yabloko.controllers;

import com.yabloko.forms.UserForm;
import com.yabloko.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {
    @Autowired
    UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<Object> signUp(@RequestBody UserForm userForm) // JSON-объект передаем
    {
        userService.signUp(userForm);
        return ResponseEntity.ok().build();
    }
}
