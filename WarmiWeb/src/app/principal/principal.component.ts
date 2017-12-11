import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { AngularFireDatabase} from 'angularfire2/database';
import { Observable } from 'rxjs/Observable';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class PrincipalComponent implements OnInit {
	email: string;
  password: string;
  user : "";
  users: any;


  constructor(public authService: AuthService, public db: AngularFireDatabase) {
    this.users = this.db.list(`Usuarios/`);
  }

  signup() {
    this.authService.signup(this.email, this.password, "");
    this.email = this.password = '';
  }

  login() {

    this.authService.login(this.email, this.password);
    this.email = this.password = '';    
  }

  logout() {
    this.authService.logout();
  }

  ngOnInit() {
  }

}
