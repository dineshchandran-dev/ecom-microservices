package net.ecommerce.microservices.user.service;


import net.ecommerce.microservices.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {


    private final List<User>userList=new ArrayList<>();

    public List<User>viewAllUser(){
        return userList;
    }

    public List<User> addUser(User user){
        userList.add(user);
        return  userList;
    }

}
