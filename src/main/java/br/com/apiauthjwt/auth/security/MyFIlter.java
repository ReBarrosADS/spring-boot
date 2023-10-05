package br.com.apiauthjwt.auth.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyFilter extends OncePerRequestFilter {

	//da requesição até o controller voce pode passar por varios filtros
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if (request.getHeader("Authorization") != null) {
			// recupero o cabecalho
			Authentication auth = TokenUtil.decodeToken(request);
			// cabecalho de autorizacao existe, vejo se e valido
			if (auth != null) {
				// se o meu "token" for valida eu passo a requesicao para frente, indicando que ela esta autenticada
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
			
			else {
			 	// token existe, mas nao eh valido - preciso customizar a minha mensagem de erro 
				System.out.println("Error no token");
				ErrorDTO error = new ErrorDTO(401, "Usuario nao autorizado para este sistema");
				response.setStatus(error.getStatus());
				response.setContentType("application/json");
				ObjectMapper mapper = new ObjectMapper();
				response.getWriter().print(mapper.writeValueAsString(error));
				response.getWriter().flush();
				return;
				}
		}
		
	//passa a requesição para frente
		filterChain.doFilter(request, response);
	}
	
}
		

		
		
	


	

	


