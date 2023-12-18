package com.gapple.weeingback.domain.schedule.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;
import java.util.UUID;

@Table
@Entity
public class Schedule {
    @Id
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    private UUID id;

    @Column
    private Date date;

    @Column
    private String reason;
}
