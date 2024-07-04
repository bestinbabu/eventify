package com.eventify.eventify.mapper.user;

import com.eventify.eventify.dto.user.UserPrivilegeDto;
import com.eventify.eventify.entity.user.UserPrivilege;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserPrivilegeMapper {


    UserPrivilegeDto userPrivilegeToUserPrivilegeDto(UserPrivilege userPrivilege);

//    @Mapping(target = "roles", ignore = true)
//    UserPrivilege userPrivilegeDtoToUserPrivilege(UserPrivilegeDto userPrivilegeDto);

    List<UserPrivilegeDto> userPrivilegesToUserPrivilegeDtos(List<UserPrivilege> userPrivileges);
}