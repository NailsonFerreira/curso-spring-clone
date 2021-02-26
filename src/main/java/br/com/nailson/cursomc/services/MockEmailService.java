package br.com.nailson.cursomc.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailMessage{
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage mail) {
		LOG.info("============TESTANDO ENVIO DE EMAIL\"============");
		LOG.info(mail.toString());
		LOG.info("==================================================");
	}

}
