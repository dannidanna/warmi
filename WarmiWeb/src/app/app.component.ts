import { Component } from '@angular/core';
import { AngularFirestore } from 'angularfire2/firestore';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.css']
})
export class AppComponent {

	myNotes = [
    	{id:1, title:'note 1', description:'This is a note 1'},
    	{id:2, title:'note 2', description:'This is a note 2'},
    	{id:3, title:'note 3', description:'This is a note 3'},
    	{id:4, title:'note 4', description:'This is a note 4'},
    ];

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
 		if(this.editing){
 			alert("Se esta editando la nota");
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

 			}
 	}

 	viewNote(note){
 		this.editing = true;
 		this.note = note;
 		this.showForm = true;
 	}

  items: Observable<any[]>;
  constructor(db: AngularFirestore) {
    this.items = db.collection('items').valueChanges();
  }


}