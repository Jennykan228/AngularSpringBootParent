package com.TechforProgram.AngularSpringBootBackend.model;

import java.util.List;
import java.util.Set;


import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "User")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
      // @Id
      // @GeneratedValue(strategy = GenerationType.AUTO)
      // private long uId;
      @Id
      @GeneratedValue(strategy = GenerationType.UUID)
      private String uId;
      private String firstName;
      private String lastName;
      private String fullName;
      private String emailAddress;
      private Set<String> subscribedToUsers;
      private Set<String> subscribers;
      private List<String> videoHistory;
      private Set<String> likeVideos;
      private Set<String> disLikeVideos;
      
}
