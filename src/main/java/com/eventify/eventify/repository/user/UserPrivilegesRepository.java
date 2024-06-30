package com.eventify.eventify.repository.user;

import com.eventify.eventify.entity.user.UserPrivilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPrivilegesRepository extends JpaRepository<UserPrivilege, Long> {
}
