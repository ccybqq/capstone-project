package pers.idc.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pers.idc.capstone.exception.IdNotNullException;
import pers.idc.capstone.exception.UniqueConstraintViolationException;
import pers.idc.capstone.model.UserAuth;
import pers.idc.capstone.service.UserAuthService;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(
        origins = "http://localhost:4200",
        exposedHeaders = "Message"
)
public class UserAuthController {
    private final UserAuthService userAuthService;

    @Autowired
    public UserAuthController(UserAuthService userAuthService, PasswordEncoder passwordEncoder) {
        this.userAuthService = userAuthService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserAuth> findByEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(userAuthService.findByEmail(email));
    }

    @GetMapping("/isAdmin")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<Boolean> isAdmin(@RequestParam("email") String email) {
        return ResponseEntity.ok(userAuthService.isAdmin(email));
    }

    @PostMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<UserAuth> register(@RequestBody UserAuth userAuth) {
        try {
            return ResponseEntity.ok(userAuthService.register(userAuth));
        } catch (IdNotNullException | UniqueConstraintViolationException e) {
            return ResponseEntity.badRequest()
                    .header("Message", e.getMessage())
                    .build();
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<UserAuth> makeAdmin(@PathVariable("id") String id) {
        try {
            userAuthService.makeAdmin(Long.parseLong(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
