package com.example.Xcrack.Service.Implementation;

import com.example.Xcrack.Model.User;
import com.example.Xcrack.Repository.UserRepository;
import com.example.Xcrack.Service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHashtagServiceImpl userHashtagServiceImpl; 

    @Autowired
    private MailSender mailSender;

    @Override
    public User saveRegistration(User registration) {
        return userRepository.save(registration);
    }

    @Override
    public boolean sendVerificationEmail(String email, String verificationCode) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("xcrackvanguard@gmail.com");
            message.setTo(email);
            message.setSubject(verificationCode 
            + " - Verification Code for Xcrack Account Setup");
            message.setText("Hello there! \n\n" 
            + "We are excited to have you set up your Xcrack account " + email
            + "\n\nYour verification code is: " + verificationCode 
            + "\n\nThis code is valid for the next 2 minutes. Please enter this code in the provided field on the Xcrack registration page to verify your email address and complete your account setup."
            + "\n\nIf you did not request this code, please ignore this email or contact our support team for assistance." 
            + "\n\nThank you for choosing Xcrack. We're thrilled to have you on board!" 
            + "\n\nBest regards! \nThe Xcrack Team");

            mailSender.send(message);
            System.out.println("Verification code sent successfully!");
            return true;
        } catch (Exception e) {
            System.out.println("Failed to send verification code: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    @Override
    public void setVerificationCode(User registration, String verificationCode) {
        registration.setVerificationCode(verificationCode);
        userRepository.save(registration);
    }

    @Override 
    public void SetHashtagPreferences(String Username,String hashtag){ 
        User user = userRepository.findByUsername(Username); 
        Set<String> extractedHashtags = extractHashtags(hashtag); 
        processHashtags(user, extractedHashtags); 
    } 
 
    private Set<String> extractHashtags(String content) { 
        content = content.toLowerCase(); 
        Set<String> hashtags = new HashSet<>(); 
        Pattern pattern = Pattern.compile("#\\w+"); 
        Matcher matcher = pattern.matcher(content); 
        while (matcher.find()) { 
            hashtags.add(matcher.group().substring(1).toLowerCase()); // Remove the '#' character 
        } 
        return hashtags; 
    } 
 
    private void processHashtags(User user, Set<String> hashtags) { 
        for (String hashtag : hashtags) { 
            userHashtagServiceImpl.addInitialHashtagPreference(user, hashtag); 
        } 
    }
}