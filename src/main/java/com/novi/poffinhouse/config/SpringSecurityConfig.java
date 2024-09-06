package com.novi.poffinhouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain filter(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/berries").permitAll()
                                .requestMatchers(HttpMethod.GET, "/pokemon").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/pokemon/{id}/validate").hasRole("ADMIN")
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/trainer/**").hasRole("TRAINER")
                                .anyRequest().denyAll()

                ).sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();

    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(auth);
    }

//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder = new AuthenticationManagerBuilder(http.getSharedObject(ProviderManager.class));
//        authenticationManagerBuilder
//                .inMemoryAuthentication()
//                .withUser("admin").password(passwordEncoder.encode("password")).roles("ADMIN")
//                .and()
//                .withUser("trainer").password(passwordEncoder.encode("password")).roles("TRAINER");
//
//        return authenticationManagerBuilder.build();
//    }


}