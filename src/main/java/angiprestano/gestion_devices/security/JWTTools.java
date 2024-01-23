package angiprestano.gestion_devices.security;

import angiprestano.gestion_devices.entities.User;
import angiprestano.gestion_devices.exceptions.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class JWTTools {
    @Value("${spring.jwt.secret}")
    private String secret;

    public String CreateToken(User user) {
            return Jwts.builder().subject(String.valueOf(user.getId()))// subject: A chi appartiene il token (id dell'utente)
                    .issuedAt(new Date(System.currentTimeMillis()))  // Data di emissione (IAT - Issued At)
                    .expiration(new Date(System.currentTimeMillis()+1000*60*60*24*7))// Data di scadenza del token (Expiration Date)
                    .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .compact(); //firmo il token con il nostro secret
        }
        public void verifyToken(String token) {
            try {
                Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
            } catch (Exception ex) {
                throw new UnauthorizedException("Problem with token! Please, repeat gain the login!");
            }
        }
        public String extractIdFromToken(String token) {
            return Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build()
                    .parseSignedClaims(token).getPayload().getSubject();
        }
    }
