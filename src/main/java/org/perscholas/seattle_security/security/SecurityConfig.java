package org.perscholas.seattle_security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;
	
	@Autowired
	public SecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Bean
	public PasswordEncoder pswdEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(pswdEncoder());
		return daoAuthenticationProvider;
	}

	@Override
	public void configure(WebSecurity web){
		web
		.ignoring()
		.antMatchers("/js/**", "/images/**", "/css/**", "/resources/**", "/scripts/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		// Disable CSRF for development and testing
		.csrf().disable()
		// Allows the H2 console to be shown in the browser
		.headers().frameOptions().disable()
		.and()
		
		.authorizeRequests()
		// Permit all users to access the register page and register post method
		.antMatchers("/login", "/register", "/registerNewUser").permitAll()
		// Restrict Admin page to "ADMIN" roles
		.antMatchers("/admin").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		// Redirect to access denied page if access not authorize for user
		.exceptionHandling().accessDeniedPage("/accessDenied")
		.and()
		
		.formLogin()
		.loginPage("/login")
		.permitAll()
		
		.and()
		.logout().invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/logoutSuccess").permitAll();
	}
	
}
