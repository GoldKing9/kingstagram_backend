package project.kingstagram.domain;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Post {

    @Id @GeneratedValue
    private Long postId;
    private String postTitle;
    @Lob
    private String postContent;
    private LocalDateTime postTime;
    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;
}
