package com.eventify.eventify.entity.event;

import com.eventify.eventify.entity.BaseEntity;
import com.eventify.eventify.entity.user.UserProfile;
import com.eventify.eventify.entity.user.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    private Integer numberOfSeats;

    @Column(nullable = false)
    private Double pricingPerSeat;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private UserProfile organizer;

    @ManyToMany
    @JoinTable(
            name = "event_managers",
            joinColumns = @JoinColumn(name = "events_id"),
            inverseJoinColumns = @JoinColumn(name = "user_roles_id")
    )
    private Set<UserRole> eventManagers;

    @ManyToMany
    @JoinTable(
            name = "event_attendees",
            joinColumns = @JoinColumn(name = "events_id"),
            inverseJoinColumns = @JoinColumn(name = "user_profiles_id")
    )
    private Set<UserProfile> attendees;


    @ManyToMany
    @JoinTable(
            name = "event_categories_mappings",
            joinColumns = @JoinColumn(name = "events_id"),
            inverseJoinColumns = @JoinColumn(name = "event_categorys_id")
    )
    private Set<EventCategory> categories = new HashSet<>();
}