import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { AngularFireDatabase} from 'angularfire2/database';
import { Observable } from 'rxjs/Observable';
import {NgForm} from '@angular/forms';
import * as jsPDF from 'jspdf';
import { FirebaseListObservable } from "angularfire2/database-deprecated";

@Component({
  selector: 'app-denuncias',
  templateUrl: './denuncias.component.html',
  styleUrls: ['./denuncias.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class DenunciasComponent implements OnInit {

	editando = false;
  mostrarForm = false;
  usuarios: any = [];
  usuariosAll: any = [];
  denuncia: any = {};

  constructor(public db: AngularFireDatabase) {   
  
      this.usuarios = this.db.list('Usuarios').valueChanges();
      this.usuariosAll = this.db.list('DenunciasAll').valueChanges();

  }

    descargarDenuncia(denuncia){
      const doc = new jsPDF();
      var objJson = JSON.stringify(denuncia);
      var parJson = JSON.parse(objJson);

      doc.text("DENUNCIA", 100, 10);
      doc.text("Fecha de la denuncia: " + parJson.fecha, 10, 20);
      doc.text("Descripcion: " + parJson.descrip, 10, 30);
      doc.text("Nombre Victima: " + parJson.nomVic, 10, 40);
      doc.text("Telefono Victima: " + parJson.numVic, 10, 50);
      doc.text("Nombre agresor: " + parJson.nomAgre, 10, 60);
      doc.text("Nombre Denunciante: " + parJson.nombreUsuario, 10, 70);
      doc.text("Relacion denunciante - victima: " + parJson.relacion, 10, 80);
      doc.text("Telefono denunciante: " + parJson.numeroUsuario, 10, 90);
      doc.text("Imagen adj. a la denuncia: " + parJson.Denuncias, 10, 100);
      doc.save('Denuncia'+ Date());
    }

    verDenuncia(denuncia){

     this.mostrarForm = true;
      this.denuncia = denuncia;
   }
   
   

  ngOnInit() {
  }

}

function verDenunciaJS(denuncia){

  console.log(denuncia.fecha);
  return "HELLO";
  }

/*editando = false;
  mostrarForm = false;
  denuncia: any = {};
  denuncias: any = {};
  den: any;
  

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
    this.denuncias = this.db.list('Usuarios').valueChanges();
    const relative = db.object('Usuarios').valueChanges();
    

  }*/
