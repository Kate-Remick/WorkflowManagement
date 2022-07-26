package com.nmk.cardinal.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
		@Bean
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http.authenticationProvider(authenticationProvider());
			
			http.csrf().disable()
			.authorizeRequests()
			 .antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll() // For CORS, the preflight request
		        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()     // will hit the OPTIONS on the route
		        .antMatchers(HttpMethod.GET, "/api/**").permitAll()     // Allow readonly access to our API
		        .antMatchers("/api/**").authenticated() // All other request for our API must be authorized.
		        .anyRequest().permitAll()               // All other requests are allowed without authentication.
		        .and()
		        .httpBasic();                           // Use HTTP Basic Authentication
			
			http
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			
			return http.build();
			
		}
		
		@Bean
		public DaoAuthenticationProvider authenticationProvider() {
		    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		     
		    authProvider.setUserDetailsService(userDetailsService);
		    authProvider.setPasswordEncoder(encoder);
		 
		    return authProvider;
		}
		
		
	@Autowired
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        // Check if username/password are valid, and user currently allowed to authenticate
	        String userQuery = "SELECT username, password, enabled FROM user WHERE username=?";
	        // Check what authorities the user has
	        String authQuery = "SELECT username, role FROM user WHERE username=?";
	        auth
	        .jdbcAuthentication()
	        .dataSource(dataSource)
	        .usersByUsernameQuery(userQuery)
	        .authoritiesByUsernameQuery(authQuery)
	        .passwordEncoder(encoder);
	    }

}
