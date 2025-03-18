package myy803.socialbookstore.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import myy803.socialbookstore.model.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Entity class representing a User.
 * <p>
 * This class is annotated with JPA annotations to map it to a database table.
 * It includes fields for the user's username, password, and role.
 * </p>
 * <p>
 * This class implements the {@link org.springframework.security.core.userdetails.UserDetails} interface
 * to integrate with Spring Security for authentication and authorization.
 * </p>
 *
 * @see Role
 * @see UserDetails
 */
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @Column(name = "username", unique = true)
    @NotBlank(message = "User's name cannot be empty.")
    @Size(min = 5, max = 250)
    private String username;

    @NotBlank(message = "User's password cannot be empty.")
    @Size(min = 5, max = 250)
    @Column(name = "password")
    private String password;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
