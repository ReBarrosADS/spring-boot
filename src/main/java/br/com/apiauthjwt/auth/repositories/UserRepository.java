package br.com.apiauthjwt.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.apiauthjwt.auth.domain.user.User;


public interface UserRepository  extends JpaRepository<User, String>{
	//consultar usuario pelo login 
	//usado pelo spring securyti para consultar o usuario
	

	UserDetails findByLogin(String username);
	

}
