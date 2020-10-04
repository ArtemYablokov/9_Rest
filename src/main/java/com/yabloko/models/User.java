package com.yabloko.models;

import com.yabloko.forms.UserForm;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "apple_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String login;
    private String password;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Token> tokens;


    @Enumerated(value = EnumType.STRING)
    Role role;
    @Enumerated(value = EnumType.STRING)
    State state;

    public static User from(UserForm userForm) {
        return User.builder()
                .name(userForm.getName())
                .login(userForm.getLogin())
                .build();
    }
}