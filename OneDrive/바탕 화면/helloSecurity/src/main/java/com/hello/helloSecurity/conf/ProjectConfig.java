package com.hello.helloSecurity.conf;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.hello.helloSecurity.auth.CustomAuthenticationFailureHandler;
import com.hello.helloSecurity.auth.CustomAuthenticationSuccessHandler;
import com.hello.helloSecurity.auth.CustomEntryPoint;
import com.hello.helloSecurity.service.InMemoryUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableAsync
@RequiredArgsConstructor
public class ProjectConfig extends WebSecurityConfigurerAdapter {
    private final AuthenticationProvider authenticationProvider;
    private final CustomAuthenticationSuccessHandler successHandler;

    private final CustomAuthenticationFailureHandler failureHandler;

    @Bean
    public UserDetailsService userDetailsService(){
        List<GrantedAuthority> authorities = Arrays.asList(
                //new SimpleGrantedAuthority("ADMIN"),
                new SimpleGrantedAuthority("READ"));
       UserDetails u = new User("John", "12345", authorities);
       List<UserDetails> users = List.of(u);
       return new InMemoryUserDetailsService(users);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean

    public InitializingBean initializingBean(){
        return () -> SecurityContextHolder.setStrategyName(
                SecurityContextHolder.MODE_INHERITABLETHREADLOCAL
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        http.httpBasic(c -> {
            c.realmName("OTHER");
            c.authenticationEntryPoint(new CustomEntryPoint());
        });

        http.authorizeRequests()
                .anyRequest()
                .authenticated();
    }
    */

        //로그인 폼이 따로 있는경우
        /*
        http.formLogin().defaultSuccessUrl("/home", true);
        http.authorizeRequests().anyRequest().authenticated();

         */

        //로그인 성공시, 실패시 따로 처리
        http.formLogin()
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                        .and()
                        .httpBasic();

        http.authorizeRequests()
                .anyRequest().authenticated();
    }

}
