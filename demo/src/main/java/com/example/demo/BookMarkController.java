package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookMarkController {
    
    @PostMapping("/bookmarkpost")
    public String handlebm(@RequestParam("targetname") String user1,
                           @RequestParam("currname") String user2,
                           @RequestParam("post") String postcontent, Model model)
    {
        Post post = new Post();
        int postid = post.getpostid(user1, postcontent);
        System.out.println(postid);

        BookMark b = BookMark.getInstance2(user2, postid);
        b.createBookMark(b);

        List<String> result = new ArrayList<>();

        User user = new User();
        result = user.getuserdetails(user1);
        System.out.println(result.get(0));

        model.addAttribute("details", result);
        model.addAttribute("username", user2);

        List<String> posts = new ArrayList<>();
        post = new Post();
        posts = post.userpostdetails(user1);
        
        model.addAttribute("userposts", posts);

        return "user_details";
    }

    @PostMapping("/getbookmark")
    public String showbms(@RequestParam("username") String username, Model model){
        
        BookMark b = BookMark.getInstance();
        List<Integer> id = b.getpostid(username);
        List<List<String>> details = new ArrayList<>();
        System.out.println(details.get(0).get(0));


        for(int i = 0; i < id.size(); i++){
            details.add(b.getbookmarks(id.get(i)));
        }

        model.addAttribute("username", username);
        model.addAttribute("bookmarklist", details);

        return "bookmarks";
    }

}
