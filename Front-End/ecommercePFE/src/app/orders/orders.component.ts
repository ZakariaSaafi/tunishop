import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Commande } from '../model/Commande';
import { Panier } from '../model/Panier';
import { Produit } from '../model/Produit';
import { AuthenticationService } from '../service/authentication/authentication.service';
import { CommandeService } from '../service/commande/commande.service';
@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
public commande:Commande[]=[];
public id:any;
public panier:Panier;
public loadingData:boolean=false;
public errorMessage:string='';
public selectedCommande:Commande;
public produitsCommande:Produit[]=[];
//pour generateur de pdf
props = {
  outputType: this.service.nativeWindow.jsPDFInvoiceTemplate.OutputType.Save,
  returnJsPDFDocObject: true,
  fileName: "Facture de "+this.service.currentAcheteur.nom+" "+this.service.currentAcheteur.prenom,
  orientationLandscape: false,
  compress: true,
  logo: {
      src: "assets/images/logoTuniShop.png",
      type: 'PNG', //optional, when src= data:uri (nodejs case)
      width: 53.33, //aspect ratio = width/height
      height: 20.66,
      margin: {
          top: 0, //negative or positive num, from the current position
          left: 0 //negative or positive num, from the current position
      }
  },
  business: {
      name: "TuniShop",
      address: "Ben Arous, Nouvelle Medina, Tunisie",
      phone: "(+216) 53 686 759",
      email: "zak.saafi@gmail.com",
      website: "Tunishop.tn",
  },
  contact: {
      label: "Bon de commande pour :",
      name: this.service.currentAcheteur.nom+" "+this.service.currentAcheteur.prenom,
      address: this.service.currentAcheteur.adresse+", "+this.service.currentAcheteur.region,
      phone: "(+216) 069 22 22 222",
      email: this.service.currentAcheteur.email,
  },
  invoice: {
      label: "Commande #: ",
      num: 19,
      invGenDate: "Date de création de commande: ",
      headerBorder: false,
      tableBodyBorder: false,
      header: [
        {
          title: "ID", 
          style: { 
            width: 10 
          } 
        }, 
        { 
          title: "Nom",
          style: {
            width: 30
          } 
        }, 
        { 
          title: "Description",
          style: {
            width: 80
          } 
        }, 
        { title: "Prix"},
        { title: "Quantité"}
      ],
      table: Array.from(Array(10), (item, index)=>([
          index + 1,
          "There are many variations ",
          "Lorem Ipsum is simply dummy text dummy text ",
          200.5,
          4.5,
          "m2",
          400.5
      ])),
      invTotalLabel: "Total:",
      invTotal: "145,250.50",
      invCurrency: "ALL",
      row1: {
          col1: 'VAT:',
          col2: '20',
          col3: '%',
          style: {
              fontSize: 10 //optional, default 12
          }
      },
      row2: {
          col1: 'SubTotal:',
          col2: '116,199.90',
          col3: 'ALL',
          style: {
              fontSize: 10 //optional, default 12
          }
      },
      
  },
  footer: {
      text: "The invoice is created on a computer and is valid without the signature and stamp.",
  },
  pageEnable: true,
  pageLabel: "Page ",
};

  constructor(public service:AuthenticationService,private route:Router,private commandeService:CommandeService) {
    if (this.service.isLogged=== false){
      this.route.navigate(['/loginAcheteur']);} 
   }

  ngOnInit(): void {
    this.commandeService.getCommandeByAcheteur(this.service.currentAcheteur.code_ach).subscribe((data:any)=>{
      this.commande=data;
      this.loadingData=true;
      console.log(this.commande);
    });
    
  }

  facture(c:Commande){
    this.produitsCommande=c.produits;
    this.props.invoice.num=Number(c.matricule);
    this.props.invoice.invGenDate="Date de création de commande: "+c.date;
    this.props.invoice.table=Array.from(Array(this.produitsCommande.length), (item, index)=>([
      index + 1,
      this.produitsCommande[index].nom,
      this.produitsCommande[index].description,
      this.produitsCommande[index].prix,
      1,
      ]))
    this.props.invoice.invTotal=(c.devis).toString();  
    this.props.invoice.invCurrency="TND";  
    this.props.invoice.row1.col2="0";  
    this.props.invoice.row2.col2=(c.devis).toString();  
    this.props.invoice.row2.col3="TND";  
    let pdfObject = new this.service.nativeWindow.jsPDFInvoiceTemplate.default(this.props);
  }

  

  logout(){
    this.service.logoutAcheteur();
  }
}
