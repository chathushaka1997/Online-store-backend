package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public List<User> getAllUsers(){
    List<User> users = new ArrayList<User>();
    User user1 = new User("lamal","sample");
    User user2 = new User("sunil","test");
    users.add(user1);
    users.add(user2);
    return  users;
    }
}
