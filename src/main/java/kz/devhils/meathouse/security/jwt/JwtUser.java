package kz.devhils.meathouse.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.devhils.meathouse.model.UserProfile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public class JwtUser implements UserDetails {

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final UserProfile profile;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Date createdAt;

    public JwtUser(Long id, String firstName, String lastName, String email, String password, UserProfile profile, Collection<? extends GrantedAuthority> authorities, Date createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.profile = profile;
        this.authorities = authorities;
        this.createdAt = createdAt;
    }

    @JsonIgnore
    public Long getId(){
        return id;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return email;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
