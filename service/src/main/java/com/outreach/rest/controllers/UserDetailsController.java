package com.outreach.rest.controllers;

import com.outreach.rest.model.User;
import com.outreach.rest.payload.request.UserDetailRequest;
import com.outreach.rest.repository.UserRepository;
import com.outreach.rest.security.services.UserDetailsImpl;
import com.outreach.rest.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserDetailsController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/details")
    public ResponseEntity<User> updateUserDetails(@AuthenticationPrincipal UserDetailsImpl userDetails, @Valid @RequestBody UserDetailRequest userDetailRequest) {
        String username = userDetails.getUsername();
        User user = userService.updateUser(username, userDetailRequest);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUsername();
        userService.deleteUser(username);

        return ResponseEntity.ok().build();
    }
}
