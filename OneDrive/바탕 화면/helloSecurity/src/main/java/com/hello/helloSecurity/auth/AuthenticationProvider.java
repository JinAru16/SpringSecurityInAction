package com.hello.helloSecurity.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public interface AuthenticationProvider {
    Authentication authentication(Authentication authentication) throws AuthenticationException;
    boolean supports(Class<?> authentication);
}
