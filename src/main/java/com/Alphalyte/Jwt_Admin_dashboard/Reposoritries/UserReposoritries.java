package com.Alphalyte.Jwt_Admin_dashboard.Reposoritries;

import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserReposoritries extends JpaRepository<user,Integer> {
    Optional<user> findByUsername(String username);

    Optional<user> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
