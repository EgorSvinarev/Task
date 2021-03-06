package com.svinarev.task.filters;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;

import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.svinarev.task.entities.User;

public class JwtTokenFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	private final Algorithm jwtAlgorithm;
	
	public JwtTokenFilter(AuthenticationManager authenticationManager, Algorithm algo) {
		this.authenticationManager = authenticationManager;
		this.jwtAlgorithm = algo;
	}
	
	@Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, 
												HttpServletResponse response) throws AuthenticationException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		return authenticationManager.authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, 
											HttpServletResponse response, 
											FilterChain chain,
											Authentication authResult) throws IOException, ServletException {
		
		User user = (User) authResult.getPrincipal();
		
		String accessToken = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
				.withIssuer(request.getRequestURL().toString())
				.withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(jwtAlgorithm);
		
		String refreshToken = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
				.withIssuer(request.getRequestURL().toString())
				.sign(jwtAlgorithm);

		Map<String, String> tokens = new HashMap<>();
		tokens.put("access_token", accessToken);
		tokens.put("refresh_token", refreshToken);
		
		response.setContentType(APPLICATION_JSON_VALUE);
		
		new ObjectMapper().writeValue(response.getOutputStream(), tokens);
		
	}
	
	
	
}
