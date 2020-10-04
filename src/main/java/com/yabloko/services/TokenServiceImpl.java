package com.yabloko.services;

import com.yabloko.forms.LoginForm;
import com.yabloko.models.Token;
import com.yabloko.models.User;
import com.yabloko.repositories.TokenRepository;
import com.yabloko.repositories.UserRepository;
import com.yabloko.transfers.TokenDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class TokenServiceImpl implements TokenService {

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public TokenDto save(LoginForm loginForm) {
        User owner = userRepository.getByLogin(loginForm.getLogin());
        if (owner != null){ // &&
            if ( passwordEncoder.matches(loginForm.getPassword(), owner.getPassword()) ){
                Token token = Token.builder()
                        .owner(owner)
                        // юзеру здесь ТОКЕН не добавляется - тк токены к юзеру привязываются при запросе ЮЗЕРА из БД
                        .value(RandomStringUtils.random(9,true, true) )
                        .build();
                tokenRepository.save(token);
                return TokenDto.from(token);
            }
        }
        throw new IllegalArgumentException("no user whith this login/password");
    }

    @Override
    public Token get(String token) {
        return tokenRepository.findByValue(token);
    }

    // no usage
    @Override
    public boolean exist(String token) {
        return tokenRepository.existsByValue(token);
    }
}