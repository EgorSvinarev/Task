package com.svinarev.task.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import com.auth0.jwt.algorithms.Algorithm;

import com.svinarev.task.services.UserService;
import com.svinarev.task.filters.JwtTokenFilter;
import com.svinarev.task.filters.CustomAuthenticationFilter;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:/application.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userDetailService;
	
	@Value("${task.jwt.secret}")
	private String jwtSecretKey;
	
	@Bean
	public Algorithm getJWTAlgorithm() {
		return Algorithm.HMAC256(jwtSecretKey.getBytes());  
	}
	
	@Bean
	public BCryptPasswordEncoder getEncoder() { 
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.sessionManagement().sessionCreationPolicy(STATELESS)
		.and()
			.authorizeRequests()
				.antMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")
				.antMatchers("/article/**").hasAnyAuthority("USER", "ADMIN")
				.antMatchers("/statistics/**").hasAnyAuthority("ADMIN")
			.anyRequest().authenticated()
		.and()
			.addFilter(new JwtTokenFilter(authenticationManagerBean(), getJWTAlgorithm()))
			.addFilterBefore(new CustomAuthenticationFilter(getJWTAlgorithm()), JwtTokenFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder authManager) throws Exception {
		authManager.userDetailsService(userDetailService).passwordEncoder(getEncoder());
	}
	
}
