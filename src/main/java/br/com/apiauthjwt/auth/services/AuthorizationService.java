package br.com.apiauthjwt.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.apiauthjwt.auth.repositories.UserRepository;

@Service
public class AuthorizationService implements UserDetailsService {
	// consultar os usuarios no banco de dados po baixo dos panos pelo spring security
	@Autowired
	UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return repository.findByLogin(username);
	}
	


}
