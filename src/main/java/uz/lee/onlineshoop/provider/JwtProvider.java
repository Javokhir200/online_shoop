package uz.lee.onlineshoop.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uz.lee.onlineshoop.entity.RoleEntity;
import uz.lee.onlineshoop.entity.UserEntity;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.token.expiration.mills}")
    private Long expiration;

    @Value("${jwt.token.secret.key}")
    private String secret;

    private SecretKey key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(UserEntity user) {
        RoleEntity role = user.getRole();
        String roleName = role != null ? role.getName() : "";

        return Jwts.builder()
//                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .claim("id", user.getId())
                .claim("email", user.getEmail())
                .claim("role", roleName)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
    }


    public boolean validateToken(String token) {
        try {
            final Claims claims = parseClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

}
