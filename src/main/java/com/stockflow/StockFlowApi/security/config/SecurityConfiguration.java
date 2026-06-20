package com.stockflow.StockFlowApi.security.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final JwtSecurityFilter jwtSecurityFilter;
    private final HandlerExceptionResolver resolver;

    public SecurityConfiguration(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver, JwtSecurityFilter jwtSecurityFilter) {
        this.jwtSecurityFilter = jwtSecurityFilter;
        this.resolver = resolver;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {

        return http
//                CSRF, desative apenas em ambiente de testes, geralmente resolve problema de endpoint bloqueados
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize

                        // Auth

                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()

                        // Produtos

                        .requestMatchers(HttpMethod.POST, "/api/produtos").hasAnyRole("ADMINISTRADOR", "GERENTE", "SUPERVISOR")
                        .requestMatchers(HttpMethod.PATCH, "/api/produtos/**").hasAnyRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/produtos/**").hasAnyRole("ADMINISTRADOR")

                        // Usuários

                        .requestMatchers(HttpMethod.GET, "/api/usuarios", "/api/usuarios/**").hasAnyRole("ADMINISTRADOR", "GERENTE")
                        .requestMatchers(HttpMethod.POST, "/api/usuarios").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PATCH, "/api/usuarios/**").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/usuarios/**").hasRole("ADMINISTRADOR")

                        // Categorias

                        .requestMatchers(HttpMethod.POST, "/api/categorias").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PATCH, "/api/categorias/**").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/categorias/**").hasRole("ADMINISTRADOR")

                        // Estoque

                        .requestMatchers(HttpMethod.PATCH, "/api/estoques/**").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/estoques/**").hasRole("ADMINISTRADOR")

                        // Movimentações

                        .requestMatchers(HttpMethod.POST, "/api/movimentacoes").hasAnyRole("GERENTE", "SUPERVISOR", "ENCARREGADO")

                        // Demais Rotas

                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtSecurityFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exHandler -> exHandler
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(403);
                            resolver.resolveException(request, response, null, accessDeniedException);
                        })
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(401);
                            resolver.resolveException(request, response, null, authException);
                        })
                )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
