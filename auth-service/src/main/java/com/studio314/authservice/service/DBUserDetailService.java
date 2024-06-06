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
        System.out.println("loadUserByUsername: 数据库获取信息中, mail: " + mail);
        MyUser myUser = null;
        try {
            myUser = userMapper.getUserByMail(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("获取成功" + myUser);
        if(myUser == null){
            throw new UsernameNotFoundException(mail);
        }
//        List<SecurityGrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SecurityGrantedAuthority(myUser.getRole()));
//        myUser.setAuthorities(authorities);

        return new LoginUser(myUser);
    }
}

