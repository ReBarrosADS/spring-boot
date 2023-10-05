package br.com.apiauthjwt.auth.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyFIlter extends OncePerRequestFilter {

	//da requesição até o controller voce pode passar por varios filtros
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Authentication auth = TokenUtil.decodeToken(request);
		if (auth != null) {
			if (TokenUtil.decodeToken(request) != null) {
				// se o meu "token" for valida eu passo a requesicao para frente, indicando que ela esta autenticada
				SecurityContextHolder.getContext().setAuthentication(null);
			}
		}

		//passa a requesição
		filterChain.doFilter(request, response);
		
	}

	

}
