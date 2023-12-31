package com.gapple.weeingback.domain.diary.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;


@Table
@Entity
@Builder
@Getter
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
    private Long studentGrade;

    @Column
    private Long studentClass;
}
