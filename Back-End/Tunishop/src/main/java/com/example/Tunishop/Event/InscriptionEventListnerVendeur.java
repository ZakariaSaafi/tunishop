package com.example.Tunishop.Event;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.example.Tunishop.Entity.Vendeur;
import com.example.Tunishop.Service.EmailSenderService;
import com.example.Tunishop.Service.UserService;

@Component
public class InscriptionEventListnerVendeur implements ApplicationListener<InscriptionEventVendeur>{

	@Autowired
	private UserService userServ;
	
	@Autowired
	private EmailSenderService emailServ;
	
	@Override
	public void onApplicationEvent(InscriptionEventVendeur event) {
	
		//créer Verification token pour le user avec l'url
		
		
		Vendeur vend= event.getVend();
		String token =UUID.randomUUID().toString();
		userServ.saveVerificationTokenVend(token,vend);
		
		//l'url va être envoyé à l'utilisateur par mail
		
		String url=event.getApplicationURL()
				+ "/verifInscriVendeur?token="
				+token;
		
		emailServ.send(vend.getEmail(), "Welcome to TuniShop Mr(s) "+vend.getNom()+" "+vend.getPrenom()+
		" Click Here To Activate Your Account : "+url);
	}
	
	
}
