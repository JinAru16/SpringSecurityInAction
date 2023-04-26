package com.hello.helloSecurity.user;

import com.hello.helloSecurity.auth.UserDetails;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class SimpleUser implements UserDetails {
    private final String username;
    private final String password;

    public SimpleUser(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnable() {
        return false;
    }
}
