package com.example.Xcrack.Service;

public interface ForgotPasswordService {
    
    boolean sendForgotPasswordEmail(String email);
    boolean verifyForgotPasswordCode(String verificationCode);
    boolean resetPassword(String newPassword);
}
