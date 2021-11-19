package jp.ne.paypay;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jp.ne.paypay.model.JwtRequestDto;
import jp.ne.paypay.model.JwtResponseDto;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.Date;

//test
// two
// three
public class JwtUtil {

    /**
     * Method to create JWT token
     *
     * @param jwtRequestDto  request object to create jwt token
     * @param apiKeySecret KeySecret to decode JWT token
     * @return String JWT token
     * @throws JwtException if fails to create JWT token
     */
    public static String createJwtToken(JwtRequestDto jwtRequestDto, String apiKeySecret) throws JwtException {
        Date expiryDate = jwtRequestDto.getExp() == null ? Date.from(LocalDateTime.now().plusMinutes(5L).toInstant(ZoneOffset.UTC))
                :jwtRequestDto.getExp();
        try{
            return JWT.create()
                    .withAudience(jwtRequestDto.getAud())
                    .withIssuer(jwtRequestDto.getIss())
                    .withExpiresAt(expiryDate)
                    .withClaim("scope", jwtRequestDto.getScope())
                    .withClaim("nonce", jwtRequestDto.getNonce())
                    .withClaim("redirectUrl", jwtRequestDto.getRedirectUrl())
                    .withClaim("referenceId", jwtRequestDto.getReferenceId())
                    .withClaim("deviceId", jwtRequestDto.getDeviceId())
                    .withClaim("phoneNumber", jwtRequestDto.getPhoneNumber())
                    .sign(Algorithm.HMAC256(Base64.getDecoder().decode(apiKeySecret)));
        }catch (Exception e){
            throw new JwtException("Error in creating JWT Token: "+e.getMessage());
        }
    }

    /**
     * Method to validate JWT token for user authorization services
     *
     * @param jwtToken JWT token to be verified for authenticity
     * @param apiKeySecret KeySecret to decode JWT token
     * @return JwtResponseDto object with token params
     * @throws JwtException if fails to parse JWT token
     */
    public static JwtResponseDto validateJWT(String jwtToken, String apiKeySecret) throws JwtException {
        try{
            Algorithm algorithm = Algorithm.HMAC256(Base64.getDecoder().decode(apiKeySecret));
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(jwtToken);
            return new JwtResponseDto(jwt.getClaim("referenceId").asString(), jwt.getClaim("userAuthorizationId").asString());
        }catch(Exception e){
            throw new JwtException("Error while verfying signature: "+e.getMessage());
        }

    }

}
