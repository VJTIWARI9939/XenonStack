//package com.cms.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
//
//@Component
//@Configuration
//@EnableWebSecurity
//public class TeacherConfig extends WebSecurityConfigurerAdapter{
//	
//	@Bean
//	public UserDetailsService getUserDetailsService1()
//	{
//		return new UserDetailServiceImp();
//	}
//	
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder1()
//	{
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Bean
//	public DaoAuthenticationProvider authenticationProvider1() 
//	{
//		DaoAuthenticationProvider daoAuthenticationProvider1=new DaoAuthenticationProvider();
//		daoAuthenticationProvider1.setUserDetailsService(this.getUserDetailsService1());
//		daoAuthenticationProvider1.setPasswordEncoder(passwordEncoder1());
//		return daoAuthenticationProvider1;
//	}
//
//	// configure method	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	
//		auth.authenticationProvider(authenticationProvider1());
//		
//		//super.configure(auth);
//	}
//
// 
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/**").hasRole("TEACHER")
//		.antMatchers("/**").permitAll().and().formLogin()
//		 .defaultSuccessUrl("/index", true) .loginPage("/loginteacher").and().csrf().disable();
//	
////		http.authorizeRequests().antMatchers("/**").hasRole("TEACHER")
////		.antMatchers("/**").permitAll().and().formLogin()
////		.defaultSuccessUrl("/index", true).loginPage("/loginteacher").and().csrf().disable();
//	}
//		
//
//}