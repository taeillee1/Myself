package com.myself.myself.entity;

import com.myself.myself.dto.UserFormDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String password;

    public static User createUser(UserFormDto userFormDto, PasswordEncoder passwordEncoder){
        User user = new User();
        user.setUsername(userFormDto.getUsername());
        String password = passwordEncoder.encode(userFormDto.getPassword());
        user.setPassword(password);
        return user;
    }

}
