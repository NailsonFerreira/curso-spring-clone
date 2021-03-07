package br.com.nailson.cursomc.services;


import java.util.Calendar;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.nailson.cursomc.domain.Cliente;
import br.com.nailson.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService{
	
	@Value("${default.sender}")
	private String senderEmail;
	
	@Autowired
	private TemplateEngine tempEngine;
	
	@Autowired(required = true)
	private JavaMailSender javaMail;

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
	
	protected String htmlFromTemplatePedido(Pedido obj) {
		Context context = new Context(); 
		context.setVariable("pedido", obj);
		return tempEngine.process("email/confirmacaoPedido", context);
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido obj) {
		MimeMessage mm;
		try {
			mm = prepareMimeMessageFromPedido(obj);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			e.printStackTrace();
			sendOrderConfimationEmail(obj);
		}
		
	}

	protected MimeMessage prepareMimeMessageFromPedido(Pedido obj) throws MessagingException {
		MimeMessage mime = javaMail.createMimeMessage();
		MimeMessageHelper mimeHelper = new MimeMessageHelper(mime, true);
		mimeHelper.setTo(obj.getCliente().getEmail());
		mimeHelper.setFrom(senderEmail);
		mimeHelper.setSubject("Pedido confirmado! Cod.:" + obj.getId());
		mimeHelper.setSentDate(Calendar.getInstance().getTime());
		mimeHelper.setText(htmlFromTemplatePedido(obj), true);
		
		return mime;
	};
	
	@Override
	public void sendNewPassword(Cliente cli, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(cli, newPass);
		sendEmail(sm);
		
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Cliente cli, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(cli.getEmail());
		sm.setFrom(senderEmail);
		sm.setSubject("Solicitação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha gerada: "+newPass);
		return sm;
	}
}
