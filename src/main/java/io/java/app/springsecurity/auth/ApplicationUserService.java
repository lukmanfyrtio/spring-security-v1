package io.java.app.springsecurity.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService{
	
	
	private final ApplicationUserRepository userRepo;
	
	

	@Autowired
	public ApplicationUserService(@Qualifier("fake") ApplicationUserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}




	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			return userRepo.getByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
	}
}
