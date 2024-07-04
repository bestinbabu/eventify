package com.eventify.eventify.initialization;

import com.eventify.eventify.entity.event.EventCategory;
import com.eventify.eventify.entity.user.UserPrivilege;
import com.eventify.eventify.entity.user.UserRole;
import com.eventify.eventify.repository.event.EventCategoryRepository;
import com.eventify.eventify.repository.user.UserPrivilegesRepository;
import com.eventify.eventify.repository.user.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer {


    private final UserRoleRepository userRoleRepository;
    private final UserPrivilegesRepository userPrivilegesRepository;
    private final EventCategoryRepository eventCategoryRepository;

    @PostConstruct
    public void initializeData() {
        initializeUserRolesAndPrivileges();
        initializeEventCategories();
    }

    private void initializeUserRolesAndPrivileges() {
        if (userRoleRepository.findByName("Attendee").isEmpty() || userRoleRepository.findByName("Event Manager").isEmpty()) {
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

    private void initializeEventCategories() {
        if (eventCategoryRepository.count() == 0) {
            List<String> categories = Arrays.asList(
                    "Technology", "Conference", "Networking", "Workshop", "Seminar",
                    "Webinar", "Trade Show", "Product Launch", "Fundraiser", "Social Gathering",
                    "Cultural Event", "Music Concert", "Art Exhibition", "Sports Event", "Business Meetup"
            );

            for (String categoryName : categories) {
                EventCategory category = new EventCategory();
                category.setName(categoryName);
                eventCategoryRepository.save(category);
            }

            System.out.println("Event Categories successfully populated");
        } else {
            System.out.println("Event Categories already exist, skipping seeding");
        }
    }

}