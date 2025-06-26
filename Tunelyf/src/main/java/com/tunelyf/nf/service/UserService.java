package com.tunelyf.nf.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunelyf.nf.model.User;
import com.tunelyf.nf.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        if (userRepository.existsByUid(user.getUid())) {
            // If user already exists, return existing user
            return userRepository.findByUid(user.getUid()).get();
        }
        return userRepository.save(user);
    }

    public Optional<User> getUserByUid(String uid) {
        return userRepository.findByUid(uid);
    }

    public User updateUser(String uid, User updatedUser) {
        Optional<User> optionalUser = userRepository.findByUid(uid);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setProfilePic(updatedUser.getProfilePic());
            return userRepository.save(user);
        }
        return null;
    }
}
