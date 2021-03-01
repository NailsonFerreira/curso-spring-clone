package br.com.nailson.cursomc.services;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import br.com.nailson.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService{
	
	@Value("${default.sender}")
	private String senderEmail;

	@Override
	public void sendOrderConfimationEmail(Pedido pedido) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(pedido);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(pedido.getCliente().getEmail());
		sm.setFrom(senderEmail);
		sm.setSubject("Pedido Confirmado! Cod: "+ pedido.getId());
		sm.setSentDate(pedido.getInstante());
		sm.setText(pedido.toString());
		return sm;
	}
}
