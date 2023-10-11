package br.com.apiauthjwt.auth.domain.user;

public record RegisterDTO(String login, String password, UserRole role) {

}
