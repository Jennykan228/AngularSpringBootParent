package com.TechforProgram.AngularSpringBootBackend.service;

import java.io.IOException;
import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.TechforProgram.AngularSpringBootBackend.configuration.AzureBlobStorageConfig;
import com.TechforProgram.AngularSpringBootBackend.service.SeviceInterface.MultifileService;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.azure.storage.blob.sas.BlobSasPermission;
import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;
import com.azure.storage.common.sas.SasProtocol;

@Service
public class AzureSerice implements MultifileService {
    
    protected static long SESSION_EXPIRE_SECOND = 3600;

    private String blobUrlWithSas;

    BlobClient blobClient;
    
    @Value("${azure.storage.container.name}")
    private String containerName;

    @Autowired
    AzureBlobStorageConfig blobServiceClient;


    @Override
    public String uploadFile(MultipartFile file){
        String filename = file.getOriginalFilename();
        BlobContainerClient blobContainerClient = blobServiceClient
                      .getBlobServiceClient()
                      .getBlobContainerClient(containerName);      

        blobClient = blobContainerClient.getBlobClient(filename);

        BlobHttpHeaders headers = new BlobHttpHeaders().setContentType(file.getContentType());

        try {
            blobClient.upload(file.getInputStream(), file.getSize(), true);
        } catch (IOException ex) {
            System.err.printf("Failed to upload from file: %s%n", ex.getMessage());
        }
       
        blobClient.setHttpHeaders(headers);
       
        blobUrlWithSas = blobClient.getBlobUrl() + "?" + generatedSasToken();
        System.out.println("Blob URL with SAS Token: " + blobUrlWithSas);
        
        return blobUrlWithSas;      
     
    }

    public String generatedSasToken() {
            // Set expiry time for the SAS token
            OffsetDateTime expiryTime = OffsetDateTime.now().plusSeconds(SESSION_EXPIRE_SECOND);

            // Define the SAS token parameters
            BlobSasPermission permissions = new BlobSasPermission()
                    .setReadPermission(true) // Allow read access
                    .setWritePermission(false) // Deny write access
                    .setDeletePermission(false); // Deny delete access

            BlobServiceSasSignatureValues sasValues = new BlobServiceSasSignatureValues(expiryTime, permissions)
                    .setProtocol(SasProtocol.HTTPS_HTTP); // Ensure SAS token is only valid over HTTPS & HTTP

            // Generate SAS token for the blob
            String sasToken = blobClient.generateSas(sasValues);
            return sasToken;   
    }
}
