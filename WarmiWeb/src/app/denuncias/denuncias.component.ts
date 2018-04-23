import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { AngularFireDatabase} from 'angularfire2/database';
import { Observable } from 'rxjs/Observable';
//import {NgForm} from '@angular/forms';
import * as jsPDF from 'jspdf';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
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
  denuncia: any = {};
  usuario:any = {};
  

  constructor(public db: AngularFireDatabase) {   
  
      this.usuarios = this.db.list('Usuarios').valueChanges();      
      this.usuariosAll = this.db.list('DenunciasAll').valueChanges();
      }
    
  specialElementHandlers = {
        // element with id of "bypass" - jQuery style selector
        '.no-export': function(element, renderer) {
          // true = "handled elsewhere, bypass text extraction"
          return true;
        }
      };    
    
  descargarDenuncia(denuncia){
      var source = document.getElementById('content').innerHTML;
      var margins = {
        top: 10,
        bottom: 10,
        left: 10,
        width: 595
      };
      const doc = new jsPDF();

      doc.fromHTML(
        source, // HTML string or DOM elem ref.
        margins.left,
        margins.top, {
          'width': margins.width,
          'elementHandlers': this.specialElementHandlers
        },
    
        function(dispose) {
          doc.save('Denuncia de ' + Date() + '.pdf');
        }, margins);      
    }    

    verDenuncia(denuncia){
      this.buscando = false;
      this.usuarios.forEach(user => {
        this.usuario = user;
        console.log(this.usuario);
    
        //console.log(value);
      });
     this.mostrarForm = true;
      this.denuncia = denuncia;
   }
   buscar(fecha){
     console.log(fecha);
    this.buscando = false;

   }
   

  ngOnInit() {
  }


}

