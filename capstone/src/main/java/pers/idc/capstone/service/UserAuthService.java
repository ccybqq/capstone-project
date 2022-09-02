package pers.idc.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pers.idc.capstone.exception.UniqueConstraintViolationException;
import pers.idc.capstone.model.UserAuth;
import pers.idc.capstone.repo.UserAuthRepository;
import pers.idc.capstone.repo.UserRepository;

import java.util.NoSuchElementException;

import static pers.idc.capstone.security.ApplicationUserRole.ADMIN;
import static pers.idc.capstone.security.ApplicationUserRole.BASIC;

@Service
public class UserAuthService implements UserDetailsService {
    private final UserAuthRepository userAuthRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserAuthService(
            UserAuthRepository userAuthRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userAuthRepository = userAuthRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAuthRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User does not exist."));
    }

    public UserAuth getByEmail(String email) {
        return userAuthRepository.findByEmail(email)
                .orElseThrow(NoSuchElementException::new);
    }

    public UserAuth register(UserAuth userAuth) {
        // Email must be unique.
        String email = userAuth.getUserEntity().getEmail();
        if (userRepository.findByEmail(email).isPresent()) throw new UniqueConstraintViolationException("Email", email);

        userAuth.setAccountNonLocked(true);
        userAuth.setAccountNonExpired(true);
        userAuth.setCredentialsNonExpired(true);
        userAuth.setEnabled(true);
        userAuth.setPassword(passwordEncoder.encode(userAuth.getPassword()));
        userAuth.setAuthorities(BASIC.getGrantedAuthorities());
        return userAuthRepository.save(userAuth);
    }

    public void makeAdmin(long id) {
        UserAuth user = userAuthRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        user.setAuthorities(ADMIN.getGrantedAuthorities());
        userAuthRepository.save(user);
    }
}
