
package com.eventify.eventify.entity.user;

import com.eventify.eventify.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_privileges")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPrivilege extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Set<UserRole> roles = new HashSet<>();

    public UserPrivilege(String name) {
        this.name = name;
    }
}