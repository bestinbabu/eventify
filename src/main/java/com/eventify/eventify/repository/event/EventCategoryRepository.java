package com.eventify.eventify.repository.event;

import com.eventify.eventify.entity.event.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface EventCategoryRepository extends JpaRepository<EventCategory, Long> {
    Set<EventCategory> findAllByNameIn(Set<String> categoryNames);
}
