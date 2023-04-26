package com.hello.helloSecurity.auth;

import java.io.Serializable;

public interface GrantAuthority extends Serializable {
    String getAuthority();
}
