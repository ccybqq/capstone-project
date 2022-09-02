package pers.idc.capstone.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "user_auth_details")
public class UserAuth implements UserDetails {
    @Id
    @SequenceGenerator(name = "user_auth_sequence", allocationSize = 1)
    @GeneratedValue(generator = "user_auth_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email", referencedColumnName = "email")
    private UserEntity userEntity;
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "granted_authority")
    @CollectionTable(name = "user_auth_table_granted_authorities", joinColumns = @JoinColumn(name = "owner_id"))
    private Set<GrantedAuthority> authorities;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    @Override
    public String getUsername() {
        return userEntity.getEmail();
    }
}
