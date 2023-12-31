package com.gapple.weeingback.domain.auth.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table
@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private UUID key;

    @Column(columnDefinition = "VARCHAR(500)")
    private String value;

    public RefreshToken(UUID key, String token) {
        this.key = key;
        this.value = token;
    }
}
