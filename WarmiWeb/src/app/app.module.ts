import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule} from "@angular/forms";

//rutas
import { app_routing } from "./app.routes";


import { AppComponent } from './app.component';
import { AngularFireModule } from 'angularfire2';
import { AngularFirestoreModule } from 'angularfire2/firestore';
import { AngularFireAuthModule } from 'angularfire2/auth';
import { AngularFireDatabaseModule } from 'angularfire2/database';
import { DelitosComponent } from './delitos/delitos.component';
import { DdhhComponent } from './ddhh/ddhh.component';
import { InstitucionesComponent } from './instituciones/instituciones.component';
import { DenunciasComponent } from './denuncias/denuncias.component';
import { PrincipalComponent } from './principal/principal.component';
import { RegistroComponent } from './registro/registro.component';

var config = {
    apiKey: "AIzaSyDUzOVcMFGzCHTiPhYs78q-YKCFV_rBAVk",
    authDomain: "warmi-6afeb.firebaseapp.com",
    databaseURL: "https://warmi-6afeb.firebaseio.com",
    projectId: "warmi-6afeb",
    storageBucket: "",
    messagingSenderId: "878784051025"
  };

@NgModule({
  imports: [
    BrowserModule,
    AngularFireModule.initializeApp(config),
    AngularFirestoreModule,
    AngularFireAuthModule,
    AngularFireDatabaseModule,
    FormsModule,
    app_routing

  ],
  declarations: [ AppComponent, DelitosComponent, DdhhComponent, InstitucionesComponent, DenunciasComponent, PrincipalComponent, RegistroComponent ],
  bootstrap: [ AppComponent ]
})
export class AppModule {}