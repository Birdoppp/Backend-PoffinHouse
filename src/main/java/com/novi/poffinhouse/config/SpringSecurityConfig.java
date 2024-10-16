package com.novi.poffinhouse.config;

import com.novi.poffinhouse.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.novi.poffinhouse.filters.JwtRequestFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {


    private final JwtRequestFilter jwtRequestFilter;

    private final CustomUserDetailsService customUserDetailsService;
    public final static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    protected SecurityFilterChain filter(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(auth ->
                                auth
                                        .requestMatchers(HttpMethod.GET, "/*/validated").permitAll()
                                        .requestMatchers(HttpMethod.GET, "/authenticated").authenticated()
                                        .requestMatchers(HttpMethod.GET, "/games").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.GET, "/users",
                                                                         "/authorities/**",
                                                                         "/region-maps/**",
                                                                         "/pokemon/**",
                                                                         "/berries/**",
                                                                         "/games/**",
                                                                         "/owned-pokemon/**",
                                                                         "/teams/**",
                                                                         "/game-maps/**",
                                                                         "/locations/**",
                                                                         "/berry-planting-sites/**"
                                                ).hasAnyRole("TRAINER","ADMIN")
                                        .requestMatchers(HttpMethod.GET, "/users/**",
                                                                         "/**").hasRole("ADMIN")



                                        .requestMatchers(HttpMethod.POST, "/users",
                                                                          "/authenticate").permitAll()
                                        .requestMatchers(HttpMethod.POST,
                                                                          "/pokemon",
                                                                          "/berries",
                                                                          "/games/**",
                                                                          "/owned-pokemon",
                                                                          "/teams",
                                                                          "/locations",
                                                                          "/berry-planting-sites/**"
                                                ).hasAnyRole("TRAINER", "ADMIN")
                                        .requestMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")



                                        .requestMatchers(HttpMethod.PUT,  "/users/username/**",
                                                                          "/pokemon/nationalDex/**",
                                                                          "/berries/**",
                                                                          "/teams/**"
                                                ).hasAnyRole("TRAINER", "ADMIN")
                                        .requestMatchers(HttpMethod.PUT,  "/**").hasRole("ADMIN")



                                        .requestMatchers(HttpMethod.PATCH,"/games/**",
                                                                          "/owned-pokemon/*/contest-condition",
                                                                          "/locations/**",
                                                                          "/berry-planting-sites/**"
                                                ).hasAnyRole("TRAINER", "ADMIN")
                                        .requestMatchers(HttpMethod.PATCH,"/region-maps/**",
                                                                          "/*/validate").hasRole("ADMIN")



                                        .requestMatchers(HttpMethod.DELETE, "owned-pokemon/**",
                                                                            "/locations/id/**",
                                                                            "/berry-planting-sites/**"
                                                ).hasAnyRole("TRAINER", "ADMIN")
                                        .requestMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")

                                        .anyRequest().denyAll()

                ).sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder);
        return authManagerBuilder.build();
    }

}