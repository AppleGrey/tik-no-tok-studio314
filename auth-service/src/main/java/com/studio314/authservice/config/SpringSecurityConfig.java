package com.studio314.authservice.config;

//import com.studio314.authservice.component.JwtAuthenticationTokenFilter;
import com.studio314.authservice.component.ResponseFilter;
import com.studio314.authservice.component.SpringSecurityHandler;
//import com.studio314.authservice.handler.CustomAuthenticationFailureHandler;
import com.studio314.authservice.handler.MyAuthenticationEntryPoint;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;


@EnableWebSecurity
@Configuration
@EnableMethodSecurity
@EnableGlobalAuthentication
public class SpringSecurityConfig {
    // SpringSecurityHandler 处理器
    @Resource
    private SpringSecurityHandler handler;
    // 修改输出流的输出格式
    @Resource
    private ResponseFilter responseFilter;

//    @Autowired
//    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
//    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
//                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(request -> {
//                    request.anyRequest().permitAll();
                    request.requestMatchers(HttpMethod.POST, "/auth/login").permitAll();
                    request.requestMatchers(HttpMethod.POST, "/auth/register").permitAll();

//                    request.requestMatchers("/signup/signup").permitAll();
                    request.anyRequest().authenticated();
                })
                .addFilterBefore(responseFilter, WebAsyncManagerIntegrationFilter.class) // 在 Web...过滤器之前添加过滤器
                .formLogin(request -> { // 添加登录处理器
                    request.successHandler(handler)
                            .failureHandler(handler);
                })
                .exceptionHandling(exeption -> {
                    exeption.authenticationEntryPoint(new MyAuthenticationEntryPoint())
                            .accessDeniedHandler(handler);
                })
                .logout(request -> { // 添加退出处理器
                    request.logoutSuccessHandler(handler)
                            .addLogoutHandler(handler);
                });
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


}

