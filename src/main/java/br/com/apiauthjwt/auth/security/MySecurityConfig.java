package br.com.apiauthjwt.auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class MySecurityConfig {
	//corrente de filtro de segurança, chamar os metodos para fazer validações no usuário
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
	    //quem é liberado, quem é bloqueado e qual o filtro vai fazer tratamento 
		return httpSecurity
				.csrf(csrf-> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
						//alterar depois para só admin criar novos usuaris
						.requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
						.requestMatchers(HttpMethod.POST, "/product").hasRole("ADMIN")
						.anyRequest().authenticated()
				)
				.build();
		
		
				
		/* metodo com http 
	    http.csrf().disable()             
	        .authorizeRequests()            //corrigido aqui
	        .requestMatchers(HttpMethod.GET, "/free").permitAll()
	        .anyRequest().authenticated().and().cors(); 

	    http.addFilterBefore(new MyFilter(), UsernamePasswordAuthenticationFilter.class);

	    return http.build();
		*/
	}
	
	@Bean
	//pegar a estancia do spring security
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
		
	}
	
	//cripitografar os dados para salvar no bd de forma segura
	//classe do spring security
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
				
	}
 
}
