package com.hello.helloSecurity.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Getter
@Setter
@ToString
public class Users {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String authority;

    public Users(String username, String password, String authority){
        this.username = username;
        this.password = password;
        this.authority = authority;
    }

    public Users() {

    }
}
