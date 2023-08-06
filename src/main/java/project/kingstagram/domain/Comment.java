package project.kingstagram.domain;

import lombok.*;

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
    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;
    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;

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
