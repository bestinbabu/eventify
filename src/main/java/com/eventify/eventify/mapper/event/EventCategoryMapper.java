package com.eventify.eventify.mapper.event;

import com.eventify.eventify.dto.event.EventCategoryDto;
import com.eventify.eventify.dto.user.CategoryDto;
import com.eventify.eventify.entity.event.EventCategory;
import org.mapstruct.Mapper;
import org.mapstruct.*;


import java.util.List;

@Mapper(componentModel = "spring")
public interface EventCategoryMapper {


    EventCategoryDto eventCategoryToEventCategoryDto(EventCategory eventCategory);

    @Mapping(target = "events", ignore = true)
    EventCategory eventCategoryDtoToEventCategory(EventCategoryDto eventCategoryDto);

    List<EventCategoryDto> eventCategoriesToEventCategoryDtos(List<EventCategory> eventCategories);

    CategoryDto categoryToCategoryDto(EventCategory category);
}
