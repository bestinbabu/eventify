package com.eventify.eventify.mapper.user;

import com.eventify.eventify.dto.user.UserProfileDto;
import com.eventify.eventify.dto.user.baseDto.BaseUserProfileDto;
import com.eventify.eventify.entity.user.User;
import com.eventify.eventify.entity.user.UserProfile;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-04T13:36:57+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class UserProfileMapperImpl implements UserProfileMapper {

    @Override
    public UserProfileDto userProfileToUserProfileDto(UserProfile userProfile) {
        if ( userProfile == null ) {
            return null;
        }

        UserProfileDto.UserProfileDtoBuilder<?, ?> userProfileDto = UserProfileDto.builder();

        userProfileDto.fullName( userProfile.getFullName() );
        userProfileDto.phoneNumber( userProfile.getPhoneNumber() );
        userProfileDto.firstName( userProfile.getFirstName() );
        userProfileDto.lastName( userProfile.getLastName() );

        return userProfileDto.build();
    }

    @Override
    public UserProfile userProfileDtoToUserProfile(UserProfileDto userProfileDto) {
        if ( userProfileDto == null ) {
            return null;
        }

        UserProfile userProfile = new UserProfile();

        userProfile.setFirstName( userProfileDto.getFirstName() );
        userProfile.setLastName( userProfileDto.getLastName() );
        userProfile.setPhoneNumber( userProfileDto.getPhoneNumber() );

        return userProfile;
    }

    @Override
    public BaseUserProfileDto userProfileToBaseUserProfileDto(UserProfile userProfile) {
        if ( userProfile == null ) {
            return null;
        }

        BaseUserProfileDto.BaseUserProfileDtoBuilder<?, ?> baseUserProfileDto = BaseUserProfileDto.builder();

        baseUserProfileDto.email( userProfileUserEmail( userProfile ) );
        baseUserProfileDto.phoneNumber( userProfile.getPhoneNumber() );

        baseUserProfileDto.fullName( userProfile.getFullName() );

        return baseUserProfileDto.build();
    }

    private String userProfileUserEmail(UserProfile userProfile) {
        if ( userProfile == null ) {
            return null;
        }
        User user = userProfile.getUser();
        if ( user == null ) {
            return null;
        }
        String email = user.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }
}
