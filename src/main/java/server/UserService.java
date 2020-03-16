package server;

import shared.User;

public interface UserService {
    User getUserByUsername(String username);
    User addUser(User user);
}
