package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {


    @PostMapping("/createpost")
    public String createpostpage(@RequestParam("username") String username, Model model){
        model.addAttribute("username", username);
        return "createpost";
    }

    @PostMapping("/dispposts")
    public String displaypost(@RequestParam("username") String username, 
                              @RequestParam("content") String content, Model model,
                              @RequestParam("urls") String url)
    {
       Post post = new Post(0, 0, username, content);
       boolean result = post.create_post(username, post);

       if(result == true){
            model.addAttribute("username", username);
            model.addAttribute("content", content);
            return "displaypost";
       }

       return "errorpage";
    }

    @PostMapping("/viewpost")
    public String viewpostpage(@RequestParam("targetname") String user1,
                               @RequestParam("currname") String user2,
                               @RequestParam("post") String postcontent, Model model)
    {

        Post post = new Post();

        Comment c = Comment.getInstance();

        int postid = post.getpostid(user1, postcontent);

        List<List<String>> comments = c.getcomments(postid);

        List<String> postdetails = new ArrayList<>();

        postdetails = post.getpostdetails(user1, postcontent);

        model.addAttribute("targetname", user1);
        model.addAttribute("postdetails", postdetails);
        model.addAttribute("currname", user2);
        model.addAttribute("comments", comments);
    

        return "displaypost2";
    }

    @PostMapping("/likepost")
    public String handlelike(@RequestParam("targetname") String user1,
                             @RequestParam("currname") String user2,
                             @RequestParam("post") String postcontent, Model model)
    {
        Post post = new Post(user1, postcontent);

        int status = post.find_liked(user2, postcontent);
        System.out.println(status);

        if(status == 0)
        {
            post.likepost(post);
            post.insertintolike(user2, postcontent, 1);
        }

        return viewpostpage(user1, user2, postcontent, model);
    }

    @PostMapping("/dislikepost")
    public String handledislike(@RequestParam("targetname") String user1,
                             @RequestParam("currname") String user2,
                             @RequestParam("post") String postcontent, Model model)
    {
        Post post = new Post(user1, postcontent);

        int status = post.find_liked(user2, postcontent);
        System.out.println(status);

        if(status == 1){
           post.dislikepost(post);
           post.updatelike(user2, postcontent, 0);
        }

        return viewpostpage(user1, user2, postcontent, model);
    }

    @PostMapping("/seemyposts")
    public String viewposts(@RequestParam("username") String username, Model model){
        Post post = new Post();
        List<List<String>> details = post.getalldetails(username);


        model.addAttribute("username", username);
        model.addAttribute("allposts", details);

        return "viewpost";
    }

    @PostMapping("/deletepost")
    public String handledelete(@RequestParam("username") String username, 
                               @RequestParam("content") String content, Model model)
    {
        Post post = new Post();
        int id = post.getpostid(username, content);

        post.deletepost(id);

        return viewposts(username, model);
        
    }

    @PostMapping("/editpost")
    public String handledit(@RequestParam("username") String username, 
                            @RequestParam("content") String content, Model model)

    {
        model.addAttribute("username", username);
        model.addAttribute("content", content);

        return "editpost";
    }
    
    @PostMapping("/changepost")
    public String changepost(@RequestParam("oldcontent") String oldcontent,
                              @RequestParam("newcontent") String newcontent, 
                              @RequestParam("username") String username, Model model)
    {
        Post post = new Post();
        post.updatepost(username, oldcontent, newcontent);

        model.addAttribute("username", username);
        model.addAttribute("content", newcontent);

        return "displaypost";
    }

    @PostMapping("/seepost")
    public String showpost(@RequestParam("username") String username, 
                           @RequestParam("content") String content, Model model){
            
         return viewpostpage(username, username, content, model);

    }

}
