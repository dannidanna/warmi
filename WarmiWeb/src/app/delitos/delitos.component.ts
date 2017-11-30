import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { AngularFireDatabase} from 'angularfire2/database';
import { Observable } from 'rxjs/Observable';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-delitos',
  templateUrl: './delitos.component.html',
  styleUrls: ['./delitos.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class DelitosComponent implements OnInit {

  editando = false;
  mostrarForm = false;
  delito: any = {};
  delitos: any;
  del : any;

  constructor(public db: AngularFireDatabase) { 
    this.delito = {
      id:"",
      Articulo: "",
      Nombre: "",
      Descripcion: ""
    }

    this.delitos = this.db.list(`/Delitos/`);
    this.del = db.list(`Delitos`).valueChanges();
  }

  nuevoDelito(){
  	this.mostrarForm = true;
  }

  verDelitos(delito){
 		this.editando = true;
 		this.delito = delito;
 		this.mostrarForm = true;
 	}

  guardarDelito(){
    if(this.editando){      
     this.db.database.ref('Delitos/'+ this.delito.id).set(this.delito);
     this.delito = {
      id:"",
      Articulo: "",
      Nombre: "",
      Descripcion: ""
    }
    }else{
     this.delito.id = Date.now();
     this.db.database.ref('Delitos/'+ this.delito.id).set(this.delito);
    }

    this.delitos = {};
    this.delito = {
      id:"",
      Articulo: "",
      Nombre: "",
      Descripcion: ""
    }
  }

  borrarDelito(){
    this.editando = true;
    this.db.database.ref('Delitos/'+ this.delito.id).remove();
  }

  cancel(){
    this.delito = {
      id:"",
      Articulo: "",
      Nombre: "",
      Descripcion: ""
    }
    this.editando = false;
  }

  ngOnInit() {
  }


}
