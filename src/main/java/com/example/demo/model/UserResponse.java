package com.example.demo.model;

import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
    private List<User> users;
}
