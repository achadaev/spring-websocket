package server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import shared.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private List<User> users;

    public UserServiceImpl() {
    }

    public User getUserByUsername(String username) {
        users = new ArrayList<>();
        users.add(new User("admin", "$2a$10$y.rA1N3L47FUxXwrgVRiuurtdTJ4oQKzovNGYiQAk0TpsdIBrdoVq", ""));
        users.add(new User("andrey", "$2a$10$fL/6gOm2ncpvIBZsr3MVReKV/n/bl.V/V1I3eAPCQdP5VQpTNv7Mu", ""));
        users.add(new User("nastiya", "$2a$10$fL/6gOm2ncpvIBZsr3MVReKV/n/bl.V/V1I3eAPCQdP5VQpTNv7Mu", ""));
        users.add(new User("stas", "$2a$10$fL/6gOm2ncpvIBZsr3MVReKV/n/bl.V/V1I3eAPCQdP5VQpTNv7Mu", ""));

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User addUser(User user) {
        users.remove(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        users.add(user);
        return user;
    }
}
