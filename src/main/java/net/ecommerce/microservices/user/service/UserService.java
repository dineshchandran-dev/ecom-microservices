package net.ecommerce.microservices.user.service;
import net.ecommerce.microservices.user.entity.User;
import net.ecommerce.microservices.user.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private  UserRepo userRepo;

    public List<User>viewAllUser(){
        return userRepo.findAll();
    }

    public List<User> addUser(User user){
        userRepo.save(user);
        return  userRepo.findAll();
    }

    public Optional<User> retrieveUser(Long id){

  return userRepo.findAll().stream().filter(user-> user.getId().equals(id)).findFirst();
    }

    public Optional<User> updateUserDetails(User user) {
        return userRepo.findById(user.getId())
                .map(existingUser -> {

                    if (user.getFirstName() != null &&
                            !user.getFirstName().equalsIgnoreCase(existingUser.getFirstName())) {
                        existingUser.setFirstName(user.getFirstName());
                    }

                    if (user.getEmail() != null &&
                            !user.getEmail().equalsIgnoreCase(existingUser.getEmail())) {
                        existingUser.setEmail(user.getEmail());
                    }

                    // Optional: update other fields
                    if (user.getLastName() != null &&
                            !user.getLastName().equalsIgnoreCase(existingUser.getLastName())) {
                        existingUser.setLastName(user.getLastName());
                    }

                    if (user.getPhoneNumber() != null &&
                            !user.getPhoneNumber().equals(existingUser.getPhoneNumber())) {
                        existingUser.setPhoneNumber(user.getPhoneNumber());
                    }

                    if (user.getRole() != null &&
                            user.getRole() != existingUser.getRole()) {
                        existingUser.setRole(user.getRole());
                    }

                    userRepo.save(existingUser);
                    return existingUser;
                });
    }




}
