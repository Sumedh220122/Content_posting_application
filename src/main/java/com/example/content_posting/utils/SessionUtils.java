package com.example.content_posting.utils;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.example.content_posting.models.User;

public class SessionUtils {
    public static HttpSession getSession() {
        return (HttpSession) RequestContextHolder
                .currentRequestAttributes()
                .resolveReference(RequestAttributes.REFERENCE_SESSION);
    }

    public static User getCurrentUser() {
        HttpSession session = getSession();
        return (User) session.getAttribute("currentUser");
    }
}
