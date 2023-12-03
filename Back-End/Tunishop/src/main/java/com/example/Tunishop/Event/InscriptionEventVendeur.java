package com.example.Tunishop.Event;

import org.springframework.context.ApplicationEvent;

import com.example.Tunishop.Entity.Vendeur;



public class InscriptionEventVendeur extends ApplicationEvent{

	private Vendeur vend;
	private String applicationURL;
	
	public InscriptionEventVendeur(Vendeur ven,String applicationURL) {
		super(ven);
		this.applicationURL=applicationURL;
		this.vend=ven;
	}
	
	//GETTERS && SETTERS
		public String getApplicationURL() {
			return applicationURL;
		}

		public void setApplicationURL(String applicationURL) {
			this.applicationURL = applicationURL;
		}
		
		public Vendeur getVend() {
			return vend;
		}
		public void setVend(Vendeur vend) {
			this.vend = vend;
		}
	

}


