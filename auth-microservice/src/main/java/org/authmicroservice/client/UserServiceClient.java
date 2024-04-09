package org.authmicroservice.client;

import org.authmicroservice.dto.RegisterRequestDTO;
import org.authmicroservice.dto.RegisterResponseDTO;
import org.authmicroservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-microservice")
public interface UserServiceClient {

    @PostMapping("/users/register")
    ResponseEntity<RegisterResponseDTO> save(@RequestBody RegisterRequestDTO request);

    @GetMapping("/users/getUserByEmail/{email}")
    ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email);

    @GetMapping("/users/existsByEmail/{email}")
    boolean existsByEmail(@PathVariable String email);

    @GetMapping("/users/confirm-account")
    ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken);

}
