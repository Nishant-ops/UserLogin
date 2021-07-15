package com.Alphalyte.Jwt_Admin_dashboard.security.Service;

import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public List<user> getAllUsers();

    public user UpdateUser(int usercode, user u);

    public void deleteById(int usercode);

    public user AddUser(user user);

    public user getUserById(int usercode);

}
