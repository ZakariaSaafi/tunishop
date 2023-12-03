import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CommandeService {
  requestOptions: Object = {
    /* other options here */
    responseType: 'text'
  }
  constructor(private http:HttpClient) { }


  //creer Commande
  public creerCommande(id_acheteur:bigint) {
    return this.http.post("http://localhost:80/Commande/creerCommande/"+id_acheteur,this.requestOptions);
}

//Get Commande by id acheteur
public getCommandeByAcheteur(id_acheteur:bigint) {
  return this.http.get("http://localhost:80/Commande/getCommande/"+id_acheteur);
}
//Get Commande by id 
public getCommandeById(id_commande:bigint) {
  return this.http.get("http://localhost:80/Commande/getCommandeById/"+id_commande);
}


//changer statut
public changeStatut(id_commande:bigint){
  return this.http.put("http://localhost:80/Commande/changerStatut/"+id_commande,this.requestOptions);
}

//list commandeByLivreur
public getCommandesByLivreur(id_livreur:bigint){
  return this.http.get("http://localhost:80/Commande/listCommandeLivreur/"+id_livreur);
}

//list commande non livré (pour l'espace livreur)
public getCommandeNotDelivered(){
  return this.http.get("http://localhost:80/Commande/listCommandeNotDelivered");
}
}
