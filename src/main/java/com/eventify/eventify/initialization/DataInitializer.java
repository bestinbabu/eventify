package com.eventify.eventify.initialization;

import com.eventify.eventify.entity.user.UserPrivilege;
import com.eventify.eventify.entity.user.UserRole;
import com.eventify.eventify.repository.user.UserPrivilegesRepository;
import com.eventify.eventify.repository.user.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {


    private final UserRoleRepository userRoleRepository;
    private final UserPrivilegesRepository userPrivilegesRepository;

    @PostConstruct
    public void initializeData() {
        if (userRoleRepository.findByName("Attendee").isEmpty()  || userRoleRepository.findByName("Event Manager").isEmpty()) {
            // User roles and privileges don't exist, proceed with seeding

            // Attendee Role and Privileges
            UserPrivilege viewEventDetails = new UserPrivilege("View_Event_Details");
            UserPrivilege registerForEvent = new UserPrivilege("Register_For_Event");

            UserRole attendeeRole = new UserRole("Attendee");
            attendeeRole.getPrivileges().add(viewEventDetails);
            attendeeRole.getPrivileges().add(registerForEvent);

            // Save user privileges (optional depending on your usage)
            userPrivilegesRepository.save(viewEventDetails);
            userPrivilegesRepository.save(registerForEvent);

            // Save user roles (associations will be created automatically)
            userRoleRepository.save(attendeeRole);

            // Event Manager Role and Privileges
            UserPrivilege createEvent = new UserPrivilege("Create_Event");
            UserPrivilege editEvent = new UserPrivilege("Edit_Event");
            UserPrivilege manageAttendees = new UserPrivilege("Manage_Event_Attendees");

            UserRole eventManagerRole = new UserRole("Event Manager");
            eventManagerRole.getPrivileges().add(createEvent);
            eventManagerRole.getPrivileges().add(editEvent);
            eventManagerRole.getPrivileges().add(manageAttendees);

            // Save user privileges (optional depending on your usage)
            userPrivilegesRepository.save(createEvent);
            userPrivilegesRepository.save(editEvent);
            userPrivilegesRepository.save(manageAttendees);

            // Save user roles (associations will be created automatically)
            userRoleRepository.save(eventManagerRole);

            System.out.println("User Roles and Privileges successfully populated");
        } else {
            System.out.println("User Roles and Privileges already exist, skipping seeding");
        }
    }
}