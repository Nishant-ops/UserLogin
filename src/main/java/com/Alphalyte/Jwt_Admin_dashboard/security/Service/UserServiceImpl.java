package com.Alphalyte.Jwt_Admin_dashboard.security.Service;


import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.UserReposoritries;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Configuration
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserReposoritries userRepo;

//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public BCryptPasswordEncoder passencoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public List<user> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    @Transactional
    public user UpdateUser(int usercode, user u) {
        u.setUsercode(usercode);
        return userRepo.save(u);
    }


    @Override
    public void deleteById(int usercode) {
        boolean exist = userRepo.existsById(usercode);
        if(!exist) throw new IllegalStateException("User with usercode "+ usercode + " not found.");
        userRepo.deleteById(usercode);
    }

    @Override
    public user AddUser(user user) {
        user.setPassword(passencoder().encode(user.getPassword()));
        userRepo.save(user);
        return user;
    }

    @Override
    public user getUserById(int usercode) {
        boolean exist = userRepo.existsById(usercode);
        if(!exist) throw new NullPointerException("User not found!");
        user u = userRepo.getById(usercode);
        return u;
    }

}
