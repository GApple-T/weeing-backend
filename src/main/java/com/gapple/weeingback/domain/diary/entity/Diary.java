package com.gapple.weeingback.domain.diary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Diary {
    @Id
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    private UUID id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private Long accessRange;
}
