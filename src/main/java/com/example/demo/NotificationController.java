package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NotificationController {

    private final UserService userService;
    private final NotificationService notificationService;

    @Autowired
    public NotificationController(UserService userService, NotificationService notificationService) {
        this.userService = userService;
        this.notificationService = notificationService;
    }

    
    @PostMapping("/notification")
    public String sendmsg(@RequestParam("username") String username, Model model){
        model.addAttribute("username", username);
        return "sendmessage";
    }

    @PostMapping("/notificationhandler")
    public String handlemsg(@RequestParam("username") String username,
                            @RequestParam("recipient") String recipient,
                            @RequestParam("message") String message, Model model)
    {
        int recipientid = userService.getUserId(recipient);
        
        notificationService.sendMessage(recipientid, username, recipient, message);


        model.addAttribute("username", username);

        return "homepage";
    }

    @PostMapping("/viewmsgs")
    public String seemsgs(@RequestParam("username") String username, Model model){
        int id = userService.getUserId(username);

        List<List<String>> msgs = notificationService.getMessages(id);
        
        model.addAttribute("username", username);
        model.addAttribute("Messages", msgs);

        return "displaymsg";
    }

}
