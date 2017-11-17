import { RouterModule, Routes } from '@angular/router';	

import {PrincipalComponent} from "./principal/principal.component";
import {DelitosComponent} from "./delitos/delitos.component";
import {DdhhComponent} from "./ddhh/ddhh.component";
import {DenunciasComponent} from "./denuncias/denuncias.component";
import {InstitucionesComponent} from "./instituciones/instituciones.component";


const app_routes: Routes = [
	{ path: 'principal', component: PrincipalComponent},
	{ path: 'denuncias', component: DenunciasComponent},
	{ path: 'instituciones', component: InstitucionesComponent},
	{ path: 'ddhh', component: DdhhComponent},
	{ path: 'delitos', component: DelitosComponent},
	{ path: '**', pathMatch: 'full', redirectTo:'principal'}
];

export const app_routing = RouterModule.forRoot(app_routes);