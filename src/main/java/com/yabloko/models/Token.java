package com.yabloko.models;

import lombok.*;

import javax.persistence.*;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

@ToString(exclude = "owner")
@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User owner;
}
