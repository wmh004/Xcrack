package com.example.Xcrack.Controller;

import com.example.Xcrack.Model.User;
import com.example.Xcrack.Service.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/forgotPassword")
public class ForgotPasswordController {

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    private static boolean isForgotPasswordVerified = false;

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        boolean emailSent = forgotPasswordService.sendForgotPasswordEmail(email);
        if (emailSent) {
            return "Password reset email sent. Please check your email.";
        } else {
            return "Failed to send password reset email. Please try again.";
        }
    }

    @PostMapping("/verify-forgot-password")
    public String verifyForgotPassword(@RequestBody Map<String, String> requestBody) {
        String verificationCode = requestBody.get("verificationCode");
        if (verificationCode == null) {
            return "Verification code is required.";
        }
        boolean codeVerified = forgotPasswordService.verifyForgotPasswordCode(verificationCode);
        if (codeVerified) {
            isForgotPasswordVerified = true;
            return "Verification code verified successfully.";
        } else {
            isForgotPasswordVerified = false;
            return "Invalid verification code. Please try again.";
        }
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody Map<String, String> requestBody) {
        if (!isForgotPasswordVerified) {
            return "Verification code not verified. Please verify the code first.";
        }

        String newPassword = requestBody.get("newPassword");
        if (newPassword == null) {
            return "New password is required.";
        }

        if (!User.isPasswordValid(newPassword)) {
            return "Password does not meet complexity";
        }

        // Use a previously verified code for the password reset
        boolean passwordReset = forgotPasswordService.resetPassword(newPassword);
        if (passwordReset) {
            isForgotPasswordVerified = false; // Reset the verification state after successful password reset
            return "Password reset successful!";
        } else {
            return "Password cannot be the same as old password.";
        }
    }
}
