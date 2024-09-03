package com.TechforProgram.AngularSpringBootBackend.service.SeviceInterface;

import org.springframework.web.multipart.MultipartFile;

public interface MultifileService {
    String uploadFile(MultipartFile file);
}
