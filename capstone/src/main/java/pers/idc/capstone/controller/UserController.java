package pers.idc.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pers.idc.capstone.exception.IdNotNullException;
import pers.idc.capstone.exception.UniqueConstraintViolationException;
import pers.idc.capstone.model.UserEntity;
import pers.idc.capstone.service.UserService;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(
        origins = "http://localhost:4200",
        exposedHeaders = "Message"
)
public class UserController {
    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<UserEntity> save(@RequestBody UserEntity userEntity) {
        try {
            return ResponseEntity.ok(userService.save(userEntity));
        } catch (IdNotNullException | UniqueConstraintViolationException e) {
            return ResponseEntity.badRequest()
                    .header("Message", e.getMessage())
                    .build();
        }
    }

    @PutMapping
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<UserEntity> update(@RequestBody UserEntity userEntity) {
        try {
            return ResponseEntity.ok(userService.update(userEntity));
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest()
                    .header("Message", "User does not exist.")
                    .build();
        }
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<UserEntity> findByEmail(@RequestParam String email) {
        try {
            return ResponseEntity.ok()
                    .header("Message", "User found.")
                    .body(userService.findByEmail(email));
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest()
                    .header("Message", "User not found.")
                    .build();
        }
    }

    @DeleteMapping
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserEntity> deleteByEmail(@RequestParam String email) {
        userService.deleteByEmail(email);
        return ResponseEntity.ok().build();
    }
}
