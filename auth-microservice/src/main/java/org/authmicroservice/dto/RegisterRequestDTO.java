package org.authmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class RegisterRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String numberPhone;
    private String password;
}
