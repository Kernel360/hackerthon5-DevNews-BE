package org.example.devnews.config.security;

import org.example.devnews.domain.user.User;
import org.example.devnews.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userPS =  userRepository.findByUsername(username).orElseThrow(
                () -> new InternalAuthenticationServiceException("Username " + username + " not found")
        );
        return new LoginUser(userPS);
    }
}
