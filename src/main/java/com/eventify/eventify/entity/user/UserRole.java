package com.eventify.eventify.entity.user;

import com.eventify.eventify.entity.BaseEntity;
import com.eventify.eventify.entity.event.Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRole extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    @ManyToMany(mappedBy = "eventManagers")
    private Set<Event> events = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_privileges",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id")
    )
    private Set<UserPrivilege> privileges = new HashSet<>();

    public UserRole(String name) {
        this.name = name;
    }
}
