//package com.studio314.authservice.component;
//
//import com.studio314.authservice.config.RedisCache;
//import com.studio314.authservice.pojo.LoginUser;
//import com.studio314.tiknotokcommon.utils.JWTUtils;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Objects;
//
//@Component
//public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private RedisCache redisCache;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, ServletException {
//        //获取token
//        String token = request.getHeader("token");
//        if (!StringUtils.hasText(token)) {
//            //放行
//            filterChain.doFilter(request,response);
//            return;
//        }
//        //解析token
//        String userId = JWTUtils.getUserId(token);
//        System.out.println("filter:userId:"+userId);
//        //从redis中获取用户信息
////        userId = userId.substring(userId.indexOf("=") + 1 , userId.indexOf("}"));
//        String redisKey = "login:" + userId;
//        LoginUser loginUser = redisCache.getCacheObject(redisKey);
//        System.out.println("filter:" + loginUser);
//        if (Objects.isNull(loginUser)){
//            //返回登录失败
//            response.sendError(401,"Unauthorized");
//            return;
//        }
//        //存入SecurityContextHolder
//        // TODO 获取权限信息封装到Authentication中
//        //获取权限信息
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//        //放行
//        filterChain.doFilter(request,response);
//    }
//}
