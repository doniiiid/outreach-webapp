package com.outreach.rest.service;

import com.outreach.rest.model.User;
import com.outreach.rest.payload.request.UserDetailRequest;
import com.outreach.rest.repository.UserRepository;
import com.outreach.rest.util.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User updateUser (String username, UserDetailRequest userDetailRequest) {
        Optional<User> existingUser = userRepository.findByUsername(username);

        if(existingUser.isPresent()) {
            User user = existingUser.get();
            String updatedFirstName = userDetailRequest.getFirstName();
            String updatedLastName = userDetailRequest.getLastName();
            if (updatedFirstName != null) {
                user.setFirstName(updatedFirstName);
            }

            if (updatedLastName != null) {
                user.setLastName(updatedLastName);
            }

            return userRepository.save(user);
        }
        else {
            throw new UserNotFoundException("Username not found:" + username);
        }
    }
}
