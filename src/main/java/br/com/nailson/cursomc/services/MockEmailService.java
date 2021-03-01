package br.com.nailson.cursomc.services;


import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService{
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage mail) {
		LOG.info("============TESTANDO ENVIO DE EMAIL\"============");
		LOG.info(mail.toString());
		LOG.info("==================================================");
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("============TESTANDO ENVIO DE EMAIL HTNL\"============");
		LOG.info(msg.toString());
		LOG.info("==================================================");
		
	}

}
