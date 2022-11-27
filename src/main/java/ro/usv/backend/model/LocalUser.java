package ro.usv.backend.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class LocalUser implements UserDetails {

    private @Id
    @GeneratedValue Long id;

    private String username;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private LocalUserRole localUserRole;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(localUserRole);
    }

    @Override
    public String getUsername() {
        return email;
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
}
