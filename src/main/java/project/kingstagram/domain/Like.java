
package project.kingstagram.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like implements Serializable {

    @Embeddable
    public static class LikeId implements Serializable {
        private Long userId;
        private Long postId;

    }
    @EmbeddedId
    private LikeId id;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private Users user;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("postId")
    @JoinColumn(name = "post_id")
    private Post post;

    // 생성자, equals, hashCode 등을 구현해야 합니다.
}

