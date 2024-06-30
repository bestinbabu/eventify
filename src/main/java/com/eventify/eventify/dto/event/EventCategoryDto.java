package com.eventify.eventify.dto.event;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class EventCategoryDto {


    @NotEmpty(message = "Category name cannot be empty")
    private String name;
}

