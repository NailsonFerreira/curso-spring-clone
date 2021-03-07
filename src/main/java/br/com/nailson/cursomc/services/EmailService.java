package br.com.nailson.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import br.com.nailson.cursomc.domain.Cliente;
import br.com.nailson.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfimationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage mail);
	
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	
	void sendHtmlEmail(MimeMessage msg);

	void sendNewPassword(Cliente cli, String newPass);
}
