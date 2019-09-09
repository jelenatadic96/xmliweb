import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import {NgxPaginationModule} from 'ngx-pagination';
import { AngularFontAwesomeModule } from 'angular-font-awesome';

import { AppComponent } from './app.component';

import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

import { HInterceptorService } from './h-interceptor.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgbModule, NgbModalRef, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { LoginComponent } from './component/korisnik/login/login.component';
import { RegistracijaComponent } from './component/korisnik/registracija/registracija.component';
import { AuthService } from './service/korisnik/auth.service';
import { KorisnikService } from './service/korisnik/korisnik.service';
import { ViewAccommodationComponent } from './component/accommodation/view-accommodation/view-accommodation.component';
import { AccommodationService } from './service/accommodation/accommodation.service';
import { DatePipe } from '@angular/common';



const appRoutes: Routes = [
  /*{ path: 'record/:id', component: RecordDetailsComponent },
  { path: 'main', component: MainComponent },  
  { path: '', redirectTo: 'main', pathMatch: 'full' },*/
  { path: 'login', component: LoginComponent},
  { path: 'registracija', component: RegistracijaComponent},
  { path: '', redirectTo: 'address', pathMatch: 'full' },
  
  { path: '**', component: ViewAccommodationComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    LoginComponent,
    RegistracijaComponent,
    ViewAccommodationComponent,
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
    NgbActiveModal,
    AuthService,
    KorisnikService,
    AccommodationService,
    AuthService,
    DatePipe,

    {
      provide: HTTP_INTERCEPTORS,
      useClass: HInterceptorService,
      multi: true
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
