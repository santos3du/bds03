package br.com.eduardo.bds03.services;

import br.com.eduardo.bds03.entities.User;
import br.com.eduardo.bds03.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByEmail(username);
        logger.info(user.getEmail());
        if(user == null){
            logger.error("User not found: "+ username);
            throw new UsernameNotFoundException("Email not found");
        }
        logger.info("User get log in: " + user.getEmail());
        return  user;

    }
}
