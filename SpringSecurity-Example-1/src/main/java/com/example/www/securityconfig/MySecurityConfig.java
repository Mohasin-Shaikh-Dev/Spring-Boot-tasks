package com.example.www.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.www.jwt.AuthEntryPointJwt;
import com.example.www.jwt.AuthTokenFilter;
import com.example.www.service.UserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MySecurityConfig {

	@Autowired
	private UserService usrService;
	
	 @Autowired
	    private AuthEntryPointJwt unauthorizedHandler;

	    @Bean
	    public AuthTokenFilter authenticationJwtTokenFilter() {
	        return new AuthTokenFilter();
	    }

	
	@Bean
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorizeRequests ->
        authorizeRequests.requestMatchers("/signin").permitAll()
                .anyRequest().authenticated());
		http.sessionManagement(
				session ->
                		session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler));
        
        //http.httpBasic(withDefaults());
        http.headers(headers -> headers
        .frameOptions(frameOptions -> frameOptions .sameOrigin()));
        http.csrf(csrf -> csrf.disable());
         http.addFilterBefore(authenticationJwtTokenFilter(),
        UsernamePasswordAuthenticationFilter.class);
        return http.build();
	}
	
	//create the user in memory
	@Bean
	public UserDetailsService userDetailsService()
	{
//		UserDetails user=User.withUsername("user")
//									.password(this.passwordEncoder().encode("user123"))
//									.roles("USER")
//									.build();
//		
//		UserDetails admin=User.withUsername("admin")
//			     			  .password(this.passwordEncoder().encode("admin123"))
//							  .roles("ADMIN")
//							  .build();
//		
//		JdbcUserDetailsManager jdbcUserDetailsManager=new JdbcUserDetailsManager(datasource);
//		jdbcUserDetailsManager.createUser(user);
//		jdbcUserDetailsManager.createUser(admin);
//		//return new InMemoryUserDetailsManager(user,admin);
//		return jdbcUserDetailsManager;
		return usrService;
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(usrService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}
