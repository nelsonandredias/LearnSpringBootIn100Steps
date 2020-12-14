package com.polarising.spring.boot.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	//Setting global security configurations
	
		// step1. Authentication - creation of two users with different roles access
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			
			//We are adding password storage format, which is {noop} for plain text passwords
			
			auth.inMemoryAuthentication()
	    			.withUser("user1")
	    				.password("{noop}secret1")
	    					.roles("USER")
	    			.and()
	    			.withUser("admin1")
    					.password("{noop}secret1")
    						.roles("USER", "ADMIN");
	    }
	
		
		// step2. Authorization - define which roles have which accesses
	    protected void configure(HttpSecurity http) throws Exception {
			
			/*
			 * basically:
			 * we are permitting everyone with "USER" role to access surveys (/surveys/**)
			 * we are permitting everyone with "USER" role to access surveys (/users/**)
			 * 
			 *otherwise, it must have an "ADMIN" role
			 */
			
	        http.httpBasic().and()
	        	.authorizeRequests()
	        		.antMatchers("/surveys/**").hasRole("USER")
	        		.antMatchers("/users/**").hasRole("USER")
	                .antMatchers("/**").hasRole("ADMIN")
	                .and().formLogin();
	        
	        http.csrf().disable();
	        http.headers().frameOptions().disable();
	    }
}
