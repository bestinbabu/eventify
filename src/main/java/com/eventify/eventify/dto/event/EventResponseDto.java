package com.eventify.eventify.dto.event;

import com.eventify.eventify.dto.event.EventCategoryDto;
import com.eventify.eventify.dto.user.CategoryDto;
import com.eventify.eventify.dto.user.UserProfileDto;
import com.eventify.eventify.dto.user.baseDto.BaseUserProfileDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class EventResponseDto {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer numberOfSeats;
    private Double pricingPerSeat;
    private LocalDateTime registrationDeadline;
    private Set<CategoryDto> categories;
    private BaseUserProfileDto organizer;
    private Set<BaseUserProfileDto> eventManagers;
}