package com.Alphalyte.Jwt_Admin_dashboard.security.Service;

import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.UserReposoritries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class userDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserReposoritries userReposoritries;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    user user= userReposoritries.findByUsername(username)
            .orElseThrow(()->new UsernameNotFoundException("no username "+username+ "found"));

    return user;
    }
}
