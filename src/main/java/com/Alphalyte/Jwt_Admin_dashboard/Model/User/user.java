package com.Alphalyte.Jwt_Admin_dashboard.Model.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_master")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class user implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int usercode;
    private String groupname;
    private String username;
    private String password;
    private LocalDateTime createdat;
    private String createdBY;
    private String localaddress;
    private Boolean isactive;
    private String email;
    private long phoneNumber;

    public user(String groupname, String username, String password,
                LocalDateTime createdat, String createdBY,
                String localaddress, Boolean isactive, String email, long phoneNumber) {
        this.groupname = groupname;
        this.username = username;
        this.password = password;
        this.createdat = createdat;
        this.createdBY = createdBY;
        this.localaddress = localaddress;
        this.isactive = isactive;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
        return this.isactive;
    }
}
