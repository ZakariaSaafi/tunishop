package com.example.Tunishop.Service;


import java.util.List;
import java.util.Optional;

import com.example.Tunishop.Entity.Acheteur;
import com.example.Tunishop.Entity.Livreur;
import com.example.Tunishop.Entity.Produit;
import com.example.Tunishop.Entity.User;
import com.example.Tunishop.Entity.Vendeur;
import com.example.Tunishop.Entity.VerificationToken;
import com.example.Tunishop.Entity.VerificationTokenAcheteur;
import com.example.Tunishop.Entity.VerificationTokenLivreur;
import com.example.Tunishop.Entity.VerificationTokenVendeur;
import com.example.Tunishop.Model.UserModel;


public interface UserService {

	User creerUser(UserModel userModel);
	Acheteur creerAcheteur(UserModel userModel);
	Vendeur creerVendeur(UserModel userModel);
	Livreur creerLivreur(UserModel userModel);

	void saveVerificationToken(String token,User user);
	void saveVerificationTokenAch(String token,Acheteur user);
	void saveVerificationTokenVend(String token,Vendeur user);
	void saveVerificationTokenLiv(String token,Livreur user);

	String validerVerificationToken(String token);
	String validerVerificationTokenAch(String token);
	String validerVerificationTokenVend(String token);
	String validerVerificationTokenLiv(String token);

	VerificationToken creerNewVerificationToken(String oldToken);
	VerificationTokenAcheteur creerNewVerificationTokenAch(String oldToken);
	VerificationTokenVendeur creerNewVerificationTokenVend(String oldToken);
	VerificationTokenLivreur creerNewVerificationTokenLiv(String oldToken);

	public User findUserByEmail(String email);
	public Acheteur findAcheteur(String email);
	public Vendeur findVendeur(String email);
	public Livreur findLivreur(String email);
/*
	void creerPasswordResetToken(User user, String token);

	String validerPasswordResetToken(String token);

	Optional<User> getUserByPasswordResetToken(String token);

	void changeMdp(User user, String nouveauMdp);

	boolean verifOldPassword(User user, String ancientMdp);*/


	
	
	boolean existsByEmailAch(String email);
	boolean existsByEmailVend(String email);
	boolean existsByEmailLiv(String email);
	boolean existsByEmail(String email);

	public void updateUser(User user);
	User updateUserInfo(UserModel userModel);
	Acheteur updateAch(UserModel userModel);
	Livreur updateLiv(UserModel userModel);
	Vendeur updateVend(UserModel userModel);
	public void initialisation();
	public User creerAdmin();
	Optional<Vendeur> findVendeurById(Long id);
	List<User> ListAllUsers();
	List<Acheteur> ListAllach();
	List<Vendeur> ListAllvend();
	List<Livreur> ListAllliv();
	
	
	
	
}
