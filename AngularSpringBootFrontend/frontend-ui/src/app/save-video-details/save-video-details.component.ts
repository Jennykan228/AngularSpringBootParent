import { Component, OnInit, signal, inject} from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatChipInputEvent,MatChipEditedEvent} from '@angular/material/chips';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {LiveAnnouncer} from '@angular/cdk/a11y';
import { ActivatedRoute } from '@angular/router';
import { FileService } from '../file.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import { Action } from 'rxjs/internal/scheduler/Action';

@Component({
  selector: 'app-save-video-details',
  templateUrl: './save-video-details.component.html',
  styleUrl: './save-video-details.component.css'
})
export class SaveVideoDetailsComponent implements OnInit{

  savedVideoDetailsForm:FormGroup;
  title:FormControl = new FormControl('');
  description:FormControl = new FormControl('');
  videoStatus:FormControl = new FormControl('');  
  readonly addOnBlur = true;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;
  // readonly fruits = signal<Fruit[]>([{name: 'Lemon'}, {name: 'Lime'}, {name: 'Apple'}]);
  readonly tags = signal<String[]>([]);
  readonly announcer = inject(LiveAnnouncer);
  fileSelected = false;
  selectedFileName ='';
  videoId = '';
  selectedFile!:File;



  constructor(private activatedRoute:ActivatedRoute, 
              private thumbNailService:FileService,
              private _snackBar:MatSnackBar){
    this.videoId = this.activatedRoute.snapshot.params['videoId'];
    this.savedVideoDetailsForm = new FormGroup({
      title:this.title,
      description:this.description,
      videoStatus:this.videoStatus
      
    })
  }
  ngOnInit(): void {
   // throw new Error('Method not implemented.');
  }

  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    // Add tags
    if (value) {
      // this.fruits.update(fruits => [...fruits, {name: value}]);
      this.tags.update(tags => [...tags, value]);
    }

    // Clear the input value
    event.chipInput!.clear();
  }

  // remove(fruit: Fruit): void {
  //   this.fruits.update(fruits => {
  //     const index = fruits.indexOf(fruit);
  //     if (index < 0) {
  //       return fruits;
  //     }
      remove(value: String): void {
        this.tags.update(tags => {
          const index = tags.indexOf(value);
          if (index < 0) {
            return tags;
          }

      tags.splice(index, 1);
      this.announcer.announce(`Removed ${value}`);
      return [...tags];
    });
  }

  // edit(fruit: Fruit, event: MatChipEditedEvent) {
  //   const value = event.value.trim();

  //   // Remove fruit if it no longer has a name
  //   if (!value) {
  //     this.remove(fruit);
  //     return;
  //   }

  //   // Edit existing fruit
  //   this.fruits.update(fruits => {
  //     const index = fruits.indexOf(fruit);
  //     if (index >= 0) {
  //       fruits[index].name = value;
  //       return [...fruits];
  //     }
  //     return fruits;
  //   });
  // }
  edit(value: String, event: MatChipEditedEvent) {
    const values = event.value.trim();

    // Remove fruit if it no longer has a name
    if (!values) {
      this.remove(value);
      return;
    }

    // Edit existing fruit
    this.tags.update(tags => {
      const index = tags.indexOf(value);
      console.log(index);
      if (index >= 0) {
        tags[index] = values;       
        return [...tags];
      }
      return tags;
    });
  }

  
  onUpload(){
    this.thumbNailService.uploadThumbnail(this.selectedFile,this.videoId)
       .subscribe(data => {
        console.log(data)
        //show an upload successful notification
        this._snackBar.open("Thumbnail upload successful", "ok");

      })

  }

  onFileSelected(event:Event){
    //@ts-ignore
    this.selectedFile=event.target.files[0];
    this.selectedFileName = this.selectedFile.name;
    this.fileSelected=true;
  }


}
