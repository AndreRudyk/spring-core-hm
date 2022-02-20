package com.spring.hm.springcorehm.mapper;

import com.spring.hm.springcorehm.dto.TicketDto;
import com.spring.hm.springcorehm.model.impl.TicketImpl;
import org.mapstruct.Mapper;

/**
 * Converter class for Ticket
 */
@Mapper(componentModel = "spring")
public interface TicketMapper {

    /**
     * Converts business model to dto.
     * @param ticket business model.
     * @return {@link TicketDto} dto.
     */
    TicketDto covertBusinessModelToDto(final TicketImpl ticket);

    /**
     * Converts dto tp business model.
     * @param ticketDto dto.
     * @return {@link TicketImpl} business model.
     */
    TicketImpl convertDtoToBusinessModel(final TicketDto ticketDto);
}
