package com.example.Tunishop.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)


public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private static final String[] WHITE_LIST_URLS= {
			"/changePassword","/savePassword",
			"/resetPassword",
			"/login**","/inscri**","/verifInscri*",
			"/renvoieVerifToken*",
			"/Produit/**","/Categorie/**",
			"/existByEmail/**",
			"/Vendeur/Produit/**",
			"/getRole/**",
			"/Panier/**",
			"/Commande/**",
			"/File/**",
			"/User/**"
			};
	

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public PasswordEncoder mdpEncoder() {
		return new BCryptPasswordEncoder(11);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.cors()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeHttpRequests()
			.antMatchers(WHITE_LIST_URLS).permitAll()
			.antMatchers("/acheteur").hasAuthority("ACHETEUR")
			.antMatchers("/vendeur").hasAuthority("VENDEUR")
			.antMatchers("/livreur").hasAuthority("LIVREUR")
			.antMatchers("/admin-access").hasAuthority("ADMIN")
			.anyRequest()
			.authenticated();
			//.exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
			
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
			
			
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(mdpEncoder());
		return provider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
	
	


}
