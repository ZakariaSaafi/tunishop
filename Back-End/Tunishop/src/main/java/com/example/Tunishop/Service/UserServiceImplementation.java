package com.example.Tunishop.Service;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Tunishop.Entity.Acheteur;
import com.example.Tunishop.Entity.Admin;
import com.example.Tunishop.Entity.Categorie;
import com.example.Tunishop.Entity.Livreur;
import com.example.Tunishop.Entity.Panier;
import com.example.Tunishop.Entity.PasswordResetToken;
import com.example.Tunishop.Entity.User;
import com.example.Tunishop.Entity.Vendeur;
import com.example.Tunishop.Entity.VerificationToken;
import com.example.Tunishop.Entity.VerificationTokenAcheteur;
import com.example.Tunishop.Entity.VerificationTokenLivreur;
import com.example.Tunishop.Entity.VerificationTokenVendeur;
import com.example.Tunishop.Model.UserModel;
import com.example.Tunishop.Repository.AcheteurRepository;
import com.example.Tunishop.Repository.CategorieRepository;
import com.example.Tunishop.Repository.LivreurRepository;
import com.example.Tunishop.Repository.PasswordResetTokenRepository;
import com.example.Tunishop.Repository.RoleRepository;
import com.example.Tunishop.Repository.UserRepository;
import com.example.Tunishop.Repository.VendeurRepository;
import com.example.Tunishop.Repository.VerificationTokenAcheteurRepository;
import com.example.Tunishop.Repository.VerificationTokenLivreurRepository;
import com.example.Tunishop.Repository.VerificationTokenRepository;
import com.example.Tunishop.Repository.VerificationTokenVendeurRepository;
import com.example.Tunishop.ServiceImplementation.PanierServiceImplementation;
import com.example.Tunishop.Entity.Role;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AcheteurRepository achRepository;
	@Autowired
	private VendeurRepository vendRepository;
	@Autowired
	private LivreurRepository livRepository;
	
	@Autowired
	private PasswordEncoder mdpEncoder;
	
	@Autowired
	private VerificationTokenRepository verifTokenRepository;
	
	@Autowired
	private VerificationTokenAcheteurRepository verifTokenRepositoryAch;
	
	@Autowired
	private VerificationTokenVendeurRepository verifTokenRepositoryVend;
	
	@Autowired
	private VerificationTokenLivreurRepository verifTokenRepositoryLiv;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private CategorieRepository categorieRepository;
	
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository; 
	
	@Autowired PanierService panierServ;
	
	@Transactional
	public void initialisation() {
		
		//Initialisation des roles
		Role adminRole=new Role("ADMIN");
		Role acheteurRole=new Role("ACHETEUR");
		Role vendeurRole=new Role("VENDEUR");
		Role livreurRole=new Role("LIVREUR");
		roleRepository.save(adminRole);
		roleRepository.save(acheteurRole);
		roleRepository.save(vendeurRole);
		roleRepository.save(livreurRole);
		
		//Initialisation des categories
		Categorie recent=new Categorie("Le plus récent","R");
		Categorie vehicules=new Categorie("Véhicules","V");
		Categorie immobilier=new Categorie("Immobilier","I");
		Categorie informatique_multimedia=new Categorie("Informatique et multimédia","IM");
		Categorie loisirs_divertissement=new Categorie("Loisirs et divertissement","LD");
		Categorie habillement_bienEtre=new Categorie("Habillement et bien être","H");
		categorieRepository.save(recent);
		categorieRepository.save(vehicules);
		categorieRepository.save(immobilier);
		categorieRepository.save(informatique_multimedia);
		categorieRepository.save(loisirs_divertissement);
		categorieRepository.save(habillement_bienEtre);
		
		//Insertion d'un admin dans BD
	}
	//Implementation de la création des utilisateurs//
	//selon leurs roles//
	
	
	@Override
	public User creerAdmin() {
		Admin admin = new Admin("admin","admin","ADMIN");
		User user=new User();
		user.setEmail(admin.getLogin_Username());
		user.setNom(admin.getLogin_Username());
		user.setPrenom(admin.getLogin_Username());
		Role userRole=roleRepository.findByRole(admin.getRole_Admin());
		Set<Role> userRoles=new HashSet<>();
		userRoles.add(userRole);
		user.setRoles(userRoles);
		user.setMdp(mdpEncoder.encode(admin.getLogin_MotDePasse()));
		userRepository.save(user);
		
		return user;
	}
	
	@Override
	public User creerUser(UserModel userModel) {

		User user=new User();
		user.setEmail(userModel.getEmail());
		user.setNom(userModel.getNom());
		user.setPrenom(userModel.getPrenom());
		if(roleRepository.existsByRole(userModel.getRole())) 
		{
					Role userRole=roleRepository.findByRole(userModel.getRole());
					Set<Role> userRoles=new HashSet<>();
					userRoles.add(userRole);
					user.setRoles(userRoles);
		}else {
			System.out.println("ROLE INVALID");
		}
		user.setMdp(mdpEncoder.encode(userModel.getMdp()));
		userRepository.save(user);
		
		return user;
	}
	

	
	//*************************************************//
	//Acheteur//
	@Override
	public Acheteur creerAcheteur(UserModel userModel) {
		Acheteur user=new Acheteur();
		user.setEmail(userModel.getEmail());
		user.setNom(userModel.getNom());
		user.setPrenom(userModel.getPrenom());
		user.setRole("ACHETEUR");
		user.setMdp(mdpEncoder.encode(userModel.getMdp()));
		user.setRegion(userModel.getRegion());
		user.setCodePostal(userModel.getCodePostal());
		user.setAdresse(userModel.getAdresse());
		user.setNumTel(userModel.getNumTel());
		achRepository.save(user);
		return user;
	}
	
	//*************************************************//
	//Vendeur//
	@Override
	public Vendeur creerVendeur(UserModel userModel) {
		Vendeur user=new Vendeur();
		user.setEmail(userModel.getEmail());
		user.setNom(userModel.getNom());
		user.setPrenom(userModel.getPrenom());
		user.setRole("VENDEUR");
		user.setMdp(mdpEncoder.encode(userModel.getMdp()));
		vendRepository.save(user);
		return user;
	}

	//*************************************************//
	//Livreur//
	@Override
	public Livreur creerLivreur(UserModel userModel) {
		Livreur user=new Livreur();
		user.setEmail(userModel.getEmail());
		user.setNom(userModel.getNom());
		user.setPrenom(userModel.getPrenom());
		user.setRole("LIVREUR");
		user.setRegion(userModel.getRegion());
		user.setMdp(mdpEncoder.encode(userModel.getMdp()));
		livRepository.save(user);
		return user;
	}

	//*************************************************//
	
	//Verification Token
	
	//user
	@Override
	public void saveVerificationToken(String token,User user) {

		VerificationToken verifToken=new VerificationToken(user,token);
		verifTokenRepository.save(verifToken);	
	}
	
	//Acheteur
	@Override
	public void saveVerificationTokenAch(String token,Acheteur user) {

		VerificationTokenAcheteur verifToken=new VerificationTokenAcheteur(user,token);
		verifTokenRepositoryAch.save(verifToken);	
	}
	
	//vendeur
	
	@Override
	public void saveVerificationTokenVend(String token,Vendeur user) {

		VerificationTokenVendeur verifToken=new VerificationTokenVendeur(user,token);
		verifTokenRepositoryVend.save(verifToken);	
	}
	
	//livreur
	
	@Override
	public void saveVerificationTokenLiv(String token,Livreur user) {

		VerificationTokenLivreur verifToken=new VerificationTokenLivreur(user,token);
		verifTokenRepositoryLiv.save(verifToken);	
	}
	
	//***************************//
	
	//user
	@Override
	public String validerVerificationToken(String token) {
		VerificationToken verifToken=verifTokenRepository.findByToken(token);
		if (verifToken==null) {
			return "invalid";
		}
		
		//vérifier l'expiration du temps
		User user=verifToken.getUser();
		Calendar cal=Calendar.getInstance();
		if ((verifToken.getExpirationTime().getTime() - cal.getTime().getTime())<=0) {
			verifTokenRepository.delete(verifToken);
			return "expiré";
		}
		
		user.setEnabled(true);
		userRepository.save(user);
		return "valid";
	}
	
	@Override
	public VerificationToken creerNewVerificationToken(String oldToken) {
		VerificationToken veriftoken=verifTokenRepository.findByToken(oldToken);
		veriftoken.setToken(UUID.randomUUID().toString());
		verifTokenRepository.save(veriftoken);
		return veriftoken;
	}
	
	//acheteur 
	@Override
	public String validerVerificationTokenAch(String token) {
		VerificationTokenAcheteur verifToken=verifTokenRepositoryAch.findByToken(token);
		if (verifToken==null) {
			return "invalid";
		}
		
		//vérifier l'expiration du temps
		Acheteur user=verifToken.getAch();
		Calendar cal=Calendar.getInstance();
		if ((verifToken.getExpirationTime().getTime() - cal.getTime().getTime())<=0) {
			verifTokenRepositoryAch.delete(verifToken);
			return "expiré";
		}
		User exist_user = userRepository.findByEmail(user.getEmail());
		exist_user.setEnabled(true);
		updateUser(exist_user);
		user.setEnabled(true);
		achRepository.save(user);
		return "valid";
	}
	
	@Override
	public VerificationTokenAcheteur creerNewVerificationTokenAch(String oldToken) {
		VerificationTokenAcheteur veriftoken=verifTokenRepositoryAch.findByToken(oldToken);
		veriftoken.setToken(UUID.randomUUID().toString());
		verifTokenRepositoryAch.save(veriftoken);
		return veriftoken;
	}
	
	//vendeur
	@Override
	public String validerVerificationTokenVend(String token) {
		VerificationTokenVendeur verifToken=verifTokenRepositoryVend.findByToken(token);
		if (verifToken==null) {
			return "invalid";
		}
		
		//vérifier l'expiration du temps
		Vendeur user=verifToken.getVend();
		Calendar cal=Calendar.getInstance();
		if ((verifToken.getExpirationTime().getTime() - cal.getTime().getTime())<=0) {
			verifTokenRepositoryVend.delete(verifToken);
			return "expiré";
		}
		User exist_user = userRepository.findByEmail(user.getEmail());
		exist_user.setEnabled(true);
		updateUser(exist_user);
		user.setEnabled(true);
		vendRepository.save(user);
		return "valid";
	}
	
	@Override
	public VerificationTokenVendeur creerNewVerificationTokenVend(String oldToken) {
		VerificationTokenVendeur veriftoken=verifTokenRepositoryVend.findByToken(oldToken);
		veriftoken.setToken(UUID.randomUUID().toString());
		verifTokenRepositoryVend.save(veriftoken);
		return veriftoken;
	}
	
	//livreur
	@Override
	public String validerVerificationTokenLiv(String token) {
		VerificationTokenLivreur verifToken=verifTokenRepositoryLiv.findByToken(token);
		if (verifToken==null) {
			return "invalid";
		}
		
		//vérifier l'expiration du temps
		Livreur user=verifToken.getLiv();
		Calendar cal=Calendar.getInstance();
		if ((verifToken.getExpirationTime().getTime() - cal.getTime().getTime())<=0) {
			verifTokenRepositoryLiv.delete(verifToken);
			return "expiré";
		}
		User exist_user = userRepository.findByEmail(user.getEmail());
		exist_user.setEnabled(true);
		updateUser(exist_user);
		user.setEnabled(true);
		livRepository.save(user);
		return "valid";
	}
	
	@Override
	public VerificationTokenLivreur creerNewVerificationTokenLiv(String oldToken) {
		VerificationTokenLivreur veriftoken=verifTokenRepositoryLiv.findByToken(oldToken);
		veriftoken.setToken(UUID.randomUUID().toString());
		verifTokenRepositoryLiv.save(veriftoken);
		return veriftoken;
	}
	
	
	

	//**********************************************************//
	
		
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	/*
	 * //password recovery
	@Override
	public void creerPasswordResetToken(User user, String token) {
		PasswordResetToken passwordResetToken=new PasswordResetToken(user,token);
		passwordResetTokenRepository.save(passwordResetToken);
	}

	@Override
	public String validerPasswordResetToken(String token) {
		
		PasswordResetToken passwordResetToken=passwordResetTokenRepository.findByToken(token);
		if (passwordResetToken==null) {
			return "invalid";
		}
		
		//vérifier l'expiration du temps
		User user=passwordResetToken.getUser();
		Calendar cal=Calendar.getInstance();
		if ((passwordResetToken.getExpirationTime().getTime() - cal.getTime().getTime())<=0) {
			passwordResetTokenRepository.delete(passwordResetToken);
			return "expiré";
		}
		return "valid";
	}

	@Override
	public Optional<User> getUserByPasswordResetToken(String token) {
		return Optional.ofNullable(passwordResetTokenRepository.findByToken(token).getUser());
	}

	@Override
	public void changeMdp(User user, String nouveauMdp) {
		user.setMdp(mdpEncoder.encode(nouveauMdp));
		userRepository.save(user);
	}

	@Override
	public boolean verifOldPassword(User user, String ancientMdp) {
		return mdpEncoder.matches(ancientMdp, user.getMdp());
	}

	@Override
	public User findByMdp(String password) {
		return userRepository.findByMdp(password);
	}
*/
	//des methodes implémentés pour plusieurs vérification.

	@Override
	public boolean existsByEmailAch(String email) {

		return achRepository.existsByEmail(email);
	}
	@Override
	public boolean existsByEmailVend(String email) {
		
		return vendRepository.existsByEmail(email);
	}
	@Override
	public boolean existsByEmailLiv(String email) {
		
		return livRepository.existsByEmail(email);
	}
	@Override
	public boolean existsByEmail(String email) {
		
		return userRepository.existsByEmail(email);
	}

	@Override
	public Optional<Vendeur> findVendeurById(Long id) {
		return vendRepository.findById(id);
	}


	@Override
	public Vendeur findVendeur(String email) {
		return vendRepository.findByEmail(email);
	}


	@Override
	public Livreur findLivreur(String email) {
		return livRepository.findByEmail(email);
	}

	@Override
	public Acheteur findAcheteur(String email) {
		return achRepository.findByEmail(email);
	}


	@Override
	public Acheteur updateAch(UserModel userModel) {
		Acheteur ach=achRepository.findByEmail(userModel.getEmail());
		ach.setEmail(userModel.getEmail());
		ach.setNom(userModel.getNom());
		ach.setPrenom(userModel.getPrenom());
		ach.setAdresse(userModel.getAdresse());
		ach.setCodePostal(userModel.getCodePostal());
		ach.setRegion(userModel.getRegion());
		ach.setNumTel(userModel.getNumTel());
		achRepository.save(ach);
		return ach;
		
	}


	@Override
	public Livreur updateLiv(UserModel userModel) {
		Livreur liv=livRepository.findByEmail(userModel.getEmail());
		liv.setEmail(userModel.getEmail());
		liv.setNom(userModel.getNom());
		liv.setPrenom(userModel.getPrenom());
		liv.setRegion(userModel.getRegion());
		livRepository.save(liv);
		return liv;
	}


	@Override
	public Vendeur updateVend(UserModel userModel) {
		Vendeur vend=vendRepository.findByEmail(userModel.getEmail());
		vend.setEmail(userModel.getEmail());
		vend.setNom(userModel.getNom());
		vend.setPrenom(userModel.getPrenom());
		vendRepository.save(vend);
		return vend;
	}


	@Override
	public User updateUserInfo(UserModel userModel) {
		User user=userRepository.findByEmail(userModel.getEmail());
		user.setEmail(userModel.getEmail());
		user.setNom(userModel.getNom());
		user.setPrenom(userModel.getPrenom());
		userRepository.save(user);
		return user;
		
	}
	
	@Override
	public void updateUser(User user) {
		userRepository.save(user);	
	}


	@Override
	public List<User> ListAllUsers() {
		return userRepository.findAll();
	}
	@Override
	public List<Acheteur> ListAllach() {
		return achRepository.findAll();
	}
	@Override
	public List<Vendeur> ListAllvend() {
		return vendRepository.findAll();
	}
	@Override
	public List<Livreur> ListAllliv() {
		return livRepository.findAll();
	}
	
	




	

	
	
	

}
