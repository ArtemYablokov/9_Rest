package com.yabloko.security;

import com.yabloko.authentication.TokenAuthenticationProvider;
import com.yabloko.filters.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
@ComponentScan("com.yabloko")
@Configuration
public class Securityconfig extends WebSecurityConfigurerAdapter {

    @Autowired
    TokenFilter tokenFilter;

    @Autowired
    TokenAuthenticationProvider tokenAuthenticationProvider;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                сначала запрос идет в фильтр, затем в провайдер
//                (вообще провайдер тольок раз вызывается - при первом запросе??? - и только при наличии токена!)
//                где устанавивается флаг АУТЕНТИФИЦИРОВАН + к аутентификации добавлется ЮЗЕРДЕТЭЙЛЗ
//                это при запросе требующем аутентификацию - без аутентификации - просто два прохода через фильтр
                .addFilterBefore(tokenFilter, BasicAuthenticationFilter.class)
                    .antMatcher("/**").authenticationProvider(tokenAuthenticationProvider)

                .authorizeRequests()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/signUp").permitAll()
                    .antMatchers("/users/**").authenticated()
//                для логаута по POST-запросу
                .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/")
        ;
        http.csrf().disable();
    }
}
