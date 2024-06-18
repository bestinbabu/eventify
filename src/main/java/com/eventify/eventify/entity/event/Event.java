package com.eventify.eventify.entity.event;

import com.eventify.eventify.entity.BaseEntity;
import com.eventify.eventify.entity.user.UserProfile;
import com.eventify.eventify.entity.user.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor // Lombok annotations
public class Event extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "event_name", nullable = false)
    private String eventName;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "number_of_seats")
    private Integer numberOfSeats;

    @Column(name = "pricing_per_seat", nullable = false)
    private Double pricingPerSeat;

    @ManyToOne
    @JoinColumn(name = "organizer_id")  // Updated to reference user_profile table
    private UserProfile organizer;

    @ManyToMany
    @JoinTable(
            name = "event_managers", // Choose an appropriate name for the join table
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_role_id")
    )
    private Set<UserRole> eventManagers;


    @ManyToMany
    private Set<UserProfile> attendees;  // Many-to-many with User for registered attendees


    @ManyToMany // Many events can have many categories
    @JoinTable(name = "event_event_category", // Join table name
            joinColumns = @JoinColumn(name = "event_id"), // Foreign key for EventDetailsEntity
            inverseJoinColumns = @JoinColumn(name = "event_category_id")) // Foreign key for EventCategoryEntity
    private List<EventCategory> categories;
}
