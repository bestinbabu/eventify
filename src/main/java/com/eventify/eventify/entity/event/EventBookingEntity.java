//package com.eventify.eventify.entity.event;
//
//import com.eventify.eventify.entity.user.UserDetails;
//import jakarta.persistence.*;
//import lombok.*;
//import org.springframework.data.annotation.CreatedDate;
//
//import java.time.LocalDateTime;
//
//
//@Getter
//@Setter
//@ToString
//@AllArgsConstructor
//@NoArgsConstructor // Lombok annotations
//public class EventBookingEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne //multiple booking for one user
//    @JoinColumn(name = "user_details_id")
//    private UserDetails user;
//
//    @ManyToOne //multiple booking for one event
//    @JoinColumn(name = "event_details_id")
//    private Event event;
//
//    @Column(nullable = false)
//    private Integer numberOfTickets;
//
//    @Column(nullable = false)
//    private Double totalPrice;
//
//    @Column(name = "created_at", nullable = false, updatable = false)
//    @CreatedDate
//    private LocalDateTime createdAt;
//
//}
