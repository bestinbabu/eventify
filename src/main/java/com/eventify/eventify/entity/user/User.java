package com.eventify.eventify.entity.user;

import com.eventify.eventify.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "user_role_id")
    )
    private List<UserRole> roles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_profile_id", unique = true)
    private UserProfile userProfile;


    public List<GrantedAuthority> getGrantedAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (UserRole role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            authorities.addAll(role.getPrivileges().stream()
                    .map(privilege -> new SimpleGrantedAuthority("PRIVILEGE_" + privilege.getName()))
                    .toList());
        }
        return authorities;
    }
}

