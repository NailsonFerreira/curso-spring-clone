package br.com.nailson.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.nailson.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfimationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage mail);
}
