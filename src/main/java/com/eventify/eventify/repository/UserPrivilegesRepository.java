package com.eventify.eventify.repository;

import com.eventify.eventify.entity.user.UserPrivileges;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPrivilegesRepository extends JpaRepository<UserPrivileges, Long> {
}
