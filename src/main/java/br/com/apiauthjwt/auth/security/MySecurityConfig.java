package br.com.apiauthjwt.auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    //quem é liberado, quem é bloqueado e qual o filtro vai fazer tratamento 
	    http.csrf().disable()             
	        .authorizeRequests()            //corrigido aqui
	        .requestMatchers(HttpMethod.GET, "/free").permitAll()
	        .anyRequest().authenticated().and().cors(); 

	    http.addFilterBefore(new MyFilter(), UsernamePasswordAuthenticationFilter.class);

	    return http.build();
		
	}
 
}
