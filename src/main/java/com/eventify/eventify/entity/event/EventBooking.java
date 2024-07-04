package com.eventify.eventify.entity.event;

import com.eventify.eventify.entity.user.UserProfile;
import com.eventify.eventify.enums.BookingStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "event_bookings")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor // Lombok annotations
public class EventBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne //multiple booking for one user
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    @NotNull
    @ManyToOne //multiple booking for one event
    @JoinColumn(name = "event_id")
    private Event event;

    @NotNull
    @Column(nullable = false)
    private Integer numberOfTickets;

    @NotNull
    @Column(nullable = false)
    private Double totalPrice;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @Builder
    public EventBooking(UserProfile userProfile, Event event, Integer numberOfTickets, Double totalPrice, BookingStatus status) {
        this.userProfile = userProfile;
        this.event = event;
        this.numberOfTickets = numberOfTickets;
        this.totalPrice = totalPrice;
        this.status = status;
    }

}
