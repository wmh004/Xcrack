package com.example.Xcrack.Controller;

import com.example.Xcrack.Model.User;
import com.example.Xcrack.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody User loginRequest) {
        // Authenticate the user with the provided username and password
        boolean authenticated = loginService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());

        // If authentication succeeds, return "Login successful!"
        if (authenticated) {
            return "Login successful!";
        } else {
            // If authentication fails, return "Invalid username or password"
            return "Invalid username or password";
        }
    }
}
