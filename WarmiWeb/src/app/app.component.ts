import { Component } from '@angular/core';
import { AngularFireDatabase} from 'angularfire2/database';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.css']
})
export class AppComponent {

	/*items: Observable<any[]>;
  constructor(db: AngularFirestore) {
    this.items = db.collection('items').valueChanges();
  }*/

  items: Observable<any[]>;
  myNotes: any;
  constructor(public db: AngularFireDatabase) {
    this.items = db.list('items').valueChanges();
    this.myNotes = db.list('Notas').valueChanges();
    
    /*this.getNotes()
        .subscribe(Notas =>
          {this.myNotes = Notas;}     
          );
        }
  

  public getNotes(){
    return this.db.list('Notas');*/
  }

  /*myNotes : FirebaseListObservable<any[]>;
  constructor(public db: AngularFireDatabase) {
    this.myNotes = db.list('Notas');
}
	/*myNotes = [
    	{id:1, title:'note 1', description:'This is a note 1'},
    	{id:2, title:'note 2', description:'This is a note 2'},
    	{id:3, title:'note 3', description:'This is a note 3'},
    	{id:4, title:'note 4', description:'This is a note 4'},
    ];*/

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