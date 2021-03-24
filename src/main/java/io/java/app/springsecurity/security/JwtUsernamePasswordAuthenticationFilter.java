package io.java.app.springsecurity.security;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private final AuthenticationManager authenticationManager;
	
	
	public JwtUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
		 this.authenticationManager=authenticationManager;
	}


	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, 
												HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		try {
			UsernamePasswordAuthenticationRequest authenticationRequest=new ObjectMapper().readValue(request.getInputStream(), UsernamePasswordAuthenticationRequest.class);
			Authentication authentication=new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), 
					authenticationRequest.getPassword());
			Authentication authenticate=authenticationManager.authenticate(authentication);
			return authenticate;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, 
											HttpServletResponse response, 	
											FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String authKey="thismustbesecureandnoonetoknowthisbecauseissecret";
		String token=Jwts.builder()
		.setSubject(authResult.getName())
		.claim("authorities", authResult.getAuthorities()).setIssuedAt(new Date())
		.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
		.signWith(Keys.hmacShaKeyFor(authKey.getBytes())).compact();
		
		response.addHeader("Authorization", "Bearer "+token);
	}

}
