package com.smart.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.smart.entities.User;

public class CustomUserDetails implements UserDetails
{
	
	private User user;
	
	public CustomUserDetails(User user) {
		this.user = user;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority auth = new SimpleGrantedAuthority(user.getRole());
		return Arrays.asList(auth);
	}

	public String getPassword() {
		
		return user.getPassword();
	}

	public String getUsername() {
		
		return user.getEmail();
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	

}
