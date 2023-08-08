package project.kingstagram.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {

    @Id @GeneratedValue
    private Long userId;
    private String userName;
    private String userNickname;
    private String userPw;
    private String userEmail;
    private String userDescription;

    @Builder
    public Users(String userName, String userNickname, String userPw, String userEmail, String userDescription) {
        this.userName = userName;
        this.userNickname = userNickname;
        this.userPw = userPw;
        this.userEmail = userEmail;
        this.userDescription = userDescription;
    }
}
