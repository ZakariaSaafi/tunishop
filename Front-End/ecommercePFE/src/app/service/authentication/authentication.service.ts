import { HttpClient, HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router, UrlSegment } from '@angular/router';
import { BehaviorSubject, Observable, catchError,throwError } from 'rxjs';
import { Acheteur } from 'src/app/model/Acheteur';
import { Role } from 'src/app/model/Role';
import { SignInData } from 'src/app/model/signInData';
import { User } from 'src/app/model/User';
import { UserModel } from 'src/app/model/UserModel';
import { Vendeur } from 'src/app/model/Vendeur';
import { Livreur } from 'src/app/model/Livreur';


function _window() : any {
  return window;
}

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService implements HttpInterceptor {

  //for razorpay method
  get nativeWindow() : any {
    return _window();
 }


  //Login part
  public user:UserModel;
  public isLogged=false;
  public loggedVendeur=false;
  public loggedAcheteur=false;
  public loggedLivreur=false;
  public loggedAdmin=false;

  public currentAcheteur:Acheteur;
  public currentVendeur:Vendeur;
  public currentLivreur:Livreur;

  //inscri part
  public responseUser:any;
  private inscriUrl= 'http://localhost:80/inscri';
  private existUrl='http://localhost:80/existByEmail';
   requestOptions: Object = {
    /* other options here */
    responseType: 'text'
  }



  constructor(private route: Router,private http:HttpClient) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let token=req.clone({
      setHeaders:{
        Authorization: 'Basic emFrYXJpYXNhYWZpQHlhaG9vLmZyOjU0NQ=='
      }
    })
    return next.handle(token);
  }

//méthode pour l'authentification
/*   authenticate (user:UserModel) : boolean{
    localStorage.clear();
    this.login(user).subscribe((data)=>{
      this.responseUser=data;
      console.log(this.responseUser.message);
       if (this.responseUser.message==="Veuillez activez votre compte pour se connecter!")
      {
        alert(this.responseUser.message)
        this.isLogged=false;
        return false;
      }else if (this.responseUser.message==="Il n'existe pas un compte avec ce mail !")
      {
        alert(this.responseUser.message)
        this.isLogged=false;
        return false;
      }else 
      {
        alert("Connexion avec succés");
        
        this.responseUser=data;
        console.log(this.responseUser.role);
        localStorage.setItem('this user is', this.responseUser.email);
        this.isLogged=true;
        
        // les directions de chaque utilisateur aprés l'authentification
        
           if (this.responseUser.role==="VENDEUR"){
            this.loggedVendeur=true;
            this.route.navigate(['homeVendeur',this.responseUser.code,'dashboardVendeur',this.responseUser.code]);
          } 
        
        if (this.responseUser.role==="ACHETEUR"){
          this.loggedAcheteur=true;
          this.route.navigate(['']);
        }
        if (this.responseUser.role==="LIVREUR"){
          this.loggedLivreur=true;
          this.route.navigate(['/homeLivreur']);
        }
        return true;
      }
    })
    return this.isLogged;
  } */

//acheteur
/**************************/
//login
authenticateAch (user:UserModel) : boolean{
  localStorage.clear();
  this.login(user).subscribe((data)=>{
    this.responseUser=data;
    console.log(this.responseUser.message);
     if (this.responseUser.message==="Veuillez activez votre compte pour se connecter!")
    {
      alert(this.responseUser.message)
      this.isLogged=false;
      return false;
    }else if (this.responseUser.message==="Il n'existe pas un compte avec ce mail !")
    {
      alert(this.responseUser.message)
      this.isLogged=false;
      return false;
    }else if (this.responseUser.role==="ACHETEUR"){
      this.currentAcheteur=this.responseUser;
      alert("Connexion avec succés");
      console.log(this.responseUser.role);
      this.isLogged=true;
      this.loggedAcheteur=true;
      this.saveAchToLocalStorage(this.loggedAcheteur);
      localStorage.setItem('Connected user is', this.currentAcheteur.email);
      this.route.navigate(['']);
      
    }else{
          this.isLogged=false;
          alert("Vous êtes dans la page de Login Acheteur !! ");
        } 
    return true;
  });
  return this.isLogged;
}
/**************************/
//logout
logoutAcheteur(){
  this.isLogged=false;
  this.loggedAcheteur=false;
  alert("Voulez-vous vraiment se déconnecter ?");
  this.resetStorageAch();
  localStorage.clear();
  localStorage.setItem('User is disconnected','');
  this.route.navigate(['']);
}
/**************************/
getAchNom(){
  return this.responseUser.nom;
}
/**************************/
getAchPrenom(){
  return this.responseUser.prenom;
}
/**************************/


/**************************/
/**************************/
//vendeur
/**************************/
//login
/**************************/
authenticateVend (user:UserModel) : boolean{
  this.login(user).subscribe((data)=>{
    this.responseUser=data;
    console.log(this.responseUser.message);
     if (this.responseUser.message==="Veuillez activez votre compte pour se connecter!")
    {
      alert(this.responseUser.message)
      this.isLogged=false;
      return false;
    }else if (this.responseUser.message==="Il n'existe pas un compte avec ce mail !")
    {
      alert(this.responseUser.message)
      this.isLogged=false;
      return false;
    }else if (this.responseUser.role==="VENDEUR"){
      this.currentVendeur=this.responseUser;
      alert("Connexion avec succés");
      console.log(this.responseUser.role);
      this.isLogged=true;
      this.loggedVendeur=true;
      this.route.navigate(['homeVendeur',this.currentVendeur.code_vendeur,'dashboardVendeur',this.currentVendeur.code_vendeur]);
      localStorage.setItem('Connected user is', this.currentVendeur.email);
    }else{
          this.isLogged=false;
          alert("Vous êtes dans la page de Login Vendeur !! ");
        } 
    return true;
  });
  return this.isLogged;
}
/**************************/
//logout
/**************************/
logoutVendeur(){
  this.isLogged=false;
  this.loggedVendeur=false;
  //il faut changer logged in BD to false
  alert("Voulez-vous vraiment se déconnecter ?");
  localStorage.clear();
  localStorage.setItem('User is disconnected','');
  this.route.navigate(['']);
}

/**************************/
/**************************/
//livreur
/**************************/
//login
/**************************/
authenticateLiv (user:UserModel) : boolean{
  this.login(user).subscribe((data)=>{
    this.responseUser=data;
    console.log(this.responseUser.message);
     if (this.responseUser.message==="Veuillez activez votre compte pour se connecter!")
    {
      alert(this.responseUser.message)
      this.isLogged=false;
      return false;
    }else if (this.responseUser.message==="Il n'existe pas un compte avec ce mail !")
    {
      alert(this.responseUser.message)
      this.isLogged=false;
      return false;
    }else if (this.responseUser.role==="LIVREUR"){
      this.currentLivreur=this.responseUser;
      alert("Connexion avec succés");
      console.log(this.responseUser.role);
      this.isLogged=true;
      this.loggedLivreur=true;
      this.route.navigate(['homeLivreur',this.currentLivreur.code_livreur,'dashboardLivreur',this.currentLivreur.code_livreur]);
      localStorage.setItem('Connected user is', this.currentLivreur.email);
    }else{
          this.isLogged=false;
          alert("Vous êtes dans la page de Login Livreur !! ");
        } 
    return true;
  });
  return this.isLogged;
}
/**************************/
//logout
/**************************/
logoutLivreur(){
  this.isLogged=false;
  this.loggedLivreur=false;
  //il faut changer logged in BD to false
  alert("Voulez-vous vraiment se déconnecter ?");
  localStorage.clear();
  localStorage.setItem('User is disconnected','');
  this.route.navigate(['']);
}

 
authenticateAdmin(c:any){
  if(c['email']==="admin" && c['mdp']==="admin"){
    this.isLogged=true;
    this.loggedAdmin=true;
    this.route.navigate(['admin']);
  }
}
 


  signUp(userModel:UserModel){
    return this.http.post(this.inscriUrl,userModel,this.requestOptions);
  }

  existByEmail(email:any){
    return this.http.get<boolean>(this.existUrl,email);
  }



  login(userModel:UserModel):Observable<any>{
    return this.http.post<any>("http://localhost:80/login",userModel); 
    }
  
  

  getAchFromLocalStorage():boolean{
    let trv=false;
    if (localStorage.getItem('app.ach')){
      trv=true;
    }
    return trv;
  }
  
  saveAchToLocalStorage(ach:any){
    localStorage.setItem('app.ach',ach);
  }

  resetStorageAch(){
    localStorage.removeItem('app.ach');
  }

  updateUser(userModel:UserModel){
    return this.http.put("http://localhost:80/User/updateUserInfo",userModel,this.requestOptions);
  }
  updateAch(userModel:UserModel){
    return this.http.put("http://localhost:80/User/updateAch",userModel,this.requestOptions);
  }
  updateLiv(userModel:UserModel){
    return this.http.put("http://localhost:80/User/updateLiv",userModel,this.requestOptions);
  }
  updateVend(userModel:UserModel){
    return this.http.put("http://localhost:80/User/updateVend",userModel,this.requestOptions);
  }

  listAllUsers(){
    return this.http.get("http://localhost:80/User/listAllUsers");
  }
  listAllach(){
    return this.http.get("http://localhost:80/User/listAllAch");
  }
  listAllliv(){
    return this.http.get("http://localhost:80/User/listAllLiv");
  }
  listAllvend(){
    return this.http.get("http://localhost:80/User/listAllVend");
  }
}
