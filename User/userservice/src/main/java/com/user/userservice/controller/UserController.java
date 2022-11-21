package com.user.userservice.controller;

import com.user.userservice.model.dto.ResponseDTO;
import com.user.userservice.model.entity.User;
import com.user.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<List<User>> readUser() {
        return ResponseEntity.ok(userService.readUser());
    }

    @GetMapping("/user-product")
    public ResponseEntity<ResponseDTO> getUserProduct(@RequestParam("userId") int id) {
        return ResponseEntity.ok(userService.getUserProduct(id));
    }

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        var result = userService.createUser(user);
        return ResponseEntity.ok("User has created!");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestParam int id, @RequestBody User user) {
        if(userService.updateUser(id, user)) {
            return ResponseEntity.ok().body("User has been updated!");
        }
        return ResponseEntity.badRequest().body("User not found!");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam int id) {
        if(userService.deleteUser(id)) {
            return ResponseEntity.ok().body("User has been deleted!");
        }
        return ResponseEntity.badRequest().body("User not found");
    }

    @GetMapping("/user-id")
    public ResponseEntity readUserById(@RequestParam int id) {
        if(userService.readUserById(id) != null) {
            return ResponseEntity.ok().body(userService.readUserById(id));
        }
        return ResponseEntity.badRequest().body("User not found!");
    }

}
