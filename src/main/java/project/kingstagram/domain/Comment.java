package project.kingstagram.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity

public class Comment {
    @Id
    @GeneratedValue
    private Long commentId;
    @Lob
    private String commentContent;
    private LocalDateTime commentTime;
    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;
}
