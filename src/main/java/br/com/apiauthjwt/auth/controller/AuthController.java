package br.com.apiauthjwt.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	@GetMapping("/free")
	public String sayFreeHello() {
		return "Este endpoint eh com acesso liberado pela API";
	}
	
	@GetMapping("/auth")
	public String sayAuthHello() {
		return "Este endpoint precisa de autenticacao";
	}
}
