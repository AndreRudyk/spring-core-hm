package com.spring.hm.springcorehm.mapper;

import com.spring.hm.springcorehm.dto.UserDto;
import com.spring.hm.springcorehm.model.impl.UserImpl;
import org.mapstruct.Mapper;

/**
 * Converter class for User
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Converts business model to dto.
     * @param user business model.
     * @return {@link UserDto} dto.
     */
    UserDto covertBusinessModelToDto(final UserImpl user);

    /**
     * Converts dto tp business model.
     * @param userDto dto.
     * @return {@link UserImpl} business model.
     */
    UserImpl convertDtoToBusinessModel(final UserDto userDto);
}
