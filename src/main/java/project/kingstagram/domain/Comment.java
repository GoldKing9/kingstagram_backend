package project.kingstagram.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @Lob
    private String commentContent;
    private LocalDateTime commentTime;
    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;

    //연관관계 편의메서드 - 한 transactional내에 있어야한다? 왜냐? jpa레파지토리는 메서드가 끝나면 닫는다, 그래서 2개의 레파지토리를 사용함으로써 트랜젝션을 열어둬야한다
    public void add(Post post){
        this.post = post;
        post.getComments().add(this);
    }

    // 사용자 객체도 있어야함
    @ManyToOne
    @JoinColumn(name="userId")
    private Users user;

    @Builder
    public Comment(String commentContent, Post post, Users user) {
        this.commentContent = commentContent;
        this.commentTime = LocalDateTime.now();
        this.user = user;
        this.post = post;
    }
}
