package autenticacion;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;


public class Tokenizer {

    public String generateToken() {
        Algorithm algorithm = Algorithm.HMAC256("clavesecreta");
        return JWT.create().withIssuer("marioYandrea").sign(algorithm);
    }

    public boolean comprobarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("clavesecreta");
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("marioYandrea").build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }

    }
}

