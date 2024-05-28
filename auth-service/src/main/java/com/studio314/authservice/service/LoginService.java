package com.studio314.authservice.service;

import com.studio314.tiknotokcommon.utils.Result;

public interface LoginService {
    Result login(String mail, String password);

    Result register(String name, String mail, String password);
}
