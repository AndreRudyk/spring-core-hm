package com.spring.hm.springcorehm.mapper;

import com.spring.hm.springcorehm.dto.EventDto;
import com.spring.hm.springcorehm.dto.UserDto;
import com.spring.hm.springcorehm.model.impl.EventImpl;
import org.mapstruct.Mapper;

/**
 * Converter class for Event
 */
@Mapper(componentModel = "spring")
public interface EventMapper {

    /**
     * Converts business model to dto.
     * @param event business model.
     * @return {@link EventDto} dto.
     */
    EventDto covertBusinessModelToDto(final EventImpl event);

    /**
     * Converts dto tp business model.
     * @param eventDto dto.
     * @return {@link EventImpl} business model.
     */
    EventImpl convertDtoToBusinessModel(final UserDto eventDto);
}
