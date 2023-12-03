package com.example.Tunishop.Model;

import lombok.Data;

@Data
public class PasswordModel {
	
	private String email;
	private String ancientMdp;
	private String nouveauMdp;

	//GETTERS && SETTERS
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAncientMdp() {
		return ancientMdp;
	}

	public void setAncientMdp(String ancientMdp) {
		this.ancientMdp = ancientMdp;
	}

	public String getNouveauMdp() {
		return nouveauMdp;
	}

	public void setNouveauMdp(String nouveauMdp) {
		this.nouveauMdp = nouveauMdp;
	}
	
	
	
	
	

}
