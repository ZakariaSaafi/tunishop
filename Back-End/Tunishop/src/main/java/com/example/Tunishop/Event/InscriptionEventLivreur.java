package com.example.Tunishop.Event;

import org.springframework.context.ApplicationEvent;

import com.example.Tunishop.Entity.Livreur;

public class InscriptionEventLivreur extends ApplicationEvent{

	private Livreur liv;
	private String applicationURL;
	
	public InscriptionEventLivreur(Livreur liv,String applicationURL) {
		super(liv);
		this.applicationURL=applicationURL;
		this.liv=liv;
	}
	
	//GETTERS && SETTERS
		public String getApplicationURL() {
			return applicationURL;
		}

		public void setApplicationURL(String applicationURL) {
			this.applicationURL = applicationURL;
		}
		
		public Livreur getLiv() {
			return liv;
		}
		public void setLiv(Livreur liv) {
			this.liv = liv;
		}
}
