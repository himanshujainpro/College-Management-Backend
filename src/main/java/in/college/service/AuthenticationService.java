package in.college.service;

import in.college.dto.AuthenticationResponse;
import in.college.dto.LoginRequest;
import in.college.util.JwtProvider;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    public AuthenticationResponse login(LoginRequest loginRequest) {

        Authentication authenticate = authenticationManager
                .authenticate(new
                        UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        logger.info("Inside login service");
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);
        logger.info("Jwt Token Generated " + authenticationToken);
        return new AuthenticationResponse(authenticationToken, loginRequest.getEmail());
    }

}
