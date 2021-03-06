import { Injectable } from '@angular/core';
import { AngularFireAuth } from 'angularfire2/auth';
import * as firebase from 'firebase/app';
import { Observable } from 'rxjs/Observable';

import { AngularFireDatabase} from 'angularfire2/database';

@Injectable()
export class AuthService {

    user: Observable<firebase.User>;    
    usuario: any= {};
    u:any={};


  private userDetails: firebase.User = null;

  constructor(private firebaseAuth: AngularFireAuth, public db: AngularFireDatabase) {
    this.user = firebaseAuth.authState;
    this.usuario = {
      Nombre: "",
      Correo: "",
      Rol: ""
    }

  }

  signup(email: string, password: string, us:any) {
    this.firebaseAuth
      .auth
      .createUserWithEmailAndPassword(email, password)
      .then(value => {
        console.log('Success!', value, email);
      })
      .catch(err => {
        console.log('Something went wrong:',err.message);
      });

  }

  login(email: string, password: string) {
    this.firebaseAuth
      .auth
      .signInWithEmailAndPassword(email, password)
      .then(value => {
        console.log('Nice, it worked!');
      })
      .catch(err => {
        console.log('Something went wrong:',err.message);
      });
  }

  logout() {
    this.firebaseAuth
      .auth
      .signOut();
  }

  registro(email: string, nombre: string, rol: string){
    firebase.auth().onAuthStateChanged((user) => {
      if (user) { 
        if(user.email=== email){
            this.u = {
              Nombre: nombre,
              Correo: email,
              Rol: rol
              }
            this.db.database.ref('Usuarios/'+ user.uid).set(this.u);
        }
      }      
    });
  }

}
