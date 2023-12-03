package com.example.Tunishop.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Tunishop.Entity.Produit;
import com.example.Tunishop.Service.ProduitService;


@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/File")
public class FileController {

	@Autowired
	private ProduitService produitServ;
	
	//Upload Produit Image
	@PostMapping("/uploadProduitImage/{id_produit}")
	public ResponseEntity<List<String>> uploadImages
	(@RequestParam("files")List<MultipartFile>multipartFiles,@PathVariable(value="id_produit")String id_produit){
		Produit p=produitServ.findProduit(Long.parseLong(id_produit)).get();
		List<String> filesNames=new ArrayList<>();
		for(MultipartFile file:multipartFiles) {
			String filename=StringUtils.cleanPath(file.getOriginalFilename());
			try {
				p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			produitServ.updateProduit(p);
			filesNames.add(filename);
		}
		return ResponseEntity.ok().body(filesNames);
	}
}
