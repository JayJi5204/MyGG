package com.project.mygg.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 접근 제어
        http
                .authorizeHttpRequests((auth)->auth
                        // permitAll() -> 모든 사용자에게 로그인하지 않아도 접근가능
                        .requestMatchers("/home","/tierList","/rule","/ranking","/signIn","/signUp").permitAll()
                        // hasRole() -> 특정한 권한이 있어야만 접근 가능
                        .requestMatchers("/result","/memberList").hasRole("ADMIN")
                        // hasAnyRole() -> 이 권한이 있으면 접근 가능
                        .requestMatchers("/board/**").hasAnyRole("ADMIN","MEMBER")
                        // authenticated() -> 로그인만 하면 어디든 가능
                        .anyRequest().authenticated());
                        // denyAll() - > 누구도 접근하지 못함

        // 로그인 폼 응답
        http
                .formLogin((auth)->auth.loginPage("/singIn")
                        .loginProcessingUrl("/singIn")
                        .permitAll()
                );

        http
                .csrf((auth)->auth.disable());

        return http.build();
    }
}
