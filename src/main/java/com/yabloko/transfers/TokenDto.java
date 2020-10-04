package com.yabloko.transfers;


import com.yabloko.models.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TokenDto {
    private String value;

    public static TokenDto from(Token token){
        return TokenDto.builder()
                .value(token.getValue())
                .build();
    }
}