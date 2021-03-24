package io.java.app.springsecurity.security;

import static io.java.app.springsecurity.security.ApplicationUserPermission.COURSE_READ;
import static io.java.app.springsecurity.security.ApplicationUserPermission.COURSE_WRITE;
import static io.java.app.springsecurity.security.ApplicationUserPermission.STUDENT_READ;
import static io.java.app.springsecurity.security.ApplicationUserPermission.STUDENT_WRITE;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

public enum ApplicationUserRoles {
	STUDENT(Sets.newHashSet()),
	ADMIN(Sets.newHashSet(COURSE_WRITE,COURSE_READ,STUDENT_READ,STUDENT_WRITE)),
	ADMINTRAINE(Sets.newHashSet(COURSE_READ,STUDENT_READ));
	

	private final Set<ApplicationUserPermission>permissions;

	 ApplicationUserRoles(Set<ApplicationUserPermission> permissions) {
		this.permissions = permissions;
	}

	 public Set<ApplicationUserPermission> getPermissions() {
		return permissions;
	}
	 
	 public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
		 Set<SimpleGrantedAuthority> permissions=getPermissions().stream()
		 	.map(permision->new SimpleGrantedAuthority(permision.getPermission())).collect(Collectors.toSet());
		 permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
		 return permissions;
	 }
}
