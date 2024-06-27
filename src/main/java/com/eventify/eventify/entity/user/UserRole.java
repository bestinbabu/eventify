package com.eventify.eventify.entity.user;

import com.eventify.eventify.entity.BaseEntity;
import com.eventify.eventify.entity.event.Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "user_role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRole extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;



    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany(mappedBy = "eventManagers")
    private Set<Event> events;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role_privileges",
            joinColumns = @JoinColumn(
                    name = "user_role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "user_privilege_id", referencedColumnName = "id"))
    private Collection<UserPrivileges> privileges = new ArrayList<>();

    public UserRole(String name) {
        this.name = name;
    }


}
