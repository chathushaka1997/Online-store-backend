package com.example.demo.controller;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.model.UserResponse;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public UserService userService;

    @GetMapping("/test")
    public UserResponse getAllUsers2() {
        log.info("GetUsers started");
        UserResponse userResponse = new UserResponse();
        userResponse.setUsers(userService.getAllUsers());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(userResponse);
        log.info(jsonString);
        log.info("GetUsers completed");
        return userResponse;
    }
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @GetMapping("/test12")
    public List<User> getUsersByQuery() {
        return userRepository.getAllUsers().orElseThrow(() -> new UserNotFoundException(5454654465465465L));

    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {


        Optional<User> fetchedUser = userRepository.findById(user.getId());
        if(!fetchedUser.isPresent()){
            return new ResponseEntity<String>("User not found",HttpStatus.NOT_FOUND);
        }

        if(!fetchedUser.get().getPassword().equals(user.getPassword())){
            return new ResponseEntity<String>("Incorrect password",HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<String>("Login sucess",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());

        return userRepository.save(existingUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
