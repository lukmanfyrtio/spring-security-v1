package io.java.app.springsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.java.app.springsecurity.auth.ApplicationUserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;
	
	private final ApplicationUserService applicationUserService;
	

	public SecurityConfig(PasswordEncoder passwordEncoder,ApplicationUserService applicationUserService) {
		this.applicationUserService = applicationUserService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.csrf().disable()
				.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilter(new JwtUsernamePasswordAuthenticationFilter(authenticationManager()))
				.addFilterAfter(new JwtTokenVerifier(), JwtUsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers("/login", "/logout", "/", "index", "/css/*", "/js/*")
				.permitAll()
				.antMatchers("/api/**").hasRole(ApplicationUserRoles.STUDENT.name())
				.anyRequest()
				.authenticated();
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(applicationUserService);
		return provider;
	}

}