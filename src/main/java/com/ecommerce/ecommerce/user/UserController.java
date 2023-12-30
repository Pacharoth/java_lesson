package com.ecommerce.ecommerce.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class UserController {
    @Autowired
    private UserService UserService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAUser() {
        return new ResponseEntity<List<User>>(UserService.getAll(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody UserRequest UserRequest) {
        User User = UserService.createUser(UserRequest);
        return new ResponseEntity<User>(User, HttpStatus.CREATED);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id,
            @RequestBody UserRequest UserRequest) {
        User User = UserService.updateUser(id, UserRequest);
        return new ResponseEntity<User>(User, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<ResponseDelete> deleteUser(@PathVariable("id") Integer id){
        if(UserService.deleteUser(id)){
            return new ResponseEntity<ResponseDelete>(new ResponseDelete("Delete User Succesful"),HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<ResponseDelete>(new ResponseDelete("Delete Fail"),HttpStatus.EXPECTATION_FAILED);
    }
}
