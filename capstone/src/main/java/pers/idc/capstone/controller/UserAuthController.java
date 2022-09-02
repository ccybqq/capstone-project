package pers.idc.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<UserAuth> getById(@RequestParam("email") String email) {
        return ResponseEntity.ok(userAuthService.getByEmail(email));
    }

    @PostMapping
    public ResponseEntity<UserAuth> register(@RequestBody UserAuth userAuth) {
        try {
            return ResponseEntity.ok(userAuthService.register(userAuth));
        } catch (UniqueConstraintViolationException e) {
            return ResponseEntity.badRequest()
                    .header("Message", e.getMessage())
                    .build();
        }
    }

    // TODO: Pre-authorise ROLE_ADMIN.
    @PutMapping("/admin")
    public ResponseEntity<UserAuth> makeAdmin(long id) {
        try {
            userAuthService.makeAdmin(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
