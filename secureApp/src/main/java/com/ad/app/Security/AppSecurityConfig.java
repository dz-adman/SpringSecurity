package com.ad.app.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class AppSecurityConfig extends WebSecurityConfigurerAdapter
{
	/*
	@Autowired
	private UserDetailsService userDetailsService;
	
	//Method to lookup username-password in DB for authentication
	@Bean
	public AuthenticationProvider authProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		
		return provider;
	}
	*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http
			.csrf().disable()
			.authorizeRequests().antMatchers("/login").permitAll()
			.anyRequest().authenticated()
			.and()
			/*
			.formLogin()
			.loginPage("/login").permitAll()
			.and()
			*/
			.logout().invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			//.logoutSuccessUrl("/logoutSuccess").permitAll();
			.logoutSuccessUrl("/oauth/revoke-token").permitAll();
	}
	
	//Spring-Security login method
	/*
	@Override
	   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.inMemoryAuthentication()
	    .passwordEncoder(passwordEncoder())
	    .withUser("adman")
	    .password(passwordEncoder().encode("adman"))
	    .roles("ADMIN");
	   }
	*/
	   
}
