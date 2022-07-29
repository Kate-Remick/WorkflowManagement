package com.nmk.cardinal.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Bean
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http.csrf().disable()
			.authorizeRequests()
			 .antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll() // For CORS, the preflight request
		        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()     // will hit the OPTIONS on the route
		        .antMatchers(HttpMethod.GET, "/api/**").permitAll()     // Allow readonly access to our API
<<<<<<< HEAD
		        .antMatchers("/api/**").authenticated() // All other request for our API must be authorized.
=======
		        .antMatchers("/api/**").authenticated() /* change back to authenticated once security is figured out */ // All other request for our API must be authorized.
>>>>>>> fe533a42b99a9b5648d7f62b1dfb2bb940cffbec
		        .anyRequest().permitAll()               // All other requests are allowed without authentication.
		        .and()
		        .httpBasic();                           // Use HTTP Basic Authentication
			
			http
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			
			return http.build();
			
		}
<<<<<<< HEAD
//		
//		 public UserDetailsManager users(DataSource dataSource) throws Exception {
//		        // Check if username/password are valid, and user currently allowed to authenticate
//		        String userQuery = "SELECT username, password, enabled FROM user WHERE username=?";
//		        // Check what authorities the user has
//		        String authQuery = "SELECT username, role FROM user WHERE username=?";
//		        
//		        
//		        auth
//		        .jdbcAuthentication()
//		        .dataSource(dataSource)
//		        .usersByUsernameQuery(userQuery)
//		        .authoritiesByUsernameQuery(authQuery)
//		        /*.passwordEncoder(encoder)*/;
//		    }
//		
=======
	
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
>>>>>>> fe533a42b99a9b5648d7f62b1dfb2bb940cffbec

}
