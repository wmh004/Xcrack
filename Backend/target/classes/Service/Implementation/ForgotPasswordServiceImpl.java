package com.example.Xcrack.Service.Implementation;

import com.example.Xcrack.Model.User;
import com.example.Xcrack.Repository.UserRepository;
import com.example.Xcrack.Service.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    private User verifiedUser; // To hold the user whose verification code is verified

    @Override
    public boolean sendForgotPasswordEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            // Generate a unique verification code
            String verificationCode = generateVerificationCode();
            user.setForgotPasswordCode(verificationCode);
            userRepository.save(user);

            // Send the verification code to the user's email
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(email);
                message.setSubject(verificationCode + " - Password Reset Verification Code for Xcrack Account");
                message.setText("Hello there,\n\n" +
                "You've requested to reset your password on Xcrack. Here is your password reset verification code:\n\n" +
                "Verification Code: " + verificationCode + "\n\n" +
                "Please use this code to securely reset your password. This code will expire in 2 minutes.\n\n" +
                "If you didn't request this change, please ignore this email or contact our support team immediately.\n\n" +
                "Best regards,\n" +
                "The Xcrack Team");
    
                mailSender.send(message);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean verifyForgotPasswordCode(String verificationCode) {
        User user = userRepository.findByForgotPasswordCode(verificationCode);
        if (user != null) {
            user.setForgotPasswordVerified(true);
            userRepository.save(user);
            verifiedUser = user; // Store the verified user
            return true;
        }
        return false;
    }

    @Override
    public boolean resetPassword(String newPassword) {
        if (verifiedUser != null && verifiedUser.isForgotPasswordVerified()) {
            String hashedNewPassword = null;
            try {
                hashedNewPassword = User.hashPassword(newPassword);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace(); // Handle the exception according to your application's error handling strategy
                return false;
            }
            
            // Check if the new hashed password is the same as the current hashed password
            if (verifiedUser.getPassword().equals(hashedNewPassword)) {
                return false; // Password unchanged, return false
            } else {
                // Set the hashed password to the user object
                verifiedUser.setPassword(newPassword);

                // Clear the forgot-password verification state and code after a successful reset
                verifiedUser.setForgotPasswordVerified(false);
                verifiedUser.setForgotPasswordCode(null);

                // Save the updated user object to the repository
                userRepository.save(verifiedUser);
                verifiedUser = null; // Reset the verified user
                return true; // Password reset successful
            }
        }
        return false; // Verification failed or user not found
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}
