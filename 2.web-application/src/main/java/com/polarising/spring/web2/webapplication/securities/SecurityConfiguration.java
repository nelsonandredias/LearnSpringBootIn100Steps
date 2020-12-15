package com.polarising.spring.web2.webapplication.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	//Setting global security configurations
	
	// create user - nelson/pass
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		
		//We are adding password storage format, which is {noop} for plain text passwords
		
		auth.inMemoryAuthentication()
    			.withUser("nelson").password("{noop}pass")
    				.roles("USER", "ADMIN");
    }
	
	
	//Take user to a Login page
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		
		/*
		 * basically, we are permitting everyone to login (/login)
		 * 
		 * however, if someone wants to access any of the "todo" pages, it must have a "USER" ROLE.
		 * otherwise, a login form should be presented
		 */
		
        http.authorizeRequests().antMatchers("/login", "/h2-console/**").permitAll()
                .antMatchers("/", "/*todo*/**").access("hasRole('USER')").and()
                .formLogin();
        
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
