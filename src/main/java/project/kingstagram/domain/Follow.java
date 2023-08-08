package project.kingstagram.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(
    name="Follow",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "followFromTo",
            columnNames = {"fromUserId","toUserId"}
        )
    }
)
@Getter
@NoArgsConstructor
public class Follow {
    //유저와 다대다 자기연관 관계이다 follow클래스는 유저와 유저 사이 연결
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;
    @ManyToOne
    @JoinColumn(name = "fromUserId")
    private Users fromUser;
    @ManyToOne
    @JoinColumn(name = "toUserId")
    private Users toUser;

    public Follow(Users toUser, Users fromUser) {
        this.toUser = toUser;
        this.fromUser = fromUser;
    }
}
