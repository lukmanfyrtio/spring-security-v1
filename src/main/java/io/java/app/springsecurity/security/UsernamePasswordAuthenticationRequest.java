package io.java.app.springsecurity.security;

public class UsernamePasswordAuthenticationRequest {
	private String username;
	private String password;
	
	private UsernamePasswordAuthenticationRequest() {
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
