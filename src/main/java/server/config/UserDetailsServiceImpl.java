package server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import server.UserService;
import shared.User;

import java.util.ArrayList;
import java.util.Collection;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    private final static Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            String password = user.getPassword();
            logger.info("Username: " + user.getUsername());
            logger.info("Password: " + password);

            Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            org.springframework.security.core.userdetails.User securedUser
                    = new org.springframework.security.core.userdetails.User(
                    username, password, true, true,
                    true, true, authorities);
            return securedUser;
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
