import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgxFileDropEntry, FileSystemFileEntry, FileSystemDirectoryEntry } from 'ngx-file-drop';
import { FileService } from '../file.service';

@Component({
  selector: 'app-upload-video',
  templateUrl: './upload-video.component.html',
  styleUrls: ['./upload-video.component.css']
})
export class UploadVideoComponent {

  public files: NgxFileDropEntry[] = [];
  fileEntry:FileSystemFileEntry | undefined;
  fileUploaded:boolean = false;

  constructor(private videoService:FileService, private router:Router){

  }

  public dropped(files: NgxFileDropEntry[]) {
    this.files = files;
    for (const droppedFile of files) {

      // Is it a file?
      if (droppedFile.fileEntry.isFile) {
        this.fileEntry = droppedFile.fileEntry as FileSystemFileEntry;
        this.fileEntry.file((file: File) => {

          // Here you can access the real file
          console.log(droppedFile.relativePath, file);

          this.fileUploaded = true;


          /**
          // You could upload it like this:
          const formData = new FormData()
          formData.append('logo', file, relativePath)

          // Headers
          const headers = new HttpHeaders({
            'security-token': 'mytoken'
          })

          this.http.post('https://mybackend.com/api/upload/sanitize-and-save-logo', formData, { headers: headers, responseType: 'blob' })
          .subscribe(data => {
            // Sanitized logo returned from backend
          })
          **/

        });
      } else {
        // It was a directory (empty directories are added, otherwise only files)
        const fileEntry = droppedFile.fileEntry as FileSystemDirectoryEntry;
        console.log(droppedFile.relativePath, fileEntry);
      }
    }
  }

  public fileOver(event:any){
    console.log(event);
  }

  public fileLeave(event:any){
    console.log(event);
  }

  // uploadVideo(){
  //   //upload video to backend
  //     if(this.fileEntry!== undefined){
  //       this.fileEntry.file((file:File) => {
  //       this.videoService.uploadfile(file).subscribe(() =>{
  //         console.log( "upload successful");
  //       })
  //     })
      
  //     }

  // }
  uploadVideo(){
    //upload video to backend
      if(this.fileEntry!== undefined){
        this.fileEntry.file(file => {
        this.videoService.uploadfile(file).subscribe(data =>{
          this.router.navigateByUrl("/save-video-details/"+data.videoId);
        })
      })
      
      }

  }
}