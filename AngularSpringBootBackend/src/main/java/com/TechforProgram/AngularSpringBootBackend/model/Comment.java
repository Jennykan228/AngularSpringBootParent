package com.TechforProgram.AngularSpringBootBackend.model;




import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     private String cId;
     private String text;
     private String authorId;
     private Integer likeCount;
     private Integer disLikeCount;
     @ManyToOne
     @JoinColumn(name = "vId")
     private Video video;

}
