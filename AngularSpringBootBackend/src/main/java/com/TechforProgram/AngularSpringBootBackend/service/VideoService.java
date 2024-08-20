package com.TechforProgram.AngularSpringBootBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.TechforProgram.AngularSpringBootBackend.model.Video;
import com.TechforProgram.AngularSpringBootBackend.repository.VideoRepository;

@Service
public class VideoService {

    @Autowired
    private AzureSerice azureSerice;

    @Autowired
    private VideoRepository repo;
    public void uploadFile(MultipartFile file){
        //upload file to Azurite
        String videoUrl = azureSerice.uploadFile(file);
        var video = new Video();
        video.setVideoUrl(videoUrl);        
        //Save video metadata to mysql database
        repo.save(video);
        

    }
}
