package com.eventify.eventify.mapper.user;

import com.eventify.eventify.dto.user.UserRoleDto;
import com.eventify.eventify.entity.user.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.*;


import java.util.List;

@Mapper(componentModel = "spring", uses = {UserPrivilegeMapper.class})
public interface UserRoleMapper {

    UserRoleDto userRoleToUserRoleDto(UserRole userRole);


    List<UserRoleDto> userRolesToUserRoleDtos(List<UserRole> userRoles);
}








//    @Mapping(target = "users", ignore = true)
//    @Mapping(target = "events", ignore = true)
//    UserRole userRoleDtoToUserRole(UserRoleDto userRoleDto);