package com.example.Xcrack.Controller;

import com.example.Xcrack.Model.User;
import com.example.Xcrack.Service.SignUpService;
import com.example.Xcrack.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/signUp")
public class SignUpController {

    @Autowired
    private UserService userService;

    @Autowired
    private SignUpService signUpService;

    private static User newUser;

    @PostMapping("/add")
    public String add(@RequestBody User registration) {
        if (userService.existsByUsername(registration.getUsername())) {
            return "Username is already used";
        } else if (userService.existsByEmail(registration.getEmail())) {
            return "Email is already used";
        } 
        else if (registration.getPassword().equals("null")) {
            return "Password does not meet complexity";
        } else {          
            // Generate verification code
            String verificationCode = signUpService.generateVerificationCode();
            newUser = registration;
            newUser.setVerificationCode(verificationCode);

            // Send verification code to the user's email
            boolean emailSent = signUpService.sendVerificationEmail(newUser.getEmail(), verificationCode);

            if (emailSent) {
                return "Verification code sent to your email. Please check and verify.";
            } else {
                return "Failed to send verification email. Please try again.";
            }
        }
    }

    @PostMapping("/verify")
    public String verifyRegistration(@RequestBody Map<String, String> requestBody) {
        String code = requestBody.get("verificationCode");
        if (newUser == null) {
            return "No registration data found. Please register first.";
        }
        if (code == null || !code.equals(newUser.getVerificationCode())) {
            return "Invalid verification code. Please try again.";
        }
        newUser.setEmailVerified(true);
        signUpService.saveRegistration(newUser); // problem is here
        newUser = null; // Reset newUser after registration is complete
        return "Registration successful!";
    }

    @PostMapping("/initialHashtag/{username}")
    public String AddInitialHashtag(@PathVariable String username, @RequestBody String hashtag) {
        signUpService.SetHashtagPreferences(username, hashtag);
        return "Hashtag chosen.";
    }
}
