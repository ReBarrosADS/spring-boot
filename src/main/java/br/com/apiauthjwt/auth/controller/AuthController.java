package br.com.apiauthjwt.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apiauthjwt.auth.domain.user.AuthentaticationDTO;
import br.com.apiauthjwt.auth.domain.user.RegisterDTO;
import br.com.apiauthjwt.auth.domain.user.User;
import br.com.apiauthjwt.auth.repositories.UserRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {
	
	@Autowired //declarando a classe
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository repository;


	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid AuthentaticationDTO data) {
		//forma de validar se a senha e email existe no bd
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
		//recebe por parametro um usarnamepassword token, usado no spring bot acima
		var auth = this.authenticationManager.authenticate(usernamePassword);
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/register")
	public  ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
		//
		if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();
		
		String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
		User newUser = new User(data.login(), encryptedPassword, data.role());
		
		//salvar o usuario no bd
		this.repository.save(newUser);
		
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping("/free")
	public String sayFreeHello() {
		return "Este endpoint eh com acesso liberado pela API";
	}
	
	@GetMapping("/autenti")
	public String sayAuthHello() {
		return "Este endpoint precisa de autenticacao";
	}
}
