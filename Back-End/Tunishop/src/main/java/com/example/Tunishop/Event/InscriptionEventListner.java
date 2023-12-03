package com.example.Tunishop.Event;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.Tunishop.Entity.User;
import com.example.Tunishop.Service.EmailSenderService;
import com.example.Tunishop.Service.UserService;

@Component
public class InscriptionEventListner implements ApplicationListener<InscriptionEvent>{

	@Autowired
	private UserService userServ;
	
	@Autowired
	private EmailSenderService emailServ;
	
	@Override
	public void onApplicationEvent(InscriptionEvent event) {
	
		//créer Verification token pour le user avec l'url
		
		
		User user= event.getUser();
		String token =UUID.randomUUID().toString();
		userServ.saveVerificationToken(token,user);
		//l'url va être envoyé à l'utilisateur par mail
		
		String url=event.getApplicationURL()
				+ "/verifInscri?token="
				+token;
		
		emailServ.send(user.getEmail(), "Bienvenue dans TuniShop Mr/Mme "+user.getNom()+" "+user.getPrenom()+
		" Cliquez sur le lien pour activer votre compte : "+url);
	}
	
	
}
