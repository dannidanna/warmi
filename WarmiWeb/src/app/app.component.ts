import { Component } from '@angular/core';
import { AngularFireDatabase} from 'angularfire2/database';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.css']
})
export class AppComponent {

  items: Observable<any[]>;
  myNotes: any;
  constructor(public db: AngularFireDatabase) {
    this.items = db.list('items').valueChanges();
    this.myNotes = db.list('Notas').valueChanges();
    
    //return this.db.list('Notas');
  }


    note = {id:null, title:null, description:null};

    editing = false;
    showForm = false;
  	addNote(){
  	this.showForm = true;
 	 }

 	cancel(){
  	this.showForm = false;
 	 } 

 	createNote(){ 		
 		this.note.id = Date.now();
 		this.db.database.ref('Notas/'+ this.note.id).set(this.note);
 		/*if(this.editing){
 			var me = this;
 			this.myNotes.forEach(function(el, i){
 				if(el.id === me.note.id){
 					me.myNotes[i]=me.note;
 				}

 			});

 			me.showForm = false;
 		}
 			else{
 		this.note.id = Date.now();
 		this.myNotes.push(this.note);
 		this.showForm = false;
 		this.note = {id:null, title:null, description:null};

 			}*/
 	}

 	viewNote(note){
 		this.editing = true;
 		this.note = note;
 		this.showForm = true;
 	}

 	/*delete(){
 		var me = this;
 		this.myNotes.forEach(function(el, i){
 				if(el === me.note){
 					me.myNotes.splice(i, 1);
 				}

 			});
 		this.showForm = false;
 		this.note = {id:null, title:null, description:null};
 	}*/

  


}