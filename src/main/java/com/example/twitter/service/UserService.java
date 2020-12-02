package com.example.twitter.service;

import com.example.twitter.domain.Role;
import com.example.twitter.domain.User;
import com.example.twitter.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    MailSender mailSender;

    public boolean activateUser(String code) {

        User user = userRepo.findByActivationCode(code);

        if (user == null){
            return false;
        }

        user.setActivationCode(null);
        userRepo.save(user);

        return true;
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepo.findByUsername(userName);
    }

    public boolean addUser(User user){
        User userFromDB = userRepo.findByUsername(user.getUsername());

        if (userFromDB != null){
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());

        userRepo.save(user);

        if (!StringUtils.isEmpty(user.getEmail())){

            String message = String.format(
              "Hello, %s! \n" +
                      "Welcome to Twitter. Please, visit next link: http://localhost:8081/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );


            mailSender.send(user.getEmail(), "Activation code", message);
        }

        return true;
    }
}
