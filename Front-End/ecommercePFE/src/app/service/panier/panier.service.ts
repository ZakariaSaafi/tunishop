import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Panier } from 'src/app/model/Panier';

@Injectable({
  providedIn: 'root'
})
export class PanierService {
  requestOptions: Object = {
    /* other options here */
    responseType: 'text'
  }
  constructor(private http:HttpClient) { }


  //ajouter au panier
  public ajouterAuPanier(id_acheteur:bigint,id_produit:bigint) {
    return this.http.post("http://localhost:80/Panier/AddToPanier/"+id_acheteur+"/"+id_produit,this.requestOptions);
}
 //mise a jour panier
 public updatePanier(id_acheteur:bigint,id_produit:bigint) {
  return this.http.put("http://localhost:80/Panier/UpdatePanier/"+id_acheteur+"/"+id_produit,this.requestOptions);
}
//Get panier by id acheteur
public getPanier(id_acheteur:bigint) {
  return this.http.get("http://localhost:80/Panier/getPanier/"+id_acheteur);
}

//get produits from panier
public getProduitPanier(id_panier:bigint){
  return this.http.get("http://localhost:80/Panier/getProduitPanier/"+id_panier,this.requestOptions);
}
}
