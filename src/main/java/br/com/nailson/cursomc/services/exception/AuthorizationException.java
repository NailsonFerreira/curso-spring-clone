package br.com.nailson.cursomc.services.exception;

public class AuthorizationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AuthorizationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public AuthorizationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
}
