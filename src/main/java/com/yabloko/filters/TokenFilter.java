package com.yabloko.filters;

import com.yabloko.authentication.TokenAuthentication;
import com.yabloko.models.Token;
import com.yabloko.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


// ИНИЦИАЛИЗИРУЕТ АУТЕНТИФИКАЦИЮ ЕСЛИ ТОКЕН ПЕРЕДАННЫЙ В ЗАПРОСЕ А - ЕСТЬ Б - ПРИСУТСТВУЕТ В БД
// А В БД ТОКЕН ЕСТЬ ЕСЛИ ЛОГИН ПРОШЕЛ УСПЕШНО
@Component
public class TokenFilter implements Filter {

    @Autowired
    TokenService tokenService;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        int n = 0;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication authenticationFromContext = SecurityContextHolder.getContext().getAuthentication();

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String tokenFromRequest = httpServletRequest.getParameter("token");
        Token tokenFromRepo = null;
        if (tokenFromRequest != null)
            tokenFromRepo = tokenService.get(tokenFromRequest);

        if (tokenFromRepo != null) {
            TokenAuthentication tokenAuthentication = new TokenAuthentication(tokenFromRequest);
            SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
