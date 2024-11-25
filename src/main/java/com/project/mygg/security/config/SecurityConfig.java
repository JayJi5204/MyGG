package com.project.mygg.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        return RoleHierarchyImpl.withDefaultRolePrefix()
                .role("ADMIN").implies("MANAGER")
                .role("MANAGER").implies("MEMBER")
                .build();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 접근 제어
        http.authorizeHttpRequests((auth) -> auth
                // permitAll() : 모든 사용자에게 로그인하지 않아도 접근가능
                .requestMatchers("/css/**", "/js/**", "/img/**", "/", "/home", "/tierList", "/ranking", "/rule", "/board", "/signIn", "/signUp").permitAll()
                // hasRole() : 특정한 권한이 있어야만 접근 가능
                .requestMatchers("/memberManage", "/updateMember/**", "/deleteMember/**").hasRole("ADMIN")
                .requestMatchers("/resultManage", "/addResult", "/updateResult", "/playerManage", "/addPlayer", "/updatePlayer/**", "/deletePlayer/**").hasRole("MANAGER")
                // hasAnyRole() : 이 권한이 있으면 접근 가능
                .requestMatchers("/board/**", "/gameResult/**", "/playerSearch/**").hasRole("MEMBER")
                // authenticated() : 로그인만 하면 어디든 가능
                // denyAll() : 누구도 접근하지 못함
                .anyRequest().authenticated());

        // 로그인 폼 응답
        http
                .formLogin((auth) -> auth
                        .loginPage("/signIn")
                        .loginProcessingUrl("/signIn")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/signIn?error=true")
                        .permitAll());

        // 로그아웃
        http
                .logout((auth) -> auth
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/"));

//        http
//                .csrf((auth) -> auth.disable());


        http
                .sessionManagement((auth) -> auth
                        // 하나의 아이디에 대해 다중 로그인을 허용하는 갯수
                        .maximumSessions(1)
                        // 다중로그인 갯수를 초과했을 때 처리방법
                        // true : 초과시 새로운 로그인 차단
                        // false : 초과시 기존 세션 하나 삭제
                        .maxSessionsPreventsLogin(true));

        http
                // 세션 고정 보호
                .sessionManagement((auth) -> auth
                        // none : 로그인 시 세션 정보 변경 안함
                        // newSession() : 로그인 시 세션 새로 생성
                        // changeSessionId() : 로그인시 동일한 세션에 대해 id변경
                        .sessionFixation().changeSessionId());

        return http.build();
    }
}
