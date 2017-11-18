import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { AngularFireDatabase} from 'angularfire2/database';
import { Observable } from 'rxjs/Observable';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-ddhh',
  templateUrl: './ddhh.component.html',
  styleUrls: ['./ddhh.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class DdhhComponent implements OnInit {

  editando = false;
  mostrarForm = false;
  derecho: any = {};
  derechos: any;
  der : any;

  constructor(public db: AngularFireDatabase) { 
    this.derecho = {
      Articulo: "",
      Nombre: "",
      Descripcion: ""
    }

    this.derechos = this.db.list(`/DDHH/`);
    this.der = db.list(`DDHH`).valueChanges();
  }

  nuevoDerecho(){
  	this.mostrarForm = true;
  }

  verDerechos(derecho){
 		this.editando = true;
 		this.derecho = derecho;
 		this.mostrarForm = true;
 	}

  guardarDerecho(){
    this.derechos.push(this.derecho);
    this.derechos = {};
    this.derecho = {
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
    this.derecho = {
      Articulo: "",
      Nombre: "",
      Descripcion: ""
    }
    this.editando =false;
  }

  ngOnInit() {
  }



}
