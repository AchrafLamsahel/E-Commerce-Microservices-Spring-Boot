package org.authmicroservice.client;

import org.authmicroservice.dto.RegisterRequestDTO;
import org.authmicroservice.dto.RegisterResponseDTO;
import org.authmicroservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(name = "user-microservice")
public interface UserServiceClient {

    @PostMapping("/users/register")
    ResponseEntity<RegisterResponseDTO> save(@RequestBody RegisterRequestDTO request);

    @GetMapping("/users/username={username}")
    ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username);

}
