package com.yabloko.security.details;

import com.yabloko.models.State;
import com.yabloko.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


// АДАПТЕР ЮЗЕРА К СПРИНГ
public class UserDetailsImpl implements UserDetails {

    private User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getState() != State.DELETED;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getState() != State.BANNED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getState() != State.DELETED;
    }

    @Override
    public boolean isEnabled() {
        return user.getState() == State.ACTIVE;
    }
}
