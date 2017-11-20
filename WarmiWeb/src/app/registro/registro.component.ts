import { Component, OnInit, ViewEncapsulation } from '@angular/core';

import { AngularFireDatabase} from 'angularfire2/database';
import { Observable } from 'rxjs/Observable';

import { AuthService } from '../auth.service';
import {NgForm} from '@angular/forms';


@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class RegistroComponent implements OnInit {
	
	usuario :any = {};
	usuarios:any;

  constructor(public db: AngularFireDatabase){
  	this.usuario = {
      Nombre: "",
      Correo: "",
      Rol:""
    }

    this.usuarios = this.db.list(`/Usuarios/`);

  }

  guardarUsuario(){      
     this.db.database.ref('Usuarios/').push(this.usuario);
 }

  ngOnInit() {
  }

}
