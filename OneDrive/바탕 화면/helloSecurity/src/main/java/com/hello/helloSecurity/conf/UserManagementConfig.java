package com.hello.helloSecurity.conf;

import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class UserManagementConfig {

    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager userDetailService = new InMemoryUserDetailsManager();

        UserDetails user = User.withUsername("john")
                .password("12345")
                .authorities("read")
                .build();

        userDetailService.createUser(user);
        return userDetailService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
