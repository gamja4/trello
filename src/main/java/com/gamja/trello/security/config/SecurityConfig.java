package com.gamja.trello.security.config;

import com.gamja.trello.security.exception.AccessDeniedHandlerImpl;
import com.gamja.trello.security.exception.AuthenticationEntryPointImpl;
import com.gamja.trello.security.filter.JwtAuthenticationFilter;
import com.gamja.trello.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        return new JwtAuthenticationFilter(jwtService, userDetailsService);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.formLogin(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);

        http.cors((cors) -> cors.configurationSource(corsConfigurationSource()));    //cors 필터 등록

        http.sessionManagement(sessionManagement -> sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.exceptionHandling(handler -> {
            handler.accessDeniedHandler(new AccessDeniedHandlerImpl());
            handler.authenticationEntryPoint(new AuthenticationEntryPointImpl());
        });

        http.authorizeHttpRequests(requests -> requests
                .requestMatchers(HttpMethod.POST, "/api/users/signup").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/auth/token/reissue").permitAll()
                .anyRequest().authenticated()
        );

        http.addFilterAt(jwtAuthenticationFilter(jwtService, userDetailsService), BasicAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);   //내 서버가 응답할 때 json을 자바스크립트에서 처리할 수 있게 할지를 설정
        config.addAllowedOriginPattern("*");
        config.setAllowedOriginPatterns(List.of("http://localhost:5173"));
        config.addAllowedHeader("*");   // 모든 header에 응답 허용
//        config.addAllowedMethod("*");
        config.addAllowedMethod(HttpMethod.GET.name());
        config.addAllowedMethod(HttpMethod.POST.name());
        config.addAllowedMethod(HttpMethod.PUT.name());
        config.addAllowedMethod(HttpMethod.DELETE.name());
        config.addAllowedMethod(HttpMethod.OPTIONS.name());

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}