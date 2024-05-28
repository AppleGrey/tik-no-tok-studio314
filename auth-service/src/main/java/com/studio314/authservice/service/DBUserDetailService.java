package com.studio314.authservice.service;

import com.studio314.authservice.mapper.UserMapper;
import com.studio314.authservice.pojo.LoginUser;
import com.studio314.authservice.pojo.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DBUserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

        MyUser myUser = userMapper.getUserByMail(mail);
        System.out.println(myUser);
        if(myUser == null){
            throw new UsernameNotFoundException(mail);
        }
//        List<SecurityGrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SecurityGrantedAuthority(myUser.getRole()));
//        myUser.setAuthorities(authorities);

        return new LoginUser(myUser);
    }
}

