package com.nmk.cardinal.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.nmk.cardinal.entities.User;
import com.nmk.cardinal.services.MyUserDetails;

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
		        .antMatchers("/api/**").permitAll() /* change back to authenticated once security is figured out */ // All other request for our API must be authorized.
		        .anyRequest().permitAll()               // All other requests are allowed without authentication.
		        .and()
		        .httpBasic();                           // Use HTTP Basic Authentication
			
			http
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			
			return http.build();
			
		}
		
//		@Bean
//	    public DataSource dataSource() {
//	        return new EmbeddedDatabaseBuilder()
//	            .setType(EmbeddedDatabaseType.H2)
//	            .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
//	            .build();
//	    }
//
//	    @Bean
//	    public UserDetailsManager users(DataSource dataSource) {
//	    	
//	        User temp = new User();
//	        temp.setUsername("username");
//	        temp.setPassword("password");
//	        temp.setRole("USER");
//	        
//	    	MyUserDetails user = new MyUserDetails(temp);
//	    	
//	        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//	        users.createUser(user);
//	        return users;
//	    }	

}
