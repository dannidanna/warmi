import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
//import { environment } from '../environments/environment';
import { FormsModule} from "@angular/forms";

import { AppComponent } from './app.component';
import { AngularFireModule } from 'angularfire2';
import { AngularFirestoreModule } from 'angularfire2/firestore';
import { AngularFireAuthModule } from 'angularfire2/auth';
import { AngularFireDatabaseModule } from 'angularfire2/database';

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
    FormsModule

  ],
  declarations: [ AppComponent ],
  bootstrap: [ AppComponent ]
})
export class AppModule {}