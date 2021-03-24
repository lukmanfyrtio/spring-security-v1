package io.java.app.springsecurity.auth;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import io.java.app.springsecurity.security.ApplicationUserRoles;


@Repository("fake")
public class FakeApplicationDaoService implements ApplicationUserRepository{

	private final PasswordEncoder passwordEncoder;
	
	
	public FakeApplicationDaoService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Optional<ApplicationUsers> getByUsername(String username) {
		return getApplicationUser()
				.stream()
				.filter(applicationUsers -> username.equals(applicationUsers.getUsername()))
				.findFirst();
	}
	
	public List<ApplicationUsers>getApplicationUser(){
		List<ApplicationUsers>applicationUsers=Lists.newArrayList(
				new ApplicationUsers(ApplicationUserRoles.STUDENT.getGrantedAuthorities(), 
						passwordEncoder.encode("password123"), "linda", 
						true, true, true, true),
				new ApplicationUsers(ApplicationUserRoles.ADMIN.getGrantedAuthorities(), 
						passwordEncoder.encode("password123"), "admin", 
						true, true, true, true),
				new ApplicationUsers(ApplicationUserRoles.ADMINTRAINE.getGrantedAuthorities(), 
						passwordEncoder.encode("password123"), "adminTraine", 
						true, true, true, true));
		return applicationUsers;
	}

}
