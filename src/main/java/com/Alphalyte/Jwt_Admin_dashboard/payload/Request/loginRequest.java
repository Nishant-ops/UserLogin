package com.Alphalyte.Jwt_Admin_dashboard.payload.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class loginRequest {

    private String username;
    private String password;

    public loginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
