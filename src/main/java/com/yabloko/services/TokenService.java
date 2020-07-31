package com.yabloko.services;

import com.yabloko.forms.LoginForm;
import com.yabloko.models.Token;
import com.yabloko.transfers.TokenDto;

public interface TokenService {
    TokenDto save(LoginForm loginForm);
    Token get(String token);
    boolean exist(String token);
}