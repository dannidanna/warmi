import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { AngularFireDatabase} from 'angularfire2/database';
import { Observable } from 'rxjs/Observable';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-denuncias',
  templateUrl: './denuncias.component.html',
  styleUrls: ['./denuncias.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class DenunciasComponent implements OnInit {

	editando = false;
  mostrarForm = false;
  denuncia: any = {};
  denuncias: any;

  constructor(public db: AngularFireDatabase) { 
  	this.denuncia = {
      nombreVictima: "",
      numeroVictima: "",
      nombreAgresor: "",
      relacion: "",
      fecha: "",
      localizacion: "",
      descripcion: "",
      urlDescarga:""
    };
    this.denuncias = this.db.list('Usuarios/').valueChanges();
  }

  verDenuncia(denuncia){
 		this.editando = true;
 		this.denuncia = denuncia;
 		this.mostrarForm = true;
 	}

  ngOnInit() {
  }

}
