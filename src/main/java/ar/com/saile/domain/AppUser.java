package ar.com.saile.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser implements Serializable, UserDetails {

    static final String ID = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank
    @NotNull
    private String username;

    @Column(nullable = false)
    @NotBlank
    @NotNull
    private String password;

    private Boolean isEnabled;

    private Boolean isAccountNonExpired;

    private Boolean isAccountNonLocked;

    private Boolean isCredentialsNonExpired;


    @ManyToMany(cascade = CascadeType.ALL)
    private Collection<AppRole> roleCollection = new ArrayList<>();

    public AppUser(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoleCollection();
    }

    @Override
    public boolean isAccountNonExpired() {

        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {

        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {

        return this.isEnabled;
    }
}
