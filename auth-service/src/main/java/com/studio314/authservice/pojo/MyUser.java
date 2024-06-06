package com.studio314.authservice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyUser implements Serializable {

    private String uName;
    private String password;
    private int uID;
    private String mail;
    private String role;
    private int cnt;

//    transient List<SecurityGrantedAuthority> authorities;

}
