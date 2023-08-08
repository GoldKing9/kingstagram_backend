package project.kingstagram.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(
    name="LIKES",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "postIdAnduserId",
            columnNames = {"postId","userId"}
        )
    }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post post;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    private Users user;
    @Builder
    public Like(Post post, Users user) {
        this.post = post;
        this.user = user;
    }
}
