package com.eventify.eventify.dto.event;

import com.eventify.eventify.entity.event.Event;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventCategoryDto {

    private String name;
    private Set<Event> events = new HashSet<>();

}
