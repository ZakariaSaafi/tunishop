package com.example.Tunishop.Controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Tunishop.Entity.Acheteur;
import com.example.Tunishop.Entity.Admin;
import com.example.Tunishop.Entity.Livreur;
import com.example.Tunishop.Entity.ResponseMsg;
import com.example.Tunishop.Entity.Role;
import com.example.Tunishop.Entity.User;
import com.example.Tunishop.Entity.Vendeur;
import com.example.Tunishop.Model.UserModel;
import com.example.Tunishop.Repository.UserRepository;
import com.example.Tunishop.Service.UserService;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
public class LoginController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private UserRepository userRep;

	@Autowired
	PasswordEncoder encoder;
	
	@PostMapping("/admin-access")
	public ResponseEntity<?> authenticateAdmin(@Validated @RequestBody Admin adminModel){
		Authentication auth=authManager.authenticate(
				new UsernamePasswordAuthenticationToken(adminModel.getLogin_Username(), adminModel.getLogin_MotDePasse())
				);
		SecurityContextHolder.getContext().setAuthentication(auth);
		UserDetails userDetails=(UserDetails) auth.getPrincipal();
		return new ResponseEntity<>("Success !", HttpStatus.OK);
	}
	
	@GetMapping("/getRole/{id}")
	public ResponseEntity<?> getRole(@PathVariable(value="id") String id){
		Optional<User> user;
		user=userRep.findById(Long.parseLong(id));
		User currentUser=user.get();
		Set<Role> roles=currentUser.getRoles();
		String userRole="";
		for(Role role:roles) {
			userRole =role.getRole(); //avoir le role de logged user
		}
		return new ResponseEntity<>(userRole, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Validated @RequestBody UserModel userModel){
		//vérification de l'existance de l'utilisateur par mail
		ResponseMsg msg_1=new ResponseMsg();
		ResponseMsg msg_2=new ResponseMsg();
		msg_1.setMessage("Il n'existe pas un compte avec ce mail !");
		msg_2.setMessage("Veuillez activez votre compte pour se connecter!");
		
		if (!userServ.existsByEmail(userModel.getEmail())) {
			return new ResponseEntity<>(msg_1, HttpStatus.OK);
		}
		//vérification de l'activation de son compte avant le login
		if (!userServ.findUserByEmail(userModel.getEmail()).isEnabled()) {
			return new ResponseEntity<>(msg_2, HttpStatus.OK);
		}
		//vérification du mdp
		/*if(userServ.existsByEmail(userModel.getEmail()))
		{
				User u= userServ.findUserByEmail(userModel.getEmail());
				if(!u.getMdp().equals(userModel.getMdp()))
				{
					return new ResponseEntity<>("Mot de passe incorrect !", HttpStatus.OK);
				}
			
		}*/
		
		Authentication auth=authManager.authenticate(
				new UsernamePasswordAuthenticationToken(userModel.getEmail(), userModel.getMdp())
				);
		SecurityContextHolder.getContext().setAuthentication(auth);
		UserDetails userDetails=(UserDetails) auth.getPrincipal();
		User user = new User();
		Acheteur ach =new Acheteur();
		Vendeur vend =new Vendeur();
		Livreur liv =new Livreur();
		user=userServ.findUserByEmail(userModel.getEmail());
		user.setLogged(true);
		Set<Role> roles=user.getRoles();
		String userRole="";
		for(Role role:roles) {
			userRole =role.getRole(); //avoir le role de logged user
		}
		ResponseEntity<?> responseUser = null;
		userServ.updateUser(user);
		if(userRole.equals("VENDEUR")) {
			vend=userServ.findVendeur(userModel.getEmail());
			responseUser = new ResponseEntity<>(vend, HttpStatus.OK);
			return responseUser;
		}
		if(userRole.equals("ACHETEUR")) {
			ach=userServ.findAcheteur(userModel.getEmail());
			responseUser = new ResponseEntity<>(ach, HttpStatus.OK);
			return responseUser;
		}
		if(userRole.equals("LIVREUR")) {
			liv=userServ.findLivreur(userModel.getEmail());
			responseUser = new ResponseEntity<>(liv, HttpStatus.OK);
			return responseUser;
		}
		//return new ResponseEntity<>("Connexion avec succés",user.getRoles().toString(), HttpStatus.OK);
		
		return responseUser;
		
	}
		
			
		
		
		
}
