package project.kingstagram.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class Users {

    @Id @GeneratedValue
    private Long userId;
    private String userName;
    private String userNickname;
    private String userPw;
    private String userEmail;
    private String userDescription;
}
