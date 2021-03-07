package br.com.nailson.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.nailson.cursomc.security.UserSS;

public class UserService {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
