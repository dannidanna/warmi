import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { AngularFireDatabase} from 'angularfire2/database';
import { FirebaseApp } from 'angularfire2';
import { Observable } from 'rxjs/Observable';
import { Index } from '@firebase/database/dist/src/core/snap/indexes/Index';
import { IndexMap } from '@firebase/database/dist/src/core/snap/IndexMap';

@Component({
  selector: 'app-estadisticas',
  templateUrl: './estadisticas.component.html',
  styleUrls: ['./estadisticas.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class EstadisticasComponent implements OnInit {

  usuariosFecha: any=[];
  usuariosAll: any = [];
  oport: any = [];
  vFisica: number;
  vPsicologicca: any;
  vSexual: any;
  vEconomica: any;
  vPatrimonial: any;
  vSimbolica: any;
  vDomestica: any;
  vInstitucional: any;
  vLaboral: any;
  otros: any;
  tipos: any = {};

  constructor(public db: AngularFireDatabase) {       
    this.usuariosAll = this.db.list('DenunciasAll').valueChanges();
    this.buscar();
  }

  buscar(){
    this.usuariosFecha = [];

    console.log(this.usuariosAll.length );
    var inde;
    var vFisCont = 0;
    var vPsiCont = 0;
    var vSexCont = 0;
    var vEcoCont = 0;
    var vPatCont = 0;
    var vSimCont = 0;
    var vDomCont = 0;
    var vInsCont = 0;
    var vLabCont = 0;
    var vOtroCont = 0;
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
             url: "",
             tipoDenuncia: "",
             };

          this.tipos = {
              totalDen: inde,
              viFis: 0,
              viPsico:0,
              viSexu: 0,
              viEco: 0,
              viPatri: 0,
              viSimb: 0,
              viDomes: 0,
              viInsti: 0,
              viLabo: 0,
              viOtr: 0
              };
         var vFis = "Violencia física";
         var vPsi = "Violencia psicológica";
         var vSex = "Violencia sexual";
         var vEco = "Violencia económica";
         var vPat = "Violencia patrimonial";
         var vSim = "Violencia simbólica";
         var vDom = "Violencia doméstica";
         var vIns = "Violencia institucional";
         var vLab = "Violencia laboral";
         var vOtro = "Otro";
         var op = opportunities[i];
         if(op.tipoDenuncia === vFis){
            vFisCont = vFisCont + 1;
         }
         if(op.tipoDenuncia === vPsi){
          vPsiCont = vPsiCont + 1;
         }
         if(op.tipoDenuncia === vSex){
          vSexCont = vSexCont + 1;
         }
         if(op.tipoDenuncia === vEco){
          vEcoCont = vEcoCont + 1;
         }
         if(op.tipoDenuncia === vPat){
          vPatCont = vPatCont + 1;
         }
         if(op.tipoDenuncia === vSim){
          vSimCont = vSimCont + 1;
         }
         if(op.tipoDenuncia === vDom){
          vDomCont = vDomCont + 1;
         }
         if(op.tipoDenuncia === vIns){
          vInsCont = vInsCont + 1;
         }
         if(op.tipoDenuncia === vLab){
          vLabCont = vLabCont + 1;
         }
         if(op.tipoDenuncia === vOtro){
          vOtroCont = vOtroCont + 1;
         }
       }
       this.tipos.viFis = vFisCont;
       this.tipos.viPsico= vPsiCont;
       this.tipos.viSexu = vSexCont;
       this.tipos.viEco = vEcoCont;
       this.tipos.viPatri = vPatCont;
       this.tipos.viSimb = vSimCont;
       this.tipos.viDomes = vDomCont;
       this.tipos.viInsti = vInsCont;
       this.tipos.viLabo = vLabCont;
       this.tipos.viOtr = vOtroCont;
      });
  }


  ngOnInit() {
  }

}
