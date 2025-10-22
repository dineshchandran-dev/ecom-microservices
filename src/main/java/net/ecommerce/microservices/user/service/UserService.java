package net.ecommerce.microservices.user.service;


import net.ecommerce.microservices.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
  private  Long num=1L;

    private final List<User>userList=new ArrayList<>();

    public List<User>viewAllUser(){
        return userList;
    }

    public List<User> addUser(User user){
        user.setId(num++);
        userList.add(user);
        return  userList;
    }

    public Optional<User> retrieveUser(Long id){

  return userList.stream().filter(user-> user.getId().equals(id)).findFirst();
    }


}
