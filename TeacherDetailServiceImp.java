package com.cms.config;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.cms.model.Teacher;
import com.cms.repository.TeacherRepository;

@Component
public class TeacherDetailServiceImp implements UserDetailsService{

	@Autowired
	private TeacherRepository teacherRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// fetching user from database
		
		Teacher teacher = teacherRepository.getTeacherByUserName(username);
		
		if (teacher==null) {
			
			throw new UsernameNotFoundException("invalid email or password!");
			
		} 
		
		CustomTeacherDetail customUserDetails = new CustomTeacherDetail(teacher);
		
		return customUserDetails;
	}

}
 
