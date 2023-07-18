package com.example.demo.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class UserRequest {
    @NotNull(message = "Name required")
    private String name;
    @NotNull(message = "Email required")
    @Email(message = "Invalid email")
    private String email;
    @NotNull(message = "Password required")
    private String password;
}
