package in.college.service;

import in.college.exception.BadCredentialsException;
import in.college.model.User;
import in.college.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserRepository userRepository;
    private User user;
    private final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        logger.info("Getting user from email");

        Optional<User> userOptional = userRepository.findByEmail(username);


        user = userOptional.orElseThrow(() -> new BadCredentialsException("Bad Credentials"));

        logger.info("Got User: " + userOptional + "from database");

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), true
                , true, true, true,
                getAuthorities()
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities() {
        String ROLE_PREFIX = "ROLE_";

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(ROLE_PREFIX + user.getRole());

        return List.of(simpleGrantedAuthority);
    }
}