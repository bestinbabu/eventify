package com.eventify.eventify.entity.user;

import com.eventify.eventify.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "user_privileges")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserPrivileges extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<UserRole> roles;

    public UserPrivileges(String name) {
        this.name = name;
    }
}
