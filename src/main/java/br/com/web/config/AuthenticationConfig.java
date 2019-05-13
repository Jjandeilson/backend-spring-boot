package br.com.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class AuthenticationConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public AuthenticationManager authenticationManeger() throws Exception{
		return super.authenticationManager();
	}
}
