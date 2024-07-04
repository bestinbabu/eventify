package com.eventify.eventify.repository.user;

import com.eventify.eventify.entity.event.EventCategory;
import com.eventify.eventify.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional <User> findByEmail(String email);
    Set<User> findAllByEmailIn(Set<String> userEmails);
}
