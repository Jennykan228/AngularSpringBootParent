package com.TechforProgram.AngularSpringBootBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.TechforProgram.AngularSpringBootBackend.dto.VideoDto;
import com.TechforProgram.AngularSpringBootBackend.dto.uploadVideoResponse;
import com.TechforProgram.AngularSpringBootBackend.exception.CustomException;
import com.TechforProgram.AngularSpringBootBackend.service.VideoService;

@RestController
@RequestMapping("/api/videos")
public class VideoController {
    
    @Autowired
    private VideoService videoService;
    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public uploadVideoResponse uploadFile(@RequestParam("file") MultipartFile file) {
         return videoService.uploadFile(file);
    }

    @PostMapping("/thumbNail")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadThumbNail(@RequestParam("file") MultipartFile file, @RequestParam("videoId") String videoId) {
         return videoService.uploadThumbNail(file, videoId);
    }

    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.OK)
    public VideoDto editVideoMetadata(@RequestBody VideoDto videoDto) throws CustomException{        
         return videoService.editVideo(videoDto);
    }
}
