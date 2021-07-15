package com.Alphalyte.Jwt_Admin_dashboard.Controller;

import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.UserReposoritries;
import com.Alphalyte.Jwt_Admin_dashboard.security.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class AdminController {
    @Autowired
    UserServiceImpl userDetails;

    @Autowired
    private UserReposoritries userRepo;

    @GetMapping("/dashboard")
    public String dashboard(){
        return "Welcome to dashboard!";
    }


    @GetMapping(path = "/users")
    public List<user> users(){
        return userDetails.getAllUsers();
    }

    @DeleteMapping(path = "/users/{usercode}")
    public void deleteUser(@PathVariable("usercode") int usercode){
        userDetails.deleteById(usercode);
    }

    @PostMapping(path = {"/users"})
    public user addUser(@RequestBody user u){
        userDetails.AddUser(u);
        return u;
    }

    @GetMapping(path = "/users/{usercode}")
    public user getUserById(@PathVariable("usercode") int usercode){
        return userDetails.getUserById(usercode);
    }

    @PutMapping(path = {"/users"})
    public user updateUser(@RequestBody user u){
        userDetails.UpdateUser(u.getUsercode(),u);
        return u;
    }


}
