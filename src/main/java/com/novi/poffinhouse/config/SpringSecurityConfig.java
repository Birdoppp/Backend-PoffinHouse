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
                                        .requestMatchers(HttpMethod.GET, "/users",
                                                                         "/authorities/**",
                                                                         "/region-maps/**",
                                                                         "/pokemon/**",
                                                                         "/berries/**",
                                                                         "/games/**",
                                                                         "/owned-pokemon/**",
                                                                         "/teams/**",
                                                                         "/locations/**",
                                                                         "/berry-planting-sites/**").hasAnyRole("TRAINER","ADMIN")
                                        .requestMatchers(HttpMethod.GET, "/users/**",
                                                                         "/**").hasRole("ADMIN")

//                                      TODO make RegionMap & Locations Validated


                                        .requestMatchers(HttpMethod.POST, "/users",
                                                                          "/authenticate").permitAll()
//                                        .requestMatchers(HttpMethod.POST, "/authorities/**").hasRole("ADMIN")
//                                        .requestMatchers(HttpMethod.POST, "/region-maps").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.POST, "/games/**",
                                                                          "/locations",
                                                                          "/berry-planting-sites/**",
                                                                          "/berries",
                                                                          "/pokemon",
                                                                          "/owned-pokemon",
                                                                          "/teams").hasAnyRole("TRAINER", "ADMIN")
                                        .requestMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")


                                        .requestMatchers(HttpMethod.PATCH,"/users/username/**",
                                                                          "/games/user/**",
//                                                                          "/pokemon/nationalDex/**",
//                                                                          "/berries/index-number/**",
                                                                          "/berry-planting-sites/**").hasAnyRole("TRAINER", "ADMIN")
                                        .requestMatchers(HttpMethod.PATCH,"/region-maps/**",
                                                                          "/games/**",
                                                                          "/*/validate").hasRole("ADMIN")


                                        .requestMatchers(HttpMethod.PUT, "/*/*/team",
                                                                         "/pokemon/nationalDex/**",
                                                                         "/berries/**").hasAnyRole("TRAINER", "ADMIN")
                                        .requestMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
//                                        .requestMatchers(HttpMethod.PUT, "/*/validate").hasRole("ADMIN")
//                                .requestMatchers(HttpMethod.PUT, "/**").authenticated()


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