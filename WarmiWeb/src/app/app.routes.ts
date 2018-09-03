import { RouterModule, Routes } from '@angular/router';	

import {PrincipalComponent} from "./principal/principal.component";
import {RegistroComponent} from "./registro/registro.component";
import {DelitosComponent} from "./delitos/delitos.component";
import {DdhhComponent} from "./ddhh/ddhh.component";
import {DenunciasComponent} from "./denuncias/denuncias.component";
import {InstitucionesComponent} from "./instituciones/instituciones.component";
import {EstadisticasComponent} from "./estadisticas/estadisticas.component";
import {ReportesComponent} from "./reportes/reportes.component";

import {AppComponent} from "./app.component";


const app_routes: Routes = [
	{ path: 'principal', component: PrincipalComponent},
	{ path: 'registro', component: RegistroComponent},
	{ path: 'denuncias', component: DenunciasComponent},
	{ path: 'instituciones', component: InstitucionesComponent},
	{ path: 'ddhh', component: DdhhComponent},
	{ path: 'delitos', component: DelitosComponent},
	{ path: 'resumen', component: EstadisticasComponent},
	{ path: 'reportes', component: ReportesComponent},

	{ path: 'home', component: AppComponent},
	{ path: '**', pathMatch: 'full', redirectTo:'principal'}
];

export const app_routing = RouterModule.forRoot(app_routes);