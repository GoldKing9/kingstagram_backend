package project.kingstagram.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
