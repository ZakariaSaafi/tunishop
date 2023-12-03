package com.example.Tunishop.Event;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.example.Tunishop.Entity.Acheteur;
import com.example.Tunishop.Service.EmailSenderService;
import com.example.Tunishop.Service.UserService;

@Component
public class InscriptionEventListnerAcheteur implements ApplicationListener<InscriptionEventAcheteur>{

	@Autowired
	private UserService userServ;
	
	@Autowired
	private EmailSenderService emailServ;
	
	@Override
	public void onApplicationEvent(InscriptionEventAcheteur event) {
	
		//créer Verification token pour le user avec l'url
		
		
		Acheteur ach= event.getAch();
		String token =UUID.randomUUID().toString();
		userServ.saveVerificationTokenAch(token,ach);
		
		//l'url va être envoyé à l'utilisateur par mail
		
		String url=event.getApplicationURL()
				+ "/verifInscriAcheteur?token="
				+token;
		
		emailServ.send(ach.getEmail(), "Welcome to TuniShop Mr(s) "+ach.getNom()+" "+ach.getPrenom()+
		" Click Here To Activate Your Account : "+url);
	}
	
}
