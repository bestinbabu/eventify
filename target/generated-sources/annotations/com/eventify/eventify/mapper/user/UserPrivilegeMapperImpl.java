package com.eventify.eventify.mapper.user;

import com.eventify.eventify.dto.user.UserPrivilegeDto;
import com.eventify.eventify.entity.user.UserPrivilege;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-03T01:09:16+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class UserPrivilegeMapperImpl implements UserPrivilegeMapper {

    @Override
    public UserPrivilegeDto userPrivilegeToUserPrivilegeDto(UserPrivilege userPrivilege) {
        if ( userPrivilege == null ) {
            return null;
        }

        UserPrivilegeDto.UserPrivilegeDtoBuilder userPrivilegeDto = UserPrivilegeDto.builder();

        userPrivilegeDto.id( userPrivilege.getId() );
        userPrivilegeDto.name( userPrivilege.getName() );

        return userPrivilegeDto.build();
    }

    @Override
    public List<UserPrivilegeDto> userPrivilegesToUserPrivilegeDtos(List<UserPrivilege> userPrivileges) {
        if ( userPrivileges == null ) {
            return null;
        }

        List<UserPrivilegeDto> list = new ArrayList<UserPrivilegeDto>( userPrivileges.size() );
        for ( UserPrivilege userPrivilege : userPrivileges ) {
            list.add( userPrivilegeToUserPrivilegeDto( userPrivilege ) );
        }

        return list;
    }
}
