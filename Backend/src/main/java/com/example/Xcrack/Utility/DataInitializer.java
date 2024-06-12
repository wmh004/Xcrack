package com.example.Xcrack.Utility;

import com.example.Xcrack.Model.Hashtag;
import com.example.Xcrack.Model.Post;
import com.example.Xcrack.Model.Reply;
import com.example.Xcrack.Model.User;
import com.example.Xcrack.Repository.PostRepository;
import com.example.Xcrack.Repository.ReplyRepository;
import com.example.Xcrack.Repository.UserRepository;
import com.example.Xcrack.Service.HashtagService;
import com.example.Xcrack.Service.UserHashtagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private HashtagService hashtagService;

    @Autowired
    private UserHashtagService userHashtagService;

    @Override
    public void run(String... args) throws Exception {
        List<String> catPosts = Arrays.asList(
            "Just adopted a new kitten! #cat #cute",
            "Cats are such graceful creatures. #cat #love",
            "My cat loves to chase laser pointers. #cat #playtime",
            "Watching my cat sleep is so relaxing. #cat #peace",
            "Cats have the best reactions. #cat #funny",
            "Feeding my cat some delicious treats. #cat #food",
            "My cat's purring is so soothing. #cat #happy",
            "Cats can be so independent yet loving. #cat #unique",
            "Every cat has its own personality. #cat #individual",
            "My cat just did the funniest thing! #cat #hilarious"
        );

        List<String> educationPosts = Arrays.asList(
            "The importance of lifelong learning cannot be overstated. #education #knowledge",
            "Attending a webinar on digital transformation in education. #education #technology",
            "Reading a fascinating book on the history of education. #education #reading",
            "Education is the key to success. #education #success",
            "Studying for my exams. #education #study",
            "Education opens doors to new opportunities. #education #opportunities",
            "Sharing knowledge is a noble endeavor. #education #sharing",
            "The future of education is digital. #education #future",
            "Teachers make a lasting impact. #education #teachers",
            "The best investment is in education. #education #investment"
        );

        List<String> animePosts = Arrays.asList(
            "Just finished watching Attack on Titan, what an epic series! #anime #AOT",
            "Studio Ghibli movies are timeless masterpieces. #anime #Ghibli",
            "Can't wait for the next season of My Hero Academia! #anime #MHA",
            "Naruto taught me the value of perseverance. #anime #Naruto",
            "Anime has some of the best storylines. #anime #stories",
            "Rewatching Fullmetal Alchemist, still amazing. #anime #FMA",
            "Anime conventions are so much fun! #anime #conventions",
            "Collecting anime figures is a great hobby. #anime #figures",
            "Anime soundtracks are simply the best. #anime #music",
            "Discussing anime theories with friends. #anime #theories"
        );

        List<String> badmintonPosts = Arrays.asList(
            "Had an intense badminton match today! #badminton #sport",
            "Practicing my smash technique. #badminton #skills",
            "Badminton is such a fast-paced game. #badminton #speed",
            "Learning new badminton strategies. #badminton #strategy",
            "Playing badminton keeps me fit. #badminton #fitness",
            "Watching professional badminton matches is inspiring. #badminton #professional",
            "Badminton requires great reflexes. #badminton #reflexes",
            "Joining a badminton club has been amazing. #badminton #club",
            "Improving my footwork in badminton. #badminton #footwork",
            "Playing badminton with friends is so much fun. #badminton #friends"
        );

        List<String> programmingPosts = Arrays.asList(
            "Just wrote my first Python script! #programming #Python",
            "Debugging is like being a detective in a crime movie. #programming #debugging",
            "Learning Java has been a great experience. #programming #Java",
            "Attending a coding bootcamp. #programming #bootcamp",
            "Programming challenges are so rewarding. #programming #challenges",
            "Writing clean code is essential. #programming #cleanCode",
            "Open-source projects are a great way to learn. #programming #openSource",
            "Exploring the world of machine learning. #programming #machineLearning",
            "Git is an indispensable tool for programmers. #programming #Git",
            "Pair programming improves code quality. #programming #pairProgramming"
        );

        // Create users
        User user1 = new User();
        user1.setName("Wan Hisham");
        user1.setDob(LocalDate.of(1990, 1, 1));
        user1.setEmail("whisham04@gmail.com");
        user1.setUsername("wmh004");
        user1.setPassword("Wmh0404!");

        User user2 = new User();
        user2.setName("Amir Hakim");
        user2.setDob(LocalDate.of(1991, 2, 2));
        user2.setEmail("muhdamirhakim2411@gmail.com");
        user2.setUsername("amirhakim2411");
        user2.setPassword("AmirHakim@241");

        User user3 = new User();
        user3.setName("Syahmie");
        user3.setDob(LocalDate.of(1992, 3, 3));
        user3.setEmail("syahmie@gmail.com");
        user3.setUsername("syahmie");
        user3.setPassword("Syahmie123!");

        User user4 = new User();
        user4.setName("Ezra");
        user4.setDob(LocalDate.of(1993, 4, 4));
        user4.setEmail("ezra@gmail.com");
        user4.setUsername("ezra");
        user4.setPassword("Ezra123!");

        User user5 = new User();
        user5.setName("Hakimi");
        user5.setDob(LocalDate.of(1994, 5, 5));
        user5.setEmail("hakimi@gmail.com");
        user5.setUsername("hakimi");
        user5.setPassword("Hakimi123!");

        // Save users
        userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));

        // Create and save posts for each user
        createAndSavePostsWithReplies(catPosts, user1);
        createAndSavePostsWithReplies(educationPosts, user2);
        createAndSavePostsWithReplies(animePosts, user3);
        createAndSavePostsWithReplies(badmintonPosts, user4);
        createAndSavePostsWithReplies(programmingPosts, user5);
    }

    private void createAndSavePostsWithReplies(List<String> postContents, User user) {
        postContents.forEach(content -> {
            Post post = new Post(content, user);
            
            // Extract hashtags and save them
            Set<String> hashtags = extractHashtags(content);
            processHashtags(user, hashtags, post);

            user.addPost(post);
            postRepository.save(post);
            
            // Create and save 5 replies for each post
            for (int i = 1; i <= 5; i++) {
                Reply reply = new Reply("This is reply " + i + " for post: " + content, user, post);
                
                // Extract hashtags from replies and save them
                Set<String> replyHashtags = extractHashtags(reply.getContent());
                processHashtags(user, replyHashtags, reply);
                processHashtagsFromParentPost(user, post);

                post.addReply(reply);
                post.setReplyCount(post.getReplyCount() + 1);
                post.addReply(reply);
                replyRepository.save(reply);
            }
            });
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

    private void processHashtags(User user, Set<String> hashtags, Post post) {
        for (String hashtag : hashtags) {
            post.setHashtags(hashtagService.addHashtag(hashtag));
            userHashtagService.addUserHashtagFromPost(user, hashtag);
        }
    }

    private void processHashtags(User user, Set<String> hashtags, Reply reply) {
        for (String hashtag : hashtags) {
            reply.setHashtags(hashtagService.addHashtag(hashtag));
            userHashtagService.addUserHashtagFromPost(user, hashtag);
        }
    }

    private void processHashtagsFromParentPost(User user, Post post){
        for(Hashtag hashtag : post.getHashtags()){
            userHashtagService.addUserHashtagFromReply(user, hashtag.getHashtag()); 
        }
    }
}