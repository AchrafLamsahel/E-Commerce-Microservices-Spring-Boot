package org.authmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.authmicroservice.enums.Role;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class UserDTO {
    private Long userId;
    private String firstname;
    private String lastname;
    private String email;
    private String numberPhone;
    private String password;
    private Role role;
}
