package com.example.Tunishop.Event;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.example.Tunishop.Entity.Livreur;
import com.example.Tunishop.Service.EmailSenderService;
import com.example.Tunishop.Service.UserService;

@Component
public class InscriptionEventListnerLivreur implements ApplicationListener<InscriptionEventLivreur>{

	@Autowired
	private UserService userServ;
	
	@Autowired
	private EmailSenderService emailServ;
	
	@Override
	public void onApplicationEvent(InscriptionEventLivreur event) {
	
		//créer Verification token pour le user avec l'url
		
		
		Livreur liv= event.getLiv();
		String token =UUID.randomUUID().toString();
		userServ.saveVerificationTokenLiv(token,liv);
		
		//l'url va être envoyé à l'utilisateur par mail
		
		String url=event.getApplicationURL()
				+ "/verifInscriLivreur?token="
				+token;
		
		emailServ.send(liv.getEmail(), "Welcome to TuniShop Mr(s) "+liv.getNom()+" "+liv.getPrenom()+
		" Click Here To Activate Your Account : "+url);
	}
	
	
}
