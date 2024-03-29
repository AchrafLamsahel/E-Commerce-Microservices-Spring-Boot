package org.usermicroservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.usermicroservice.enums.Active;
import org.usermicroservice.enums.Role;
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class UserDTO {
    private Long userId;
    private String firstname;
    private String lastname;
    private String email;
    private String numberPhone;
    private Role role;
    private Active isActive;
}
