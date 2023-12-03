package com.example.Tunishop.Entity;

/**
 * @author user
 *
 */
public class Admin {

	private String login_Username;
	private String login_MotDePasse;
	private String role_Admin;
	
	
	public Admin(String login_Username, String login_MotDePasse,String role_Admin) {
		super();
		this.login_Username = login_Username;
		this.login_MotDePasse = login_MotDePasse;
		this.role_Admin=role_Admin;
	}
	
	
	public String getLogin_Username() {
		return login_Username;
	}
	public void setLogin_Username(String login_Username) {
		this.login_Username = login_Username;
	}
	public String getLogin_MotDePasse() {
		return login_MotDePasse;
	}
	public void setLogin_MotDePasse(String login_MotDePasse) {
		this.login_MotDePasse = login_MotDePasse;
	}


	public String getRole_Admin() {
		return role_Admin;
	}


	public void setRole_Admin(String role_Admin) {
		this.role_Admin = role_Admin;
	}
	
	
	
	
	
}
