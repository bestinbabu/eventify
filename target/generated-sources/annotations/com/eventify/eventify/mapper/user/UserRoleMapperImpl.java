package com.eventify.eventify.mapper.user;

import com.eventify.eventify.dto.user.UserPrivilegeDto;
import com.eventify.eventify.dto.user.UserRoleDto;
import com.eventify.eventify.entity.user.UserPrivilege;
import com.eventify.eventify.entity.user.UserRole;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-04T13:36:56+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class UserRoleMapperImpl implements UserRoleMapper {

    @Autowired
    private UserPrivilegeMapper userPrivilegeMapper;

    @Override
    public UserRoleDto userRoleToUserRoleDto(UserRole userRole) {
        if ( userRole == null ) {
            return null;
        }

        UserRoleDto.UserRoleDtoBuilder userRoleDto = UserRoleDto.builder();

        userRoleDto.name( userRole.getName() );
        userRoleDto.privileges( userPrivilegeSetToUserPrivilegeDtoSet( userRole.getPrivileges() ) );

        return userRoleDto.build();
    }

    @Override
    public List<UserRoleDto> userRolesToUserRoleDtos(List<UserRole> userRoles) {
        if ( userRoles == null ) {
            return null;
        }

        List<UserRoleDto> list = new ArrayList<UserRoleDto>( userRoles.size() );
        for ( UserRole userRole : userRoles ) {
            list.add( userRoleToUserRoleDto( userRole ) );
        }

        return list;
    }

    protected Set<UserPrivilegeDto> userPrivilegeSetToUserPrivilegeDtoSet(Set<UserPrivilege> set) {
        if ( set == null ) {
            return null;
        }

        Set<UserPrivilegeDto> set1 = new LinkedHashSet<UserPrivilegeDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( UserPrivilege userPrivilege : set ) {
            set1.add( userPrivilegeMapper.userPrivilegeToUserPrivilegeDto( userPrivilege ) );
        }

        return set1;
    }
}
