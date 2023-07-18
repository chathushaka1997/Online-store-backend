package com.example.demo.service;

import com.example.demo.dto.UserRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> getAllUsers(){
    List<User> users = new ArrayList<User>();
    User user1 = new User("lamal","sample");
    User user2 = new User("sunil","test");
    users.add(user1);
    users.add(user2);
    return  users;
    }

    public User insertUser (UserRequest userRequest){
        User user =  User.build(9223372054775808L,userRequest.getName(),userRequest.getEmail(),userRequest.getPassword(),null);


        return userRepository.save(user);
    }
}
