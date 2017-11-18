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
    this.delitos.push(this.delito);
    this.delitos = {};
    this.delito = {
      Articulo: "",
      Nombre: "",
      Descripcion: ""
    }
  }

  borrarInst(key:String){
    this.editando = true;
    //this.db.database.ref([`/Instituciones/`, key]).remove();
  }

  cancel(){
    this.delito = {
      Articulo: "",
      Nombre: "",
      Descripcion: ""
    }
    this.editando = false;
  }

  ngOnInit() {
  }


}
