package com.metacrew.pr2s.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/address/**", "/room-search/**");
        http.authorizeHttpRequests((requests) -> requests
                        // /about 요청에 대해서는 로그인을 요구함
                        .antMatchers("/about").authenticated()
                        // /admin 요청에 대해서는 ROLE_ADMIN 역할을 가지고 있어야 함
                        .antMatchers("/admin").hasRole("ADMIN")
                        // 나머지 요청에 대해서는 로그인을 요구하지 않음
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true));

        return http.build();
    }

}

