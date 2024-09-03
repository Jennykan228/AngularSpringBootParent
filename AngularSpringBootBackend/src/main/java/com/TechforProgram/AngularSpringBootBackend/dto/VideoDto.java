package com.TechforProgram.AngularSpringBootBackend.dto;

import java.util.Set;

import com.TechforProgram.AngularSpringBootBackend.model.VideoStatus;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto {
    
    private String id;
    private String title;
    private String description;    
    private Set<String> tabs;
    private String videoUrl;
    private VideoStatus videoStatus;    
    private String thumbnailUrl;


}
