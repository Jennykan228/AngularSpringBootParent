import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { UploadVideoResponse } from "./upload-video/upload-video-response"


@Injectable({
  providedIn: 'root'
})
export class FileService {

  constructor(private httpClient:HttpClient) {  }

  // uploadfile(file:File):Observable<UploadVideoResponse>{


  //     const formData = new FormData()
  //         formData.append('file', file, file.name)
      
  //        return this.httpClient.post<UploadVideoResponse>("http://localhost:8081/api/videos/upload", formData);

  // }
  uploadfile(fileEntry:File): Observable<UploadVideoResponse>{

    const formData = new FormData()
        formData.append('file', fileEntry, fileEntry.name)
    
       return this.httpClient.post<UploadVideoResponse>("http://localhost:8081/api/videos/upload", formData);

}
uploadThumbnail(fileEntry:File, videoId:string): Observable<string>{

  const formData = new FormData()
      formData.append('file', fileEntry, fileEntry.name)
  formData.append('videoId', videoId);

     return this.httpClient.post("http://localhost:8081/api/videos/thumbNail", formData,{responseType:'text'});

}
}
