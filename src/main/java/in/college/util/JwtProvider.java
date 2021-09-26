package in.college.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.security.Key;

import static io.jsonwebtoken.Jwts.parser;


@Service
public class JwtProvider {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    public String generateToken(Authentication authentication) {
        logger.info("Generating jwt token");
        User principal = (User) authentication.getPrincipal();
        logger.info("jwt token generated");
        return Jwts.builder().setSubject(principal.getUsername())
                .signWith(key).compact();

    }

    @SuppressWarnings("deprecation")
    public String getUsernameFromJWT(String token) {
        logger.info("Getting user from jwt");
        Claims claims = parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        logger.info("Got email from jwt" + claims.getSubject());
        return claims.getSubject();
    }

    @SuppressWarnings("deprecation")
    public boolean validateToken(String jwt) {
        logger.info("Validating token");
        parser().setSigningKey(key).parseClaimsJws(jwt);
        logger.info("token validated");
        return true;
    }


}
