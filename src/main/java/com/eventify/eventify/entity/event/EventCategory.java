package com.eventify.eventify.entity.event;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor // Lombok annotations
public class EventCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "categories") // Mapped by refers to the field in EventDetailsEntity
    private List<Event> events;


}

