package com.example.Tunishop.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Tunishop.Entity.Acheteur;
import com.example.Tunishop.Entity.Livreur;
import com.example.Tunishop.Entity.User;
import com.example.Tunishop.Entity.Vendeur;
import com.example.Tunishop.Model.UserModel;
import com.example.Tunishop.Service.UserService;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping(value="/User")
public class UserController {

	@Autowired
	private UserService userServ;
	
	@PutMapping("/updateUserInfo")
	public User updateUserInfo(@RequestBody UserModel userModel) {
		return userServ.updateUserInfo(userModel);
	}
	@PutMapping("/updateAch")
	public Acheteur updateAchInfo(@RequestBody UserModel userModel) {
		return userServ.updateAch(userModel);
	}
	@PutMapping("/updateLiv")
	public Livreur updateLivInfo(@RequestBody UserModel userModel) {
		return userServ.updateLiv(userModel);
	}
	@PutMapping("/updateVend")
	public Vendeur updateVendInfo(@RequestBody UserModel userModel) {
		return userServ.updateVend(userModel);
	}
	
	@GetMapping("/listAllUsers")
	public List<User> listAll(){
		return userServ.ListAllUsers();
	}
	@GetMapping("/listAllAch")
	public List<Acheteur> listAllach(){
		return userServ.ListAllach();
	}
	@GetMapping("/listAllVend")
	public List<Vendeur> listAllVend(){
		return userServ.ListAllvend();
	}
	@GetMapping("/listAllLiV")
	public List<Livreur> listAllLiv(){
		return userServ.ListAllliv();
	}
	
	
	
}
