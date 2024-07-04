package com.eventify.eventify.repository.event;

import com.eventify.eventify.entity.event.EventBooking;
import com.eventify.eventify.entity.user.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventBookingRepository extends JpaRepository<EventBooking, Long> {

    Optional<EventBooking> findByEventIdAndUserProfile(Long eventId, UserProfile userProfile);

    Page<EventBooking> findAllByUserProfile(UserProfile userProfile, Pageable pageable);
}
