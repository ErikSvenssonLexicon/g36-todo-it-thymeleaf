package se.lexicon.g36todoit.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import se.lexicon.g36todoit.model.entity.AppUser;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MyUserDetails implements UserDetails {

    private final AppUser appUser;
    private final Set<GrantedAuthority> authorities;

    public MyUserDetails(AppUser appUser) {
        this.appUser = appUser;

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(appUser.getRole().getRole().name()));
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return appUser.getPassword();
    }

    @Override
    public String getUsername() {
        return appUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return appUser.isActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return appUser.isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return appUser.isActive();
    }

    @Override
    public boolean isEnabled() {
        return appUser.isActive();
    }
}
