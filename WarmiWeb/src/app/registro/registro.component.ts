import { Component, OnInit, ViewEncapsulation } from '@angular/core';

import { AngularFireDatabase} from 'angularfire2/database';
import { Observable } from 'rxjs/Observable';
import { AuthService } from '../auth.service';

import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';


@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class RegistroComponent implements OnInit {

  email: string;
  password: string;
  nombre: string;
  rol: string;
  usuario: any= {};
  usuarios: any;


  constructor(public authService: AuthService, public db: AngularFireDatabase){
    this.usuario = {
      Nombre: "",
      Correo: "",
      Rol: ""
    }
  }
  ngOnInit() {
  }


  signup() {
    this.usuario.Correo = this.email;
    console.log('EMAIL',this.usuario.Correo);
    this.authService.signup(this.email, this.password, this.usuario);
    //console.log( 'ID REGISTRO',this.authService.getUid());
    this.email = this.password = this.usuario.Nombre=this.usuario.Correo=this.usuario.Rol='';   
    
  }

  login() {
    this.authService.login(this.email, this.password);
    this.email = this.password = '';         
  }


  logout() {
    this.authService.logout();
  }

}
