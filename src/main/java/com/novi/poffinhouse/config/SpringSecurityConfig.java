package com.novi.poffinhouse.config;

import com.novi.poffinhouse.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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
//                                .requestMatchers("/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/berries").permitAll()
                                .requestMatchers(HttpMethod.GET, "/pokemon").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/pokemon/{id}/validate").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/games").authenticated()
                                .requestMatchers(HttpMethod.POST, "/authenticate").permitAll()
                                .requestMatchers(HttpMethod.GET, "/authenticated").authenticated()
                                .requestMatchers("/users/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/trainer/**").hasRole("TRAINER")
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