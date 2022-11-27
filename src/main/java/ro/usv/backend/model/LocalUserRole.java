package ro.usv.backend.model;

import org.springframework.security.core.GrantedAuthority;

public enum LocalUserRole implements GrantedAuthority {
    ADMIN, MODERATOR;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
