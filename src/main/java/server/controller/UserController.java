package server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import server.UserService;
import shared.User;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;
import java.util.UUID;

@CrossOrigin(origins = "http://34.89.232.247:8080/angular", allowedHeaders = "*")
@RestController
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public User getUser(Principal principal) {
        return userService.getUserByUsername(principal.getName());
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        User temp = userService.getUserByUsername(user.getUsername());
        if (temp != null) {
            temp.setToken(UUID.randomUUID().toString());
            return temp;
        } else {
            throw new UsernameNotFoundException("Username not found");
        }
    }

}
