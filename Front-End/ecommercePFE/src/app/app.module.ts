import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { InscriptionComponent } from './inscription/inscription.component';
import { MdpRecoveryComponent } from './mdp-recovery/mdp-recovery.component';
import { MatCarouselModule } from '@ngmodule/material-carousel';
import { ProfilInfoComponent } from './profil-info/profil-info.component';
import { PanierComponent } from './panier/panier.component';
import { HttpClient, HttpClientModule,HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HeaderUserComponent } from './header-user/header-user.component';
import { AdresseComponent } from './adresse/adresse.component';
import { OrdersComponent } from './orders/orders.component';
import { MailSentComponent } from './mail-sent/mail-sent.component';
import { RolePageBeforeLoginComponent } from './role-page-before-login/role-page-before-login.component';
import { ListProduitComponent } from './list-produit/list-produit.component';
import { ProductsingleComponent } from './productsingle/productsingle.component';
import { ListViewProduitComponent } from './list-view-produit/list-view-produit.component';
import { ListVehiculesComponent } from './list-vehicules/list-vehicules.component';
import { ListViewVehiculesComponent } from './list-view-vehicules/list-view-vehicules.component';
import { ListImmobilierComponent } from './list-immobilier/list-immobilier.component';
import { ListViewImmobilierComponent } from './list-view-immobilier/list-view-immobilier.component';
import { ListInfoMultiComponent } from './list-info-multi/list-info-multi.component';
import { ListViewInfoMultiComponent } from './list-view-info-multi/list-view-info-multi.component';
import { ListLoisirDiverComponent } from './list-loisir-diver/list-loisir-diver.component';
import { ListViewLoisirDiverComponent } from './list-view-loisir-diver/list-view-loisir-diver.component';
import { ListHabillementComponent } from './list-habillement/list-habillement.component';
import { ListViewHabillementComponent } from './list-view-habillement/list-view-habillement.component';
import { HomeVendeurComponent } from './home-vendeur/home-vendeur.component';
import { DashboardVendeurComponent } from './dashboard-vendeur/dashboard-vendeur.component';
import { AjouterProduitComponent } from './ajouter-produit/ajouter-produit.component';
import { ListProduitVendeurComponent } from './list-produit-vendeur/list-produit-vendeur.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { NotfoundComponent } from './notfound/notfound.component';
import { ParamsVendeurComponent } from './params-vendeur/params-vendeur.component';
import { LoginVendeurComponent } from './login-vendeur/login-vendeur.component';
import { LoginAcheteurComponent } from './login-acheteur/login-acheteur.component';
import { LoginLivreurComponent } from './login-livreur/login-livreur.component';
import { HomeLivreurComponent } from './home-livreur/home-livreur.component';
import { DashboardLivreurComponent } from './dashboard-livreur/dashboard-livreur.component';
import { TachesLivreurComponent } from './taches-livreur/taches-livreur.component';
import { ParamsLivreurComponent } from './params-livreur/params-livreur.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { PaymeeService } from './service/paymeeIntegration/paymee.service';
import { ImageProduitComponent } from './image-produit/image-produit.component';
import { SuccessPaymentComponent } from './success-payment/success-payment.component';
import { GooglePlaceModule } from 'ngx-google-places-autocomplete';
import { AdminComponent } from './admin/admin.component';
import { LoginAdminComponent } from './login-admin/login-admin.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    routingComponents,
    InscriptionComponent,
    MdpRecoveryComponent,
    ProfilInfoComponent,
    PanierComponent,
    HeaderUserComponent,
    AdresseComponent,
    OrdersComponent,
    MailSentComponent,
    RolePageBeforeLoginComponent,
    ListProduitComponent,
    ProductsingleComponent,
    ListViewProduitComponent,
    ListVehiculesComponent,
    ListViewVehiculesComponent,
    ListImmobilierComponent,
    ListViewImmobilierComponent,
    ListInfoMultiComponent,
    ListViewInfoMultiComponent,
    ListLoisirDiverComponent,
    ListViewLoisirDiverComponent,
    ListHabillementComponent,
    ListViewHabillementComponent,
    HomeVendeurComponent,
    DashboardVendeurComponent,
    AjouterProduitComponent,
    ListProduitVendeurComponent,
    CheckoutComponent,
    NotfoundComponent,
    ParamsVendeurComponent,
    LoginVendeurComponent,
    LoginAcheteurComponent,
    LoginLivreurComponent,
    HomeLivreurComponent,
    DashboardLivreurComponent,
    TachesLivreurComponent,
    ParamsLivreurComponent,
    ImageProduitComponent,
    SuccessPaymentComponent,
    AdminComponent,
    LoginAdminComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    Ng2SearchPipeModule,
    ReactiveFormsModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass:PaymeeService,
      multi:true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
