package net.ecommerce.user.controller;

import lombok.RequiredArgsConstructor;

import net.ecommerce.user.dto.RequestDto;
import net.ecommerce.user.dto.UserResponseDto;
import net.ecommerce.user.entity.User;
import net.ecommerce.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> fetchAllUser() {
        return new ResponseEntity<>(userService.viewAllUser(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<List<UserResponseDto>> AddUser(@RequestBody User user) {

        return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseEntity<UserResponseDto> retrieve(@PathVariable Long id) {

//        Optional<User> user= userService.retrieveUser(id);
//        if(user==null){
//            return  ResponseEntity.notFound().build();
//        }
//        return new ResponseEntity<>(user,HttpStatus.OK);

        return userService.retrieveUser(id).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());

    }
    @PostMapping("/update/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable Long id, @RequestBody RequestDto user) {
        return new ResponseEntity<>(userService.updateUserDetails(id,user),HttpStatus.OK);
    }

}
