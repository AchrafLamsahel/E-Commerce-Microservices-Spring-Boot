package org.authmicroservice.service;

import lombok.AllArgsConstructor;
import org.authmicroservice.client.CustomErrorDecoder;
import org.authmicroservice.client.UserServiceClient;
import org.authmicroservice.model.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class CustomUserDetailsService implements ICustomUserDetailService{
    private final UserServiceClient userServiceClient;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userServiceClient.getUserByUsername(email).getBody();
        assert user != null;
        return new CustomUserDetails(user);
    }
}
