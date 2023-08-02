package project.kingstagram.domain;

import javax.persistence.*;

@Entity
@Table(name = "LIKES")
public class Like {
    @Id
    @GeneratedValue
    private Long likeId;
    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;
    @ManyToOne
    @JoinColumn(name="userId")
    private Users user;
}
