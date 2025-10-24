package net.ecommerce.microservices.user.controller;

import lombok.RequiredArgsConstructor;
import net.ecommerce.microservices.user.entity.User;
import net.ecommerce.microservices.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

   @Autowired
   private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> fetchAllUser() {
        return new ResponseEntity<>(userService.viewAllUser(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<List<User>> AddUser(@RequestBody User user) {

        return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseEntity<User> retrieve(@PathVariable Long id) {

//        Optional<User> user= userService.retrieveUser(id);
//        if(user==null){
//            return  ResponseEntity.notFound().build();
//        }
//        return new ResponseEntity<>(user,HttpStatus.OK);

        return userService.retrieveUser(id).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());

    }
    @PostMapping("/update")
    public ResponseEntity<User>update(@RequestBody User user){
        return userService.updateUserDetails(user).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

}
