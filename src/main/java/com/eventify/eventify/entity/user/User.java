package com.eventify.eventify.entity.user;

import com.eventify.eventify.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles_mappings",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "user_roles_id")
    )
    private Set<UserRole> roles = new HashSet<>();


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)  // Owning side
    @JoinColumn(name = "user_profile_id", unique = true) // MappedBy points to the owning side (UserProfile)
    private UserProfile userProfile;

    @Column(nullable = false)
    private boolean enabled = true;

    @Column(nullable = false)
    private boolean accountNonExpired = true;

    @Column(nullable = false)
    private boolean accountNonLocked = true;

    @Column(nullable = false)
    private boolean credentialsNonExpired = true;


    public List<GrantedAuthority> getGrantedAuthorities() {
        return roles.stream()
                .flatMap(role -> {
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
                    authorities.addAll(role.getPrivileges().stream()
                            .map(privilege -> new SimpleGrantedAuthority("PRIVILEGE_" + privilege.getName()))
                            .toList());
                    return authorities.stream();
                })
                .collect(Collectors.toList());
    }


}