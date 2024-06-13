package com.studio314.apigateway.config;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Configuration
public class LimitConfig {
    /**
     * 按照 Path 访问次数限流
     *
     * @return key
     */
    @Bean
    public KeyResolver pathKeyResolver() {
//        return exchange -> Mono.just(
//                exchange.getRequest()
//                        .getPath()
//                        .toString()
//        );
        return exchange -> Mono.just(
                Objects.requireNonNull(exchange.getRequest()
                                .getRemoteAddress())
                        .getHostName()
        );
    }

//    /**
//     * 按照 Path 访问次数限流
//     */
//    @Bean
//    public KeyResolver pathKeyResolver() {
//        /* 内部类 */
//        return new KeyResolver() {
//            @Override
//            public Mono<String> resolve(ServerWebExchange exchange) {
//                System.out.println("---" + exchange.getRequest().getPath().toString());
//                return Mono.just(exchange.getRequest().getPath().toString());
//            }
//        };
//    }

//   /**
//     * 根据IP限流
//     */
//    @Bean
//    public KeyResolver ipKeyResolver() {
//        return exchange -> Mono.just(
//                Objects.requireNonNull(exchange.getRequest()
//                                .getRemoteAddress())
//                        .getHostName()
//        );
//    }

//    /**
//     * 根据token限流
//     */
//    @Bean
//    public KeyResolver tokenKeyResolver() {
//        return new KeyResolver() {
//            @Override
//            public Mono<String> resolve(ServerWebExchange exchange) {
//                return Mono.just(exchange.getRequest().getQueryParams().getFirst("token"));
//            }
//        };
//    }

}