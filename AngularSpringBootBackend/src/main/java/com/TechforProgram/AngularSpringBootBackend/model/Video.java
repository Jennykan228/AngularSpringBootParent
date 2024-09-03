package com.TechforProgram.AngularSpringBootBackend.model;

import java.util.List;
import java.util.Set;


import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Video")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video {
    // @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    //private long vId;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String vId;
    private String title;
    private String description;
    private String useId;
    private Integer likes;
    private Integer dislike;
    private Set<String> tabs;
    private String videoUrl;
    private VideoStatus videoStatus;
    private Integer viewCount;
    private String thumbnailUrl;
    @OneToMany(mappedBy = "video", fetch = FetchType.EAGER)
    private List<Comment> commentList;
    

    
}
