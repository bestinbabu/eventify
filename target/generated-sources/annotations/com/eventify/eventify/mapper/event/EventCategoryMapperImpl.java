package com.eventify.eventify.mapper.event;

import com.eventify.eventify.dto.event.EventCategoryDto;
import com.eventify.eventify.dto.user.CategoryDto;
import com.eventify.eventify.entity.event.Event;
import com.eventify.eventify.entity.event.EventCategory;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-04T13:36:57+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class EventCategoryMapperImpl implements EventCategoryMapper {

    @Override
    public EventCategoryDto eventCategoryToEventCategoryDto(EventCategory eventCategory) {
        if ( eventCategory == null ) {
            return null;
        }

        EventCategoryDto.EventCategoryDtoBuilder eventCategoryDto = EventCategoryDto.builder();

        eventCategoryDto.name( eventCategory.getName() );
        Set<Event> set = eventCategory.getEvents();
        if ( set != null ) {
            eventCategoryDto.events( new LinkedHashSet<Event>( set ) );
        }

        return eventCategoryDto.build();
    }

    @Override
    public EventCategory eventCategoryDtoToEventCategory(EventCategoryDto eventCategoryDto) {
        if ( eventCategoryDto == null ) {
            return null;
        }

        EventCategory eventCategory = new EventCategory();

        eventCategory.setName( eventCategoryDto.getName() );

        return eventCategory;
    }

    @Override
    public List<EventCategoryDto> eventCategoriesToEventCategoryDtos(List<EventCategory> eventCategories) {
        if ( eventCategories == null ) {
            return null;
        }

        List<EventCategoryDto> list = new ArrayList<EventCategoryDto>( eventCategories.size() );
        for ( EventCategory eventCategory : eventCategories ) {
            list.add( eventCategoryToEventCategoryDto( eventCategory ) );
        }

        return list;
    }

    @Override
    public CategoryDto categoryToCategoryDto(EventCategory category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto.CategoryDtoBuilder<?, ?> categoryDto = CategoryDto.builder();

        categoryDto.name( category.getName() );

        return categoryDto.build();
    }
}
