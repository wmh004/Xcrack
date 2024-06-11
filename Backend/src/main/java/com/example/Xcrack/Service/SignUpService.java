package com.example.Xcrack.Service;

import com.example.Xcrack.Model.User;

public interface SignUpService {

    User saveRegistration(User registration);
    String generateVerificationCode();
    void setVerificationCode(User registration, String verificationCode);
    public boolean sendVerificationEmail(String email, String verificationCode);
    public void SetHashtagPreferences(String Username,String hashtag);
}
