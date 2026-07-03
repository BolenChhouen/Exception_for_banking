package com.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


//    Incoming Request
//      ↓
//    Security Filters
//      ↓
//    Controller
    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http
    ) throws Exception {

        http
        // CSRF protection is mainly designed for browser form submissions.
        // Most Restful API used
        // JWT
        // Bearer Token
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("api/public/**").permitAll() // everyone
                        .requestMatchers("api/admin/**")
                            .hasRole("ADMIN")
                        .requestMatchers(
                                "api/user",
                                "api/profile")
                            .hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated() // required login
                )
                .httpBasic(
                        Customizer.withDefaults()
                );

        return http.build();
    }


    @Bean
    public UserDetailsService  userDetailsService() {
        UserDetails admin = User.builder()
                .username("bolen")
                .password(encoder().encode("123456"))
                .roles("USER")
                .build();


        UserDetails user =
                User.builder()
                        .username("user")
                        .password(encoder().encode("123456"))
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(
                admin,
                user
        );
    }
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder(12);
    }

}
