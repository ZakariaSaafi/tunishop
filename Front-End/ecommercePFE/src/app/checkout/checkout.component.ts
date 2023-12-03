import { DOCUMENT } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Acheteur } from '../model/Acheteur';
import { Commande } from '../model/Commande';
import { Produit } from '../model/Produit';
import { AuthenticationService } from '../service/authentication/authentication.service';
import { CommandeService } from '../service/commande/commande.service';
import { PaymeeService } from '../service/paymeeIntegration/paymee.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
response:any;
token:any;
id:any;
commande:Commande;
payment_id:any;
produits:Produit[]=[];
region:any=[
  {nom: "Tunis"},
  {nom: "Ariana"},
  {nom: "Ben arous"},
  {nom: "Manouba"},
  {nom: "Bizerte"},
  {nom: "Zaghouan"},
  {nom: "Nabeul"},
  {nom: "Beja"},
  {nom: "Jendouba"},
  {nom: "Le Kef"},
  {nom: "Siliana"},
  {nom: "Monastir"},
  {nom: "Mahdia"},
  {nom: "Sousse"},
  {nom: "Sfax"},
  {nom: "Kairouan"},
  {nom: "Kasserine"},
  {nom: "Medenine"},
  {nom: "Gabes"},
  {nom: "Tataouine"},
  {nom: "Tozeur"},
  {nom: "Gafsa"},
  {nom: "Kebili"}
]

options = {
  "key": "rzp_test_DkDctJpyMRt3Gx", 
  "amount": "50000", 
  "currency": "USD",
  "name": "Acme Corp",
  "description": "Le devis sera converti en $US",
  "image": "https://m.media-amazon.com/images/I/31s65UrXsZL._AC_.jpg",
  "order_id": "", 
  "handler": (res:any)=>{
    //alert("Paiement avec succès");
    this.payment_id=res.razorpay_payment_id;
    console.log(this.payment_id);
    this.redirectSuccess();
  },
  "prefill": {
      "name": "Gaurav Kumar",
      "email": "gaurav.kumar@example.com",
      "contact": "9999999999"
      
  },
  "notes": {
      "address": "Razorpay Corporate Office"
  },
  "theme": {
      "color": "#3399cc"
  }
};

  constructor(private paymeeService:PaymeeService,private route:Router,@Inject(DOCUMENT) private document:Document,public service:AuthenticationService,
  private router:ActivatedRoute,private commandeService:CommandeService ) { 
    if (this.service.isLogged=== false)
    this.route.navigate(['/loginAcheteur']);
  }

  
  ngOnInit(): void {
    this.id = this.router.snapshot.paramMap.get('id'); //pour recuperer l'id 
    console.log('this.id',this.id);
    if (this.id){
      this.commandeService.getCommandeById(this.id).subscribe((data:any)=>{
        this.commande=data;
        console.log(this.commande);
        this.produits=this.commande.produits;
        console.log(this.produits);
        this.createPayment(this.commande.devis,this.commande.matricule);
      });
      
    }
    
  }

  createPayment(devis:number,matricule:string){
  
    this.paymeeService.paymentProcess(devis,matricule).subscribe((r)=>{
      this.response=r;
      this.token=this.response.data.token;
      console.log(this.response);
    })
  }
  onSubmit(){
    window.open('https://sandbox.paymee.tn/gateway/'+this.token,"_blank");

  }

  pay(devis:any,nom:any,prenom:any,email:any,numTel:any){
    let ammount:string=(Math.round((devis*100)/3.2)).toString();
    this.options.amount=ammount; //set devis de la commande
    this.options.name=nom+" "+prenom; //set nom et prénom de l'acheteur
    this.options.prefill.email=email; //set email de l'acheteur
    this.options.prefill.name=nom; //set nom de l'acheteur pour détail de la carte
    this.options.prefill.contact="216"+numTel; //set num tel
    let rzp1 = new this.service.nativeWindow.Razorpay(this.options);
    rzp1.open();
  }

  redirectSuccess(){
    if(this.payment_id){
      this.commandeService.changeStatut(this.commande.code_commande).subscribe((data:any)=>{
        console.log(data);
      });
      this.service.loggedAcheteur=true;
      this.route.navigate(['/success']);
    }
  }


}
