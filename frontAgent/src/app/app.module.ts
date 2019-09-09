import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import {NgxPaginationModule} from 'ngx-pagination';
import { NgbModule, NgbModalRef, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { AngularFontAwesomeModule } from 'angular-font-awesome';

import { AppComponent } from './app.component';

import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

import { HInterceptorService } from './h-interceptor.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthService } from './service/korisnik/auth.service';
import { PorukeComponent } from './component/korisnik/poruke/poruke.component';
import { DodavanjeSmestajaComponent } from './component/accommodation/dodavanje-smestaja/dodavanje-smestaja.component';
import { LoginComponent } from './component/korisnik/login/login.component';
import { PorukeService } from './service/korisnik/poruke.service';
import { KorisnikService } from './service/korisnik/korisnik.service';
import { SrediCeneComponent } from './component/accommodation/sredi-cene/sredi-cene.component';



const appRoutes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: 'poruke', component: PorukeComponent},
  { path: 'addaccomodation', component: DodavanjeSmestajaComponent},
  { path: 'managePrices', component: SrediCeneComponent},
  { path: '', redirectTo: 'login', pathMatch: 'full' }, 
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    LoginComponent,
    PorukeComponent,
    DodavanjeSmestajaComponent,
    SrediCeneComponent
  ],
  imports: [
    BrowserModule,
    AngularFontAwesomeModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    FormsModule,
    HttpModule,
    HttpClientModule,
    NgxPaginationModule,
    NgMultiSelectDropDownModule.forRoot(),
    NgbModule.forRoot(),
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true, useHash: true } // <-- debugging purposes only
    )
  ],
  providers: [ //registrujem servise obaveznoo!!!!!!
    AuthService,
    NgbActiveModal,
    PorukeService,
    KorisnikService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HInterceptorService,
      multi: true
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
