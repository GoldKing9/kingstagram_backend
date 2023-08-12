package project.kingstagram.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
@ToString
@Getter
@Setter
@NoArgsConstructor
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
