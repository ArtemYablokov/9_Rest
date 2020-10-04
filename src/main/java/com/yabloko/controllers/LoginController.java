package com.yabloko.controllers;

import com.yabloko.forms.LoginForm;
import com.yabloko.models.User;
import com.yabloko.services.TokenService;
import com.yabloko.transfers.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class LoginController {

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginForm loginForm, Authentication authentication, HttpServletResponse response) throws IOException {
        int n = 0;
        Authentication authenticationFromContext = SecurityContextHolder.getContext().getAuthentication(); // даже если не аутентифицироваться - она будет-анонимная) но в фильтре будет НУЛЛ
        if (authentication != null) {
            User principal = (User) authentication.getPrincipal();
            response.sendRedirect("/users");
        }
        ResponseEntity<TokenDto> ok = ResponseEntity.ok(tokenService.save(loginForm));
        return ok;
        // {"value":"Arp9tQjeP4"} - в ответе приходит JSON - DtoToken
    }


}