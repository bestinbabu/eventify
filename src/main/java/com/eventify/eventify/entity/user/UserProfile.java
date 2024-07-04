package com.eventify.eventify.entity.user;

import com.eventify.eventify.entity.BaseEntity;
import com.eventify.eventify.entity.event.Event;
import com.eventify.eventify.entity.event.EventBooking;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_profiles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String phoneNumber;

    @OneToMany(mappedBy = "organizer")
    private Set<Event> organizedEvents = new HashSet<>();

    @ManyToMany(mappedBy = "eventManagers")
    private Set<Event> managedEvents = new HashSet<>();

    @OneToOne(mappedBy = "userProfile")
    private User user;

    @OneToMany(mappedBy = "userProfile")
    private Set<EventBooking> bookings = new HashSet<>();


    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
}