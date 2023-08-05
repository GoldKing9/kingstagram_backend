package project.kingstagram.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class Users {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String userNickname;
    private String userPw;
    private String userEmail;

}
