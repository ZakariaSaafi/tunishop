package com.example.Tunishop.Event;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import com.example.Tunishop.Entity.Acheteur;


public class InscriptionEventAcheteur extends ApplicationEvent{
	
	private Acheteur ach;
	private String applicationURL;
	
	public InscriptionEventAcheteur(Acheteur ach,String applicationURL) {
		super(ach);
		this.applicationURL=applicationURL;
		this.ach=ach;
	}
	
	//GETTERS && SETTERS
		public String getApplicationURL() {
			return applicationURL;
		}

		public void setApplicationURL(String applicationURL) {
			this.applicationURL = applicationURL;
		}
		
		public Acheteur getAch() {
			return ach;
		}
		public void setAch(Acheteur ach) {
			this.ach = ach;
		}

}
