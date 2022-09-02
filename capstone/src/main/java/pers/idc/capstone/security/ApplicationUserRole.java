package pers.idc.capstone.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static pers.idc.capstone.security.ApplicationUserPermission.USER_READ;
import static pers.idc.capstone.security.ApplicationUserPermission.USER_WRITE;

public enum ApplicationUserRole {
    BASIC(Sets.newHashSet(USER_READ)),
    ADMIN(Sets.newHashSet(USER_READ, USER_WRITE));

    private Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return this.permissions;
    }

    public Set<GrantedAuthority> getGrantedAuthorities() {
        Set<GrantedAuthority> permissions = this.permissions
                .stream()
                .map(p -> new SimpleGrantedAuthority(p.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
