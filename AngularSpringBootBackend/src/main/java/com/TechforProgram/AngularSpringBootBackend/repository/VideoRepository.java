package com.TechforProgram.AngularSpringBootBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TechforProgram.AngularSpringBootBackend.model.Video;

public interface VideoRepository extends JpaRepository<Video,String> {
    
}
