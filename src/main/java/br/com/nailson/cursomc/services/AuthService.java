package br.com.nailson.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.nailson.cursomc.domain.Cliente;
import br.com.nailson.cursomc.repositories.ClienteRepository;
import br.com.nailson.cursomc.services.exception.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private EmailService emailS;

	private Random random = new Random();

	public void sendNewPassword(String email) {
		Cliente cli = repo.findByEmail(email);

		if (cli == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}

		String newPass = newPassword();
		cli.setSenha(pe.encode(newPass));
		repo.save(cli);
		emailS.sendNewPassword(cli, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int cod = random.nextInt(3);
		switch (cod) {
		case 0:
			return (char) (random.nextInt(10) + 48);
		case 1:
			return (char) (random.nextInt(26) + 65);

		default:
			return (char) (random.nextInt(26) + 97);
		}
	}

}
