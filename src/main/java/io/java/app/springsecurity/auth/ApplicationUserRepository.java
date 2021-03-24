package io.java.app.springsecurity.auth;

import java.util.Optional;

public interface ApplicationUserRepository {

	 Optional<ApplicationUsers> getByUsername(String username);
}
