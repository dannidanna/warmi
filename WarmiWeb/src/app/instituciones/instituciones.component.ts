import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { AngularFireDatabase} from 'angularfire2/database';
import { Observable } from 'rxjs/Observable';
import {NgForm} from '@angular/forms';


@Component({
  selector: 'app-instituciones',
  templateUrl: './instituciones.component.html',
  styleUrls: ['./instituciones.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class InstitucionesComponent implements OnInit {

	
	editando = false;
  mostrarForm = false;
  institucion: any = {};
  instituciones: any;
  inst : any;

  constructor(public db: AngularFireDatabase) { 
    this.institucion = {
      id:"",
      Nombre: "",
      Direccion: "",
      Telefono: "",
      Servicio: "",
      PaginaReferencia: ""
    }

    this.instituciones = this.db.list(`/Instituciones/`);
    this.inst = db.list(`Instituciones`).valueChanges();
  }

  nuevaInst(){
  	this.mostrarForm = true;
  }

  verInst(institucion){
 		this.editando = true;
 		this.institucion = institucion;
 		this.mostrarForm = true;
 	}

  guardarInst(){
    if(this.editando){        
     this.db.database.ref('Instituciones/'+ this.institucion.id).set(this.institucion);

    }else{

     this.institucion.id = Date.now();
     this.db.database.ref('Instituciones/'+ this.institucion.id).set(this.institucion);

    }
    this.instituciones = {};
    this.institucion = {
      id:"",
      Nombre: "",
      Direccion: "",
      Telefono: "",
      Servicio: "",
      PaginaReferencia: ""
    }
  }

  borrarInst(){
    this.editando = true;
    this.db.database.ref('Instituciones/'+ this.institucion.id).remove();
    this.institucion = {
      id:"",
      Nombre: "",
      Direccion: "",
      Telefono: "",
      Servicio: "",
      PaginaReferencia: ""
    }
  }

  cancel(){
    this.institucion = {
      id:"",
      Nombre: "",
      Direccion: "",
      Telefono: "",
      Servicio: "",
      PaginaReferencia: ""
    }
    this.editando = false;
    }

  ngOnInit() {
  }

}
