package com.TechforProgram.AngularSpringBootBackend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.TechforProgram.AngularSpringBootBackend.dto.VideoDto;
import com.TechforProgram.AngularSpringBootBackend.dto.uploadVideoResponse;
import com.TechforProgram.AngularSpringBootBackend.exception.CustomException;
import com.TechforProgram.AngularSpringBootBackend.model.Video;
import com.TechforProgram.AngularSpringBootBackend.repository.VideoRepository;

@Service
public class VideoService {

    @Autowired
    private AzureSerice azureSerice;

    @Autowired
    private VideoRepository repo;
    public uploadVideoResponse uploadFile(MultipartFile file){
        //upload file to Azurite
        String videoUrl = azureSerice.uploadFile(file);
        var video = new Video();
        video.setVideoUrl(videoUrl);        
        //Save video metadata to mysql database
        var savedVideo = repo.save(video);
        return new uploadVideoResponse(savedVideo.getVId(), savedVideo.getVideoUrl());
    }
    public VideoDto editVideo(VideoDto videoDto) throws CustomException{
         //find the video by video Id
         Optional<Video> savedVideo=repo.findById(videoDto.getId());
         if (!savedVideo.isPresent()) {
            throw new CustomException("1", "Video not found", "/");
        }
        // System.out.println(videoDto.getId());
       
        // Video savedVideo = repo.findById(videoDto.getId())
        // .orElseThrow(()->new IllegalArgumentException("can not find vedo by id: " + videoDto.getId()));
        
        //map videoDto to video
        savedVideo.get().setTitle(videoDto.getTitle());
        savedVideo.get().setDescription(videoDto.getDescription());
        savedVideo.get().setTabs(videoDto.getTabs());
        savedVideo.get().setThumbnailUrl(videoDto.getThumbnailUrl());
        savedVideo.get().setVideoStatus(videoDto.getVideoStatus());
        //    savedVideo.setTitle(videoDto.getTitle());
        // savedVideo.setDescription(videoDto.getDescription());
        // savedVideo.setTabs(videoDto.getTabs());
        // savedVideo.setThumbnailUrl(videoDto.getThumbnailUrl());
        // savedVideo.setVideoStatus(videoDto.getVideoStatus());
        //sava video to database
        repo.save(savedVideo.get());
        return videoDto;    
    }
    public String uploadThumbNail(MultipartFile file, String videoId) {
         //retrieve video by videoId
         var savedVideo= repo.findById(videoId)
         .orElseThrow(()->new IllegalArgumentException("can not find vedo by id: " + videoId));
         
         //upload thumbnail and set url
         String thumbnailUrl = azureSerice.uploadFile(file);
         
         savedVideo.setThumbnailUrl(thumbnailUrl);     

         //Save video metadata to mysql database
         repo.save(savedVideo);

         return thumbnailUrl;
         
    
    
    }

}
