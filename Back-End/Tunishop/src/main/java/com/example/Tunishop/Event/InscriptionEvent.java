package com.example.Tunishop.Event;

import org.springframework.context.ApplicationEvent;

import com.example.Tunishop.Entity.Acheteur;
import com.example.Tunishop.Entity.Livreur;
import com.example.Tunishop.Entity.User;
import com.example.Tunishop.Entity.Vendeur;



public class InscriptionEvent extends ApplicationEvent{

	private User user;
	private String applicationURL;
	
	
	public InscriptionEvent(User user,String applicationURL) {
		super(user);
		this.applicationURL=applicationURL;
		this.user=user;
	}
	
	//GETTERS && SETTERS
	public String getApplicationURL() {
		return applicationURL;
	}

	public void setApplicationURL(String applicationURL) {
		this.applicationURL = applicationURL;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}


	
	
	
	
	
	
	

}
