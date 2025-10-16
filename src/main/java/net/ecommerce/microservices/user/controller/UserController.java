package net.ecommerce.microservices.user.controller;

import lombok.RequiredArgsConstructor;
import net.ecommerce.microservices.user.entity.User;
import net.ecommerce.microservices.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

   @Autowired
   private UserService userService;

    @GetMapping("/user")
    public List<User> fetchAllUser(){
        return userService.viewAllUser();
    }


    @PostMapping("/user")
    public List<User> AddUser(@RequestBody User user){

       return  userService.addUser(user);
    }

}
