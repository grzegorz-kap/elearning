package com.grzk.elearning.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService customUserDetailsService;

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;

	@Autowired
	public void configGlobal(AuthenticationManagerBuilder auth)
			throws Exception {

		auth.userDetailsService(this.customUserDetailsService).passwordEncoder(
				passwordEncoder());
	}

	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().csrfTokenRepository(csrfTokenRepository())
		.and()
			.logout()
				.logoutSuccessHandler(logoutSuccessHandler)
				.permitAll()
		.and()
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/webjars/**").permitAll()
				.antMatchers("/static/**").permitAll()
				.antMatchers("/register").anonymous()
				.antMatchers("/login").anonymous()
				.antMatchers("/**").hasRole("USER")
		.and()
			.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager myAuthenticationManager() throws Exception {
		return super.authenticationManagerBean();
	}
	
	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}

}
