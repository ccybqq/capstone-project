package pers.idc.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pers.idc.capstone.exception.IdNotNullException;
import pers.idc.capstone.model.UserEntity;
import pers.idc.capstone.service.UserService;

import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserEntity> register(@RequestBody UserEntity userEntity) {
        try {
            return ResponseEntity.ok(userService.save(userEntity));
        } catch (SQLIntegrityConstraintViolationException e) {
            return ResponseEntity.badRequest()
                    .header("Message", "Email already in use.")
                    .build();
        } catch (IdNotNullException e) {
            return ResponseEntity.badRequest()
                    .header("Message", e.getMessage())
                    .build();
        }
    }

    @PutMapping
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
    public ResponseEntity<UserEntity> findByEmail(@RequestParam String email) {
        try {
            return ResponseEntity.ok(userService.findByEmail(email));
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest()
                    .header("Message", "User not found.")
                    .build();
        }
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<UserEntity> deleteByEmail(@RequestParam String email) {
        userService.deleteByEmail(email);
        return ResponseEntity.ok().build();
    }
}
