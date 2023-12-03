import { Acheteur } from "./Acheteur";
import { Livreur } from "./Livreur";
import { Panier } from "./Panier";
import { Produit } from "./Produit";

export interface Commande{
     code_commande:bigint;
	 matricule:string;
	 region:string;
	 statut:string;
	 devis:number;
	 date:Date;
     livreurs:Array<Livreur>;
     paniers:Panier;
	 acheteur:Acheteur;
	 produits:Array<Produit>;
	 isDelivered:boolean;
}