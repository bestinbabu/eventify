package com.eventify.eventify.initialization;

import com.eventify.eventify.entity.user.UserPrivileges;
import com.eventify.eventify.entity.user.UserRole;
import com.eventify.eventify.repository.UserPrivilegesRepository;
import com.eventify.eventify.repository.UserRepository;
import com.eventify.eventify.repository.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserPrivilegesRepository userPrivilegesRepository;
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void initializeData() {
        if (userRoleRepository.findByName("Attendee") == null || userRoleRepository.findByName("Event Manager") == null) {
            // User roles and privileges don't exist, proceed with seeding

            // Attendee Role and Privileges
            UserPrivileges viewEventDetails = new UserPrivileges("View_Event_Details");
            UserPrivileges registerForEvent = new UserPrivileges("Register_For_Event");

            UserRole attendeeRole = new UserRole("Attendee");
            attendeeRole.getPrivileges().add(viewEventDetails);
            attendeeRole.getPrivileges().add(registerForEvent);

            // Save user privileges (optional depending on your usage)
            userPrivilegesRepository.save(viewEventDetails);
            userPrivilegesRepository.save(registerForEvent);

            // Save user roles (associations will be created automatically)
            userRoleRepository.save(attendeeRole);

            // Event Manager Role and Privileges
            UserPrivileges createEvent = new UserPrivileges("Create_Event");
            UserPrivileges editEvent = new UserPrivileges("Edit_Event");
            UserPrivileges manageAttendees = new UserPrivileges("Manage_Event_Attendees");

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