package pers.idc.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.idc.capstone.model.UserEntity;
import pers.idc.capstone.service.UserService;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(UserEntity userEntity) {
        return ResponseEntity.ok(userService.save(userEntity));
    }

    @PostMapping("/update")
    public ResponseEntity<UserEntity> update(UserEntity userEntity) {
        return ResponseEntity.ok(userService.update(userEntity));
    }

    @GetMapping
    public ResponseEntity<UserEntity> findById(String id) {
        try {
            return ResponseEntity.ok(userService.findById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
