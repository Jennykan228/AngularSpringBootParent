package com.TechforProgram.AngularSpringBootBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class uploadVideoResponse {
    private String videoId;
    private String videoUrl;
    
}
