package br.com.apiauthjwt.auth.security;

import java.io.IOException;

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
		
		System.out.println("Debug - requesicao passou pelo filtro");
		
		//passa a requesição
		filterChain.doFilter(request, response);
		
	}

	

}
