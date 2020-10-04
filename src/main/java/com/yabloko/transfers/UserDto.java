package com.yabloko.transfers;

import com.yabloko.models.User;
import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = {"id", "name", "password", "role", "state"})

public class UserDto {

    private String name;
    private String login;

    public static UserDto from(User user) {
        return UserDto.builder()
                .name(user.getName())
                .login(user.getLogin())
                .build();
    }
}