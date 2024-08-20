package com.TechforProgram.AngularSpringBootBackend.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

@Configuration
public class AzureBlobStorageConfig {
    @Value("${azure.storage.connection.string}")
    private String connectionString;

    // @Value("${azure.storage.account-name}")
    // private String accountName;

    // @Value("${azure.storage.sas-token}")
    // private String sasToken;

    @Bean
    public BlobServiceClient getBlobServiceClient() {
            BlobServiceClient serviceClient = new BlobServiceClientBuilder()
                            .connectionString(connectionString)
                            .buildClient();

            return serviceClient;

    } 
    // @Bean
    // public BlobServiceClient getBlobServiceClient() {
    //     return new BlobServiceClientBuilder()
    //             .endpoint("https://" + accountName + ".blob.core.windows.net")
    //             .sasToken(sasToken)
    //             .buildClient();
    // }
    
}
