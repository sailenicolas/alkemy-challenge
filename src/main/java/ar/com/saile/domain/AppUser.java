package ar.com.saile.domain;

import ar.com.saile.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
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
import java.time.LocalDateTime;
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

    @JsonView({Views.RegisterUserForm.class, Views.LoginUserForm.class, Views.RegisterResult.class})
    @NotBlank
    @NotNull
    private String username;

    @JsonView({Views.RegisterResult.class})
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    @NotBlank
    @NotNull
    @JsonView({Views.LoginUserForm.class, Views.RegisterUserForm.class})
    private String password;

    private Boolean isEnabled = false;

    private Boolean isAccountNonExpired = false;

    private Boolean isAccountNonLocked = false;

    private Boolean isCredentialsNonExpired = false;

    public AppUser(String username, String password, Boolean isEnabled, Boolean isAccountNonExpired, Boolean isAccountNonLocked, Boolean isCredentialsNonExpired) {

        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
    }

    public void addRole(AppRole grantedAuthority) {

        this.getRoleCollection().add( grantedAuthority );
    }

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

    @Override
    public String toString() {

        return "AppUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", isEnabled=" + isEnabled +
                ", isAccountNonExpired=" + isAccountNonExpired +
                ", isAccountNonLocked=" + isAccountNonLocked +
                ", isCredentialsNonExpired=" + isCredentialsNonExpired +
                '}';
    }
}
