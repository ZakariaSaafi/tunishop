import { HttpClient, HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JsonpClientBackend } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Commande } from 'src/app/model/Commande';
import { CommandeService } from '../commande/commande.service';
import { PanierService } from '../panier/panier.service';
import { AuthenticationService } from '../authentication/authentication.service';
import { Panier } from 'src/app/model/Panier';

@Injectable({
  providedIn: 'root'
})
export class PaymeeService implements HttpInterceptor {



  public data ={
  "vendor": 2278,
  "amount": 55.2,
  "note" : "Commande #1234"
};

  public response:any;

  constructor(public http:HttpClient,private commandeService:CommandeService,private panierService:PanierService,private service:AuthenticationService) { }
  

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let token=req.clone({
      setHeaders:{
        Authorization: 'Token 6ef82e1ec99a9b179a1af3cd6137f06b6a08e3e1'
      }
    })
    return next.handle(token);
  }

/*   createPayment(){
    this.paymentProcess().subscribe((r)=>{
      this.response=r;
      console.log(this.response);
    })
  } */
/* 
  checkpayment(){
    this.checkPaymentProcess().subscribe((r)=>{
      this.response=r;
      if (this.response['data']['payment_status']){
        alert('success');
      }else{alert('failed');}
    })
  } */



  paymentProcess(devis:number,matricule:string){
    this.data.amount=devis;
    this.data.note="Commande #"+matricule;
    return this.http.post("https://sandbox.paymee.tn/api/v1/payments/create", this.data);
  }
/* 
  checkPaymentProcess(){
    return this.http.post("https://sandbox.paymee.tn/api/v1/payments/9D320F732CE972A47E8C73E0A4D23BB0947/check",{header:this.header});
  } */
 
  
  
}
