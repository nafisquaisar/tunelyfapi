package com.tunelyf.nf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tunelyf.nf.model.User;
import com.tunelyf.nf.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    // Register or return existing user
    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }

    // Get user by UID
    @GetMapping("/{uid}")
    public ResponseEntity<?> getUser(@PathVariable("uid") String uid) {
        Optional<User> user = userService.getUserByUid(uid);
        return user.isPresent()
                ? ResponseEntity.ok(user.get())
                : ResponseEntity.notFound().build();
    }

    // Update user profile
    @PutMapping("/{uid}")
    public ResponseEntity<?> updateUser(@PathVariable("uid") String uid, @RequestBody User updatedUser) {
        User user = userService.updateUser(uid, updatedUser);
        return user != null
                ? ResponseEntity.ok(user)
                : ResponseEntity.notFound().build();
    }
}
