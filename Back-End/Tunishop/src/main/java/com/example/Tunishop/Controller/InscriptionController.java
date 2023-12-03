package com.example.Tunishop.Controller;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Tunishop.Entity.Acheteur;
import com.example.Tunishop.Entity.Admin;
import com.example.Tunishop.Entity.Livreur;
import com.example.Tunishop.Entity.User;
import com.example.Tunishop.Entity.Vendeur;
import com.example.Tunishop.Entity.VerificationToken;
import com.example.Tunishop.Entity.VerificationTokenAcheteur;
import com.example.Tunishop.Entity.VerificationTokenLivreur;
import com.example.Tunishop.Entity.VerificationTokenVendeur;
import com.example.Tunishop.Event.InscriptionEvent;
import com.example.Tunishop.Event.InscriptionEventAcheteur;
import com.example.Tunishop.Event.InscriptionEventLivreur;
import com.example.Tunishop.Event.InscriptionEventVendeur;
import com.example.Tunishop.Model.PasswordModel;
import com.example.Tunishop.Model.UserModel;
import com.example.Tunishop.Service.EmailSenderService;
import com.example.Tunishop.Service.UserService;
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
public class InscriptionController {
	
	@Autowired
	private UserService userServ;
	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private EmailSenderService emailServ;
	
	@PostConstruct
	public void initialisation() {
		userServ.initialisation();
	}
	
	@GetMapping("/existByEmail/{email}")
	public ResponseEntity<?> existUser(@PathVariable(value="email")String email) {
		if (this.userServ.existsByEmail(email)) {
			return new ResponseEntity<>("Found !", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Not Found !", HttpStatus.OK);
		}
	}
	
	@PostMapping("/inscri")
	public String inscri(@RequestBody UserModel userModel, final HttpServletRequest request) {
		if (userServ.existsByEmail(userModel.getEmail())) {
			return "Il existe déja un compte avec le mail "+userModel.getEmail();
		}
			
		User user= userServ.creerUser(userModel);
		if(userModel.getRole().equals("ACHETEUR")) {return inscriAcheteur(userModel, request);}
		if(userModel.getRole().equals("VENDEUR")) {return inscriVendeur(userModel, request);}
		if(userModel.getRole().equals("LIVREUR")) {return inscriLivreur(userModel, request);}
		/*publisher.publishEvent(new InscriptionEvent(user,applicationUrl(request)));*/
		return "Vérifiez vos données";
		
	}
	
	@PostMapping("/inscriAcheteur")
	public String inscriAcheteur(@RequestBody UserModel userModel, final HttpServletRequest request) {
		/*if (userServ.existsByEmailAch(userModel.getEmail())) {
			return "Il existe déja un compte avec le mail "+userModel.getEmail();
		}*/
			
		Acheteur ach= userServ.creerAcheteur(userModel);
		publisher.publishEvent(new InscriptionEventAcheteur(ach,applicationUrl(request)));
		return "Inscription avec succées";
		
	}
	
	@PostMapping("/inscriVendeur")
	public String inscriVendeur(@RequestBody UserModel userModel, final HttpServletRequest request) {
		/*if (userServ.existsByEmailVend(userModel.getEmail())) {
			return "Il existe déja un compte avec le mail "+userModel.getEmail();
		}*/
		Vendeur vend= userServ.creerVendeur(userModel);
		publisher.publishEvent(new InscriptionEventVendeur(vend,applicationUrl(request)));
		return "Inscription avec succées";
		
	}
	@PostMapping("/inscriLivreur")
	public String inscriLivreur(@RequestBody UserModel userModel, final HttpServletRequest request) {
		/*if (userServ.existsByEmailLiv(userModel.getEmail())) {
			return "Il existe déja un compte avec le mail "+userModel.getEmail();
		}*/
		Livreur liv= userServ.creerLivreur(userModel);
		publisher.publishEvent(new InscriptionEventLivreur(liv,applicationUrl(request)));
		return "Inscription avec succées";
		
	}
	
	//**********************************************************************************************//
	//Partie I- Activation du compte
	
	//user
	@GetMapping("/verifInscri")
	public String verifInscription(@RequestParam("token") String token) {
		String resultat = userServ.validerVerificationToken(token);
		
		if(resultat.equalsIgnoreCase("valid")) {
			return "Vérification avec succées";
		}
		return "Vérification échoué: Vous avez dépassé le temps d'expiration";

	}
	
	//permet de renvoyer un nouveau url en cas d'expiration du token
	@GetMapping("/renvoieVerifToken")
	public String renvoieVerifToken(@RequestParam("token") String oldToken,HttpServletRequest request) {
		VerificationToken verifToken=userServ.creerNewVerificationToken(oldToken);
		User user =verifToken.getUser();
		renvoieVerifMail(user,applicationUrl(request), verifToken);
		return "Verification Link Sent";
	}

	private void renvoieVerifMail(User user, String applicationUrl,VerificationToken token) {
		String url=applicationUrl
				+ "/verifInscri?token="
				+token.getToken();
		//envoie du mail
		emailServ.send(user.getEmail(), "Welcome to TuniShop Mr(s) "+user.getNom()+" "+user.getPrenom()+
		" Click Here To Activate Your Account : "+url);
		//System.out.println("click the link to verify your account : "+url);
		
	}


	//acheteur 
	
	@GetMapping("/verifInscriAcheteur")
	public String verifInscriptionAch(@RequestParam("token") String token) {
		String resultat = userServ.validerVerificationTokenAch(token);
		
		if(resultat.equalsIgnoreCase("valid")) {
			return "Vérification avec succées";
		}
		return "Vérification échoué: Vous avez dépassé le temps d'expiration";

	}
	
	//permet de renvoyer un nouveau url en cas d'expiration du token
	@GetMapping("/renvoieVerifTokenAcheteur")
	public String renvoieVerifTokenAch(@RequestParam("token") String oldToken,HttpServletRequest request) {
		VerificationTokenAcheteur verifToken=userServ.creerNewVerificationTokenAch(oldToken);
		Acheteur user =verifToken.getAch();
		renvoieVerifMailAch(user,applicationUrl(request), verifToken);
		return "Verification Link Sent";
	}

	private void renvoieVerifMailAch(Acheteur user, String applicationUrl,VerificationTokenAcheteur token) {
		String url=applicationUrl
				+ "/verifInscriAcheteur?token="
				+token.getToken();
		//envoie du mail
		emailServ.send(user.getEmail(), "Welcome to TuniShop Mr(s) "+user.getNom()+" "+user.getPrenom()+
		" Click Here To Activate Your Account : "+url);
		//System.out.println("click the link to verify your account : "+url);
		
	}
	
	//vendeur
	
	@GetMapping("/verifInscriVendeur")
	public String verifInscriptionVend(@RequestParam("token") String token) {
		String resultat = userServ.validerVerificationTokenVend(token);
		
		if(resultat.equalsIgnoreCase("valid")) {
			return "Vérification avec succées";
		}
		return "Vérification échoué: Vous avez dépassé le temps d'expiration";

	}
	
	//permet de renvoyer un nouveau url en cas d'expiration du token
	@GetMapping("/renvoieVerifTokenVendeur")
	public String renvoieVerifTokenVend(@RequestParam("token") String oldToken,HttpServletRequest request) {
		VerificationTokenVendeur verifToken=userServ.creerNewVerificationTokenVend(oldToken);
		Vendeur user =verifToken.getVend();
		renvoieVerifMailVend(user,applicationUrl(request), verifToken);
		return "Verification Link Sent";
	}

	private void renvoieVerifMailVend(Vendeur user, String applicationUrl,VerificationTokenVendeur token) {
		String url=applicationUrl
				+ "/verifInscriVendeur?token="
				+token.getToken();
		//envoie du mail
		emailServ.send(user.getEmail(), "Welcome to TuniShop Mr(s) "+user.getNom()+" "+user.getPrenom()+
		" Click Here To Activate Your Account : "+url);
		//System.out.println("click the link to verify your account : "+url);
		
	}
	
	//livreur
	
	@GetMapping("/verifInscriLivreur")
	public String verifInscriptionLiv(@RequestParam("token") String token) {
		String resultat = userServ.validerVerificationTokenLiv(token);
		
		if(resultat.equalsIgnoreCase("valid")) {
			return "Vérification avec succées";
		}
		return "Vérification échoué: Vous avez dépassé le temps d'expiration";

	}
	
	//permet de renvoyer un nouveau url en cas d'expiration du token
	@GetMapping("/renvoieVerifTokenLivreur")
	public String renvoieVerifTokenLiv(@RequestParam("token") String oldToken,HttpServletRequest request) {
		VerificationTokenLivreur verifToken=userServ.creerNewVerificationTokenLiv(oldToken);
		Livreur user =verifToken.getLiv();
		renvoieVerifMailLiv(user,applicationUrl(request), verifToken);
		return "Verification Link Sent";
	}

	private void renvoieVerifMailLiv(Livreur user, String applicationUrl,VerificationTokenLivreur token) {
		String url=applicationUrl
				+ "/verifInscriLivreur?token="
				+token.getToken();
		//envoie du mail
		emailServ.send(user.getEmail(), "Welcome to TuniShop Mr(s) "+user.getNom()+" "+user.getPrenom()+
		" Click Here To Activate Your Account : "+url);
		//System.out.println("click the link to verify your account : "+url);
		
	}
//*************************************************************************************************************//
	//méthode pour la création du lien d'activation du compte
	private String applicationUrl(HttpServletRequest request) {
		return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
	}
	
	//**********************************************************************************************//
	/*
	//Partie II- Recouvrir le mot de passe
	
	@PostMapping("/resetPassword")
	public String  passwordRecovery(@RequestBody PasswordModel passModel, HttpServletRequest request) {
		User user=userServ.findUserByEmail(passModel.getEmail());
		String url="";
		if (user!=null) {
			String token = UUID.randomUUID().toString();
			userServ.creerPasswordResetToken(user,token);
			url=passwordResetTokenMail(user,applicationUrl(request),token);
		}
		return url;
		
	}
	
	@PostMapping("/savePassword")
	public String savePassword(@RequestParam ("token")String token, @RequestBody PasswordModel passModel) {
		String resultat=userServ.validerPasswordResetToken(token);
		if(!resultat.equalsIgnoreCase("valid")) {
			return "Invalid Token";
		}
		Optional<User> user= userServ.getUserByPasswordResetToken(token);
		if(user.isPresent()) {
			userServ.changeMdp(user.get(),passModel.getNouveauMdp());
			return "Recovrey With Success";
		}else {
			return "Invalid Token";
		}
	}
	
	private String passwordResetTokenMail(User user, String applicationUrl,String token) {
		String url=applicationUrl
				+ "/savePassword?token="
				+token;
		
		emailServ.send(user.getEmail(), "Welcome to TuniShop Mr(s) "+user.getNom()+" "+user.getPrenom()+
				" Click Here To to reset your password : "+url);
		return"mail sent";
	}
	
	//**********************************************************************************************/
	
	//PARTIE III- Changement de mot de passe 
	//Inputs : Ancient mot de passe && Nouveau mot de passe
	/*
	@PostMapping("/changePassword")
	public String changePassword(@RequestBody PasswordModel passModel) {
		User user=userServ.findUserByEmail(passModel.getEmail());
		if(!userServ.verifOldPassword(user,passModel.getAncientMdp())) {
			return "Invalid Old Password";
		}
		
		//Save New Password
		userServ.changeMdp(user, passModel.getNouveauMdp());
		return "Password Changed Successfully";
	}
	
	
	//**********************************************************************************************/
	
	
	
	
	

}
