import { Commande } from "./Commande";
import { Produit } from "./Produit";

export class Acheteur{
      code_ach:bigint;
      nom: string;
      prenom: string;
      mdp: string;
      role: string;
      email: string;
      enabled: boolean;
      logged: boolean;
      isBanned: boolean;
      produits:Array<Produit>;
      region:string;
      codePostal:string;
      adresse:string;
      commande:Array<Commande>;
      numTel:string;
}