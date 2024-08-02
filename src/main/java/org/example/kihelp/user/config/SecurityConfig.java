package org.example.kihelp.user.config;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.user.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfig {
    private final UserServiceImpl userService;
    private final JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers(HttpMethod.POST,"/api/v1/users/user").permitAll()
                                .requestMatchers(HttpMethod.POST,"/api/v1/subjects/subject").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/subjects/subject/{subject_id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/subjects/subject/{subject_id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/v1/teachers/teacher").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/v1/teachers/teacher").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/teachers/teacher/{teacher_id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/teachers/teacher/{teacher_id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/v1/tasks/task").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/tasks/task/{task_id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/api/v1/tasks/task/{task_id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/api/v1/histories/history").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/api/v1/histories/history/user/{user_id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/v1/wallets/wallet").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/v1/wallets/wallet/{wallet_id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/wallets/wallet/{wallet_id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/wallets/wallet/user/{telegram_id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/v1/transactions/transaction").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/v1/transactions/transaction/all").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/v1/transactions/earnings").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/transactions/transaction/{transaction_id}").hasRole("ADMIN")
                                .requestMatchers("/api/v1/arguments/argument/**").hasRole("ADMIN")
                                .requestMatchers("/error").permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(userService);
        return authProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}
