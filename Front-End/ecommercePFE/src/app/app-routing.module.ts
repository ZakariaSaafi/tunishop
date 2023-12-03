import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { AdresseComponent } from './adresse/adresse.component';
import { AjouterProduitComponent } from './ajouter-produit/ajouter-produit.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { DashboardLivreurComponent } from './dashboard-livreur/dashboard-livreur.component';
import { DashboardVendeurComponent } from './dashboard-vendeur/dashboard-vendeur.component';
import { HomeLivreurComponent } from './home-livreur/home-livreur.component';
import { HomeVendeurComponent } from './home-vendeur/home-vendeur.component';
import { HomeComponent } from './home/home.component';
import { ImageProduitComponent } from './image-produit/image-produit.component';
import { InscriptionComponent } from './inscription/inscription.component';
import { ListHabillementComponent } from './list-habillement/list-habillement.component';
import { ListImmobilierComponent } from './list-immobilier/list-immobilier.component';
import { ListInfoMultiComponent } from './list-info-multi/list-info-multi.component';
import { ListLoisirDiverComponent } from './list-loisir-diver/list-loisir-diver.component';
import { ListProduitVendeurComponent } from './list-produit-vendeur/list-produit-vendeur.component';
import { ListProduitComponent } from './list-produit/list-produit.component';
import { ListVehiculesComponent } from './list-vehicules/list-vehicules.component';
import { ListViewHabillementComponent } from './list-view-habillement/list-view-habillement.component';
import { ListViewImmobilierComponent } from './list-view-immobilier/list-view-immobilier.component';
import { ListViewInfoMultiComponent } from './list-view-info-multi/list-view-info-multi.component';
import { ListViewLoisirDiverComponent } from './list-view-loisir-diver/list-view-loisir-diver.component';
import { ListViewProduitComponent } from './list-view-produit/list-view-produit.component';
import { ListViewVehiculesComponent } from './list-view-vehicules/list-view-vehicules.component';
import { LoginAcheteurComponent } from './login-acheteur/login-acheteur.component';
import { LoginAdminComponent } from './login-admin/login-admin.component';
import { LoginLivreurComponent } from './login-livreur/login-livreur.component';
import { LoginVendeurComponent } from './login-vendeur/login-vendeur.component';
import { LoginComponent } from './login/login.component';
import { MailSentComponent } from './mail-sent/mail-sent.component';
import { MdpRecoveryComponent } from './mdp-recovery/mdp-recovery.component';
import { OrdersComponent } from './orders/orders.component';
import { PanierComponent } from './panier/panier.component';
import { ParamsLivreurComponent } from './params-livreur/params-livreur.component';
import { ParamsVendeurComponent } from './params-vendeur/params-vendeur.component';
import { ProductsingleComponent } from './productsingle/productsingle.component';
import { ProfilInfoComponent } from './profil-info/profil-info.component';
import { RolePageBeforeLoginComponent } from './role-page-before-login/role-page-before-login.component';
import { SuccessPaymentComponent } from './success-payment/success-payment.component';
import { TachesLivreurComponent } from './taches-livreur/taches-livreur.component';

const routes: Routes = [
  
  
  //page home
  {path : '', component:HomeComponent},
  //login 
  {path : 'login', component:LoginComponent},
  {path : 'loginVendeur', component:LoginVendeurComponent},
  {path : 'loginAcheteur', component:LoginAcheteurComponent},
  {path : 'loginLivreur', component:LoginLivreurComponent},
  //Inscription
  {path : 'inscription', component:InscriptionComponent},
  //Mdp recovery
  {path : 'mdp-recovery', component:MdpRecoveryComponent},
  //parametre && info
  {path : 'profil-info', component:ProfilInfoComponent},
  //panier acheteur
  {path : 'panier', component:PanierComponent},
  {path:'address', component:AdresseComponent},
  {path:'order', component:OrdersComponent},
  {path:'mailSent', component:MailSentComponent},
  {path:'role', component:RolePageBeforeLoginComponent},
  {path:'success', component:SuccessPaymentComponent},
  
  //Poduits
  //les plus récents
  {path:'list-produit/:id/R', component:ListProduitComponent},
  {path:'list-view-produit/:id/R', component:ListViewProduitComponent},
  //vehicules
  {path:'list-produit/:id/V', component:ListVehiculesComponent},
  {path:'list-view-produit/:id/V', component:ListViewVehiculesComponent},
  //immobilier
  {path:'list-produit/:id/I', component:ListImmobilierComponent},
  {path:'list-view-produit/:id/I', component:ListViewImmobilierComponent},
  //informatique et multimedia
  {path:'list-produit/:id/IM', component:ListInfoMultiComponent},
  {path:'list-view-produit/:id/IM', component:ListViewInfoMultiComponent},
  //loisirs et divertissement
  {path:'list-produit/:id/LD', component:ListLoisirDiverComponent},
  {path:'list-view-produit/:id/LD', component:ListViewLoisirDiverComponent},
  //habillement et bien etre
  {path:'list-produit/:id/H', component:ListHabillementComponent},
  {path:'list-view-produit/:id/H', component:ListViewHabillementComponent},
 
  //page produit 
  {path:'produit/:id', component:ProductsingleComponent},
  
  //Espace Vendeur
  {path:'homeVendeur/:id', component:HomeVendeurComponent,children:
    [
     {path:'dashboardVendeur/:code',component:DashboardVendeurComponent},
     {path:'ajouterProduit',component:AjouterProduitComponent},
     {path:'imageProduit/:id',component:ImageProduitComponent},
     {path:'listProduit/:code',component:ListProduitVendeurComponent},
     {path:'parametreVendeur/:id',component:ParamsVendeurComponent}
    ]
  },

  //paymee
  {path:'checkout/:id', component:CheckoutComponent},
  //admin
  {path:'admin', component:AdminComponent},
  {path:'loginAdmin', component:LoginAdminComponent},
  //Espace livreur
  {path:'homeLivreur/:id', component:HomeLivreurComponent,children:
    [
    {path:'dashboardLivreur/:code',component:DashboardLivreurComponent},
    {path:'tachesLivreur',component:TachesLivreurComponent},
    {path:'parametreLivreur/:id',component:ParamsLivreurComponent}
    ]
  }

 


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [LoginComponent,InscriptionComponent,MdpRecoveryComponent,ProfilInfoComponent,PanierComponent]
