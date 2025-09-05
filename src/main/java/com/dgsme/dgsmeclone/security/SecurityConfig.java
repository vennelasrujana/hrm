package com.dgsme.dgsmeclone.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // http.csrf().disable()
        // .cors()
        // .and()
        //     .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        //     .and()
        //     .authorizeHttpRequests()
        //     .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() 
        //     .requestMatchers("/api/employee-login/validate", "/api/employee-login/create").permitAll()
        //     .requestMatchers("/api/attendance/range","/api/attendance/{employeeId}").permitAll()
        //     .requestMatchers(HttpMethod.POST, "/api/admin/employees").permitAll()
        //     .requestMatchers(HttpMethod.PUT, "/api/admin/employees/{id}").permitAll()
        //     .requestMatchers(HttpMethod.DELETE, "/api/admin/employees/{id}").permitAll()
        //     .anyRequest().authenticated();

         http.csrf().disable()
        .cors()
        .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests()
            .anyRequest().permitAll();

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
