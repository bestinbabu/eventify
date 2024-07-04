package com.eventify.eventify.mapper.user;

import com.eventify.eventify.dto.user.UserProfileDto;
import com.eventify.eventify.dto.user.baseDto.BaseUserProfileDto;
import com.eventify.eventify.entity.user.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

    UserProfileDto userProfileToUserProfileDto(UserProfile userProfile);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    UserProfile userProfileDtoToUserProfile(UserProfileDto userProfileDto);

    @Named("userProfileToBaseUserProfileDto")
    @Mapping(target = "fullName", expression = "java(userProfile.getFullName())")
    @Mapping(target = "email", source = "user.email")
    BaseUserProfileDto userProfileToBaseUserProfileDto(UserProfile userProfile);



}
