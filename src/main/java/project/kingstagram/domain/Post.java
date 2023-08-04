package project.kingstagram.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)//무분별한 객체생성에 대해 한번더 체크
public class Post {

    @Id @GeneratedValue
    private Long postId;
    @Lob
    private String postContent;
    private LocalDateTime postTime;
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;
    @Builder
    public Post(String postContent, String imageUrl, Users user) {
        this.postContent = postContent;
        this.postTime = LocalDateTime.now();
        this.imageUrl = imageUrl;
        this.user = user;
    }


    public void update(String postContent){
        this.postContent = postContent;
        this.postTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", postContent='" + postContent + '\'' +
                ", postTime=" + postTime +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
