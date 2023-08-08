package project.kingstagram.domain;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue
    private Long commentId;
    @Lob
    private String commentContent;
    private LocalDateTime commentTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post post;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Users user;

    public void update(String content) {
        this.commentContent = content;
        this.commentTime = LocalDateTime.now();
    }
    public void add(Post post){
        this.post = post;
        post.getComments().add(this);
    }

//로그 확인용
//    @Override
//    public String toString() {
//        return "Comment{" +
//                "commentId=" + commentId +
//                ", commentContent='" + commentContent + '\'' +
//                ", commentTime=" + commentTime +
//                '}';
//    }
}
