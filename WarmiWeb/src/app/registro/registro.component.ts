import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { AngularFireDatabase} from 'angularfire2/database';
import { Observable } from 'rxjs/Observable';
import { AuthService } from '../auth.service';

import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Console } from '@angular/core/src/console';


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
    this.authService.signup(this.email, this.password, this.usuario);
    console.log( 'NOMBRE USUARIO',this.usuario.Nombre);
    this.authService.registro(this.email, this.usuario.Nombre, this.usuario.Rol);
    this.email = this.password = this.usuario.Nombre=this.usuario.Correo=this.usuario.Rol='';   
    
  }

  login() {
    this.authService.login(this.email, this.password);
    this.email = this.password = '';         
  }


  logout() {
    this.authService.logout();
  }

  roles(num){
    if(num == "1"){
      this.usuario.Rol = "Administrador 1";
    }else{
      if(num == "2"){
        this.usuario.Rol = "Administrador 2";
      }else 
      this.usuario.Rol = "Administrador 3";
    }
    
    console.log(this.usuario.Rol);
  }

}
