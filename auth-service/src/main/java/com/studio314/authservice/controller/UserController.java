package com.studio314.authservice.controller;


import com.studio314.authservice.service.LoginService;
import com.studio314.tiknotokcommon.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
@Slf4j
public class UserController {

    @Autowired
    LoginService loginService;

    @PostMapping("/register")
    public Result register(@RequestBody HashMap body){
        String name = (String) body.get("name");
        String mail = (String) body.get("mail");
        String password = (String) body.get("password");

        log.info("name:{}尝试注册",name);
        return loginService.register(name, mail, password);
    }

    @PostMapping("/login")
    public Result login(@RequestBody HashMap body) {
        String mail = (String) body.get("mail");
        String password = (String) body.get("password");
        log.info("用户尝试登录：{}", mail);
        log.info("密码: {}", password);
//        System.out.println(loginService);
        Result login = loginService.login(mail, password);
        System.out.println(login);
        return login;
    }
}