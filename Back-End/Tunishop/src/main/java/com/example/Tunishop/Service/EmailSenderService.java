package com.example.Tunishop.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class EmailSenderService implements EmailSender{

	private final static Logger LOGGER=org.slf4j.LoggerFactory.getLogger(EmailSenderService.class);
	
	@Autowired
	private JavaMailSender emailSender;
	
	@Override
	@Async
	public void send(String to, String email) {
		try {
			MimeMessage mimeMsg= emailSender.createMimeMessage();
			MimeMessageHelper helper= new MimeMessageHelper(mimeMsg,"utf-8");
			helper.setFrom("zak.saafi@gmail.com");
			helper.setTo(to);
			helper.setText(email,true);
			helper.setSubject("Activation de votre compte");
			emailSender.send(mimeMsg);
		} catch (MessagingException e) {
			LOGGER.error("Failed to send email",e);
			throw new IllegalStateException("Failed to send email");
		}
	}
}
