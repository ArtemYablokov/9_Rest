package com.yabloko.authentication;

import com.yabloko.models.Token;
import com.yabloko.models.User;
import com.yabloko.security.details.UserDetailsImpl;
import com.yabloko.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


// на каждый запрос навешивается - ВООБЩЕ ДОЛЖНО ХВТАТЬ ПРОСТО ПРИ ЛОГИНЕ ВЫПОЛНИТЬ ЭТОТ МЕТОД
// инициализация объекта АУТЕНТИФИКАЦИИ(токен) юзером(юзер детэйлсами)
@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    TokenService tokenService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // сюда приходит аутентификация после фильтра - в ней есть только ТОКЕН
        TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;
        String tokenValue = tokenAuthentication.getName();
//        токен же вместе с ЮЗЕРОМ вынимается ???
        Token token = tokenService.get(tokenValue);
        if (token != null) {
            User owner = token.getOwner();
//            кладем ТОКЕНУ ЮЗЕРА - а когда происходит наоборот? в юзера - токены ?
//            при вынимании юзера ?
//            НЕТ - здесь оперирование с ЮзерДетэйлз
            tokenAuthentication.setUserDetails(new UserDetailsImpl(owner));
            tokenAuthentication.setAuthenticated(true);
            return tokenAuthentication;
        } else {
            authentication.setAuthenticated(false);
            return authentication;
        }
    }

//    if this AuthenticationProvider supports the indicated Authentication object
    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication); // сравнение по ссылке ? что?
    }
}