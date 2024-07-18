//package com.novi.poffinhouse.config;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfig {
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
//        var auth = new DaoAuthenticationProvider();
//        auth.setPasswordEncoder(passwordEncoder);
//        auth.setUserDetailsService(customUserDetailsService);
//        return new ProviderManager(auth);
//    }
//
//    @Bean
//    public UserDetailsService authManager(PasswordEncoder passwordEncoder) {
//        InMemoryUserDetailsManager man = new InMemoryUserDetailsManager();
//
//
//        UserDetails u1 = User.withUsername("Karel")
//                .password(passwordEncoder
//                        .encode("Appel"))
//                .roles("USER")
//                .build();
//        man.createUser(u1);
//
//        UserDetails u2 = User.withUsername("Freek")
//                .password(passwordEncoder
//                        .encode("Appel"))
//                .roles("USER")
//                .build();
//        man.createUser(u2);
//
//        UserDetails u3 = User.withUsername("Ans")
//                .password(passwordEncoder
//                        .encode("Peer"))
//                .roles("ADMIN")
//                .build();
//        man.createUser(u3);
//
//        return man;
//    }
//
//
//    @Bean
//    protected SecurityFilterChain filter(HttpSecurity http) throws Exception {
//
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .httpBasic(Customizer.withDefaults())
//                .authorizeHttpRequests(auth ->
//                        auth
//                                .requestMatchers(HttpMethod.GET, "/berries").hasRole("ADMIN")
//                                .requestMatchers(HttpMethod.POST, "/berries").authenticated()
//                                .requestMatchers(HttpMethod.GET, "/berries/{username}").authenticated()
//                                .anyRequest().denyAll()
//
//                ).sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        return http.build();
//
//    }
//
//
//}