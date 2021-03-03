package com.smart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.smart.dao.UserRepository;
import com.smart.entities.User;




public class UserDetailServiceImpl implements UserDetailsService
{
	
	@Autowired
	private UserRepository userRepository;
	

	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException
	{
		
		User user = userRepository.getUserByUsername(name);
		
		if(user == null)
		{
			throw new UsernameNotFoundException("user not found!!!");
		}
		
		CustomUserDetails custom = new CustomUserDetails(user);
		
		return custom;
	}
	
	

}
