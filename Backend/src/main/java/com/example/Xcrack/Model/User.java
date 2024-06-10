package com.example.Xcrack.Model;

import jakarta.persistence.*;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    private static final String SHA2_ALGORITHM = "SHA-256"; // SHA-256 hashing algorithm
    private static final String STATIC_SALT = "YourStaticSalt"; // Use a fixed salt

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(nullable = false, unique = true, length = 255)
    private String name;

    @Column(nullable = false)
    @Convert(converter = LocalDateConverter.class)
    private LocalDate dob;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, unique = true, length = 255)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @ManyToMany
    @JoinTable(name = "user_following")
    private List<User> following = new ArrayList<>();

    @ManyToMany(mappedBy = "following")
    private List<User> followers = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reply> replies = new ArrayList<>();

    private boolean banned;

    @ManyToMany
    @JoinTable(name = "user_blocked_users")
    private List<User> blockedUsers = new ArrayList<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Message> sentMessages = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private List<Message> receivedMessages = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Notification> notifications = new ArrayList<>();

    private String bio;
    private String profilePictureUrl;
    private String backgroundPictureUrl;
    private String location;
    private String websiteUrl;

    private String verificationCode; 
    private boolean emailVerified;

    private String forgotPasswordCode; 
    private boolean forgotPasswordVerified = false; 
    private String resetToken;

    @ElementCollection
    @CollectionTable(name = "user_tagging_preferences", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "tagging_preference")
    private List<String> taggingPreferences;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<UserHashtag> userHashtags = new ArrayList<>();

    public User() {}

    public List<UserHashtag> getUserHashtags() {
        return userHashtags;
    }

    public void setUserHashtags(List<UserHashtag> userHashtags) {
        this.userHashtags = userHashtags;
    }
    
    public void followUser(User userToFollow) {
        if (!this.following.contains(userToFollow)) {
            this.following.add(userToFollow);
            userToFollow.getFollowers().add(this);
        }
    }

    public void unfollowUser(User userToUnfollow) {
        if (this.following.contains(userToUnfollow)) {
            this.following.remove(userToUnfollow);
            userToUnfollow.getFollowers().remove(this);
        }
    }

    public void blockUser(User userToBlock) {
        if (!this.blockedUsers.contains(userToBlock)) {
            this.blockedUsers.add(userToBlock);
        }
    }

    public void unblockUser(User userToUnblock) {
        this.blockedUsers.remove(userToUnblock);
    }

    public void setPassword(String password) {
        try {
            if (isPasswordValid(password)) {
                // Hash password with static salt
                String hashedPassword = hashPassword(password);
                this.password = hashedPassword;
            } else {
                this.password = "null";
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static boolean isPasswordValid(String password) {
        return password.length() >= 8 &&
                password.matches(".*\\d.*") &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*[!@#$%^&*()].*");
    }

    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(SHA2_ALGORITHM);
        digest.update(STATIC_SALT.getBytes(StandardCharsets.UTF_8));
        byte[] hashedBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder();
        for (byte hashedByte : hashedBytes) {
            String hex = Integer.toHexString(0xff & hashedByte);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public void addSentMessage(Message message) {
        this.sentMessages.add(message);
    }

    public void removeSentMessage(Message message) {
        this.sentMessages.remove(message);
    }

    public void addReceivedMessage(Message message) {
        this.receivedMessages.add(message);
    }

    public void removeReceivedMessage(Message message) {
        this.receivedMessages.remove(message);
    }
    
    public void addPost(Post post) {
        this.posts.add(post);
    }

    public void removePost(Post post) {
        this.posts.remove(post);
    }

    public void addReply(Reply reply) {
        this.replies.add(reply);
    }

    public void removeReply(Reply reply) {
        this.replies.remove(reply);
    }

    public void ban() {
        this.banned = true;
    }

    public void unban() {
        this.banned = false;
    }
    
    // Getters and Setters

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public boolean isForgotPasswordVerified() {
        return forgotPasswordVerified;
    }

    public void setForgotPasswordVerified(boolean forgotPasswordVerified) {
        this.forgotPasswordVerified = forgotPasswordVerified;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getBackgroundPictureUrl() {
        return backgroundPictureUrl;
    }

    public void setBackgroundPictureUrl(String backgroundPictureUrl) {
        this.backgroundPictureUrl = backgroundPictureUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(List<Message> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(List<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public List<User> getBlockedUsers() {
        return blockedUsers;
    }

    public void setBlockedUsers(List<User> blockedUsers) {
        this.blockedUsers = blockedUsers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<String> getTaggingPreferences() {
        return taggingPreferences;
    }

    public void setTaggingPreferences(List<String> taggingPreferences) {
        this.taggingPreferences = taggingPreferences;
    }

    public String getForgotPasswordCode() {
        return forgotPasswordCode;
    }

    public void setForgotPasswordCode(String forgotPasswordCode) {
        this.forgotPasswordCode = forgotPasswordCode;
    }

}