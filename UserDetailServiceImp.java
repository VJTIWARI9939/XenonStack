package com.cms.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.cms.model.Admin;
import com.cms.repository.AdminRepository;

@Component
public class UserDetailServiceImp implements UserDetailsService{

	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// fetching user from database
		
		Admin admin = adminRepository.getAdminByUserName(username);
		
		if (admin==null) {
			
			throw new UsernameNotFoundException("invalid email or password!");
			
		} 
		
		
		CustomUserDetail customUserDetails = new CustomUserDetail(admin);
		
		return customUserDetails;
	}

}
 
