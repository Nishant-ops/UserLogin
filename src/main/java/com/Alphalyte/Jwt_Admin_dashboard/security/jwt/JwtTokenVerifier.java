package com.Alphalyte.Jwt_Admin_dashboard.security.jwt;


import com.Alphalyte.Jwt_Admin_dashboard.security.Service.userDetailsServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenVerifier extends OncePerRequestFilter {

    private JwtUsernameandPasswordAuthenticationFilter jwtUsernameandPasswordAuthenticationFilter;
    private userDetailsServiceImpl adminDetailsService;

    public JwtTokenVerifier(JwtUsernameandPasswordAuthenticationFilter jwtUsernameandPasswordAuthenticationFilter,
                            userDetailsServiceImpl adminDetailsService) {
        this.jwtUsernameandPasswordAuthenticationFilter = jwtUsernameandPasswordAuthenticationFilter;
        this.adminDetailsService = adminDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String tokenAuth=jwtUsernameandPasswordAuthenticationFilter.getToken(request);

        if(tokenAuth!=null)
        {
            String Username=jwtUsernameandPasswordAuthenticationFilter.getUsernameFromToken(tokenAuth);

            if(Username!=null)
            {
                UserDetails userDetails=adminDetailsService.loadUserByUsername(Username);

                if(jwtUsernameandPasswordAuthenticationFilter.ValidateToken(tokenAuth, userDetails)) {

                    UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);



                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
