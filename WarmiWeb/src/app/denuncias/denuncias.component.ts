import { Component, OnInit, ViewEncapsulation} from '@angular/core';
import { AngularFireDatabase} from 'angularfire2/database';
import { Observable } from 'rxjs/Observable';
//import {NgForm} from '@angular/forms';
import * as jsPDF from 'jspdf';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { FirebaseApp } from 'angularfire2';
import { Index } from '@firebase/database/dist/src/core/snap/indexes/Index';
import { IndexMap } from '@firebase/database/dist/src/core/snap/IndexMap';
//import { FirebaseListObservable } from "angularfire2/database-deprecated";
//import { Observer } from 'rxjs/Rx';

@Component({
  selector: 'app-denuncias',
  templateUrl: './denuncias.component.html',
  styleUrls: ['./denuncias.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class DenunciasComponent implements OnInit {

	editando = false;
  mostrarForm = false;
  buscando = true;
  usuarios: any = [];
  usuariosAll: any = [];
  usuariosFecha: any=[];
  denuncia: any = {};
  usuario:any = {};
  fechaDenuncia: any;
  oport: any = [];
  

  constructor(public db: AngularFireDatabase) {   
  
      this.usuarios = this.db.list('DenunciasAll').valueChanges();      
      this.usuariosAll = this.db.list('DenunciasAll').valueChanges();
      this.fechaDenuncia = {
        fechaDen: ""
        }
      }
    
  specialElementHandlers = {
        // element with id of "bypass" - jQuery style selector
        '.no-export': function(element, renderer) {
          // true = "handled elsewhere, bypass text extraction"
          return true;
        }
      };    
    
  descargarDenuncia(){

      var source = document.getElementById('content').innerHTML;
      var margins = {
        top: 25,
        bottom: 10,
        left: 10,
        width: 595
      };
      var imagen = this.denuncia.url != undefined? true: false;
      var fechaImagen= this.denuncia.fecha;
      var dirImagen = this.denuncia.dir;
      var key = this.denuncia.id;
      const doc = new jsPDF();
      doc.text(160, 15, 'WARMI - WEB');
      doc.setTextColor(150);
      doc.setFontSize(10);
      doc.text(130, 20, 'Parte administrativa de la app movil Warmi');
      doc.setDrawColor(150);
      doc.setLineWidth(0.5);
      doc.line(10, 25, 200, 25);
      doc.setTextColor(20);
      doc.text(100, 280, '1');
      doc.setFont("arial")
      doc.fromHTML(
        source, // HTML string or DOM elem ref.'
        margins.left,
        margins.top, {
          'width': margins.width,
          'elementHandlers': this.specialElementHandlers
        },
        
        function(dispose) {
          
          if(imagen){
            doc.setTextColor(20);
            doc.setFontSize(15);
            doc.text(160, 15, 'WARMI - WEB');
            doc.setTextColor(150);
            doc.setFontSize(10);
            doc.text(130, 20, 'Parte administrativa de la app movil Warmi');
            doc.setDrawColor(150);
            doc.setLineWidth(0.5);
            doc.line(10, 25, 200, 25);
            doc.setTextColor(20);
            doc.text(10, 180, 'Imagen tomada en fecha: ' + fechaImagen);
            doc.text(10, 185, 'Ubicación: ' + dirImagen);
            doc.text(100, 280, '2');
          }
          doc.save('Denuncia de ' + Date() + '.pdf');
        }, margins);      
    }   

    verDenuncia(denunciaVer){
      this.buscando = false;
      this.usuarios.forEach(user => {
        this.usuario = user;
        console.log(this.usuario);
      });
      this.mostrarForm = true;
      this.denuncia = denunciaVer;
   }
   
   buscar(){
     this.usuariosFecha = [];
    var inde;
      this.usuariosAll
      .subscribe( opportunities => {
          inde = opportunities.length;
          this.oport = opportunities;
          for(let i=0; i<inde; i++){
            var den = {
              descrip: "",
              dir: "",
              fecha: "",
              nomAgre: "",
              nomVic: "",
              nombreUsuario: "",
              numVic: "",
              numeroUsuario: "",
              relacion: "",
              url: ""
              };
          if(this.fechaDenuncia.fechaDen === opportunities[i].fecha){
            this.usuariosFecha.push(opportunities[i]);
          }
        }
       });
    this.buscando = false;

   }

  verDenunciaFecha(denuncia, fechaBusqueda){
    if(denuncia.fecha === fechaBusqueda){
     // console.log("true");
    }
   this.mostrarForm = true;
    this.denuncia = denuncia;
 }
   

  ngOnInit() {
  }


}

