package com.outreach.rest;

import com.outreach.rest.controllers.UserDetailsController;
import com.outreach.rest.model.User;
import com.outreach.rest.payload.request.UserDetailRequest;
import com.outreach.rest.security.services.UserDetailsImpl;
import com.outreach.rest.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class UserDetailsControllerTests {
    @InjectMocks
    private UserDetailsController userDetailsController;

    @Mock
    private UserService userService;

    @Mock
    private UserDetailsImpl userDetails;

    @Test
    public void testUpdateUserDetails() {
        // Initialize Mocks
        MockitoAnnotations.openMocks(this);

        String username = "321bobbilly";
        UserDetailRequest userDetailRequest = new UserDetailRequest();
        userDetailRequest.setFirstName("Franklin");
        userDetailRequest.setLastName("Bob");

        User expectedUser = new User();
        expectedUser.setFirstName("Billy");
        expectedUser.setLastName("Bob");
        expectedUser.setUsername(username);

        when(userService.updateUser(username, userDetailRequest)).thenReturn(expectedUser);
        when(userDetails.getUsername()).thenReturn(username);

        ResponseEntity<User> response = userDetailsController.updateUserDetails(userDetails, userDetailRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUser, response.getBody());

        verify(userService, times(1)).updateUser(username, userDetailRequest);
    }
}
