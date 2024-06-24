package com.RNE.referentiel;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

	private JwtAuthConverter jwtAuthConverter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(ar -> ar.anyRequest().authenticated())
				.oauth2ResourceServer(ors -> ors.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)))
				.sessionManagement(sm -> sm.sessionCreationPolicy(STATELESS)).build();
	}
}