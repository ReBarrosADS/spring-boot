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
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		//quem e liberado, quem e blloqueado e qual o filtro vai fazer tratamento 
		http.csrf().disable()             //desabilto o crsf(porque eu vou tratar a autenticacao
			.authorizeHttpRequests()            //agora as requisao http sao passiveis de autorizacao
			.requestMatchers(HttpMethod.GET, "/free").permitAll()
			.anyRequest().authenticated().and().cors(); // todas as outras urls tesao nescessidade de autenticacao e sofrerao restricao do cors
		
		http.addFilterBefore(new MyFIlter(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
		
		
		
		
		
	}
 
}
