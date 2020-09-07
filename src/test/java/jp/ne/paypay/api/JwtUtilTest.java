package jp.ne.paypay.api;

import com.auth0.jwt.JWTVerifier;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.JwtException;
import jp.ne.paypay.JwtUtil;
import jp.ne.paypay.Pair;
import jp.ne.paypay.auth.HmacAuth;
import jp.ne.paypay.model.JwtRequestDto;
import jp.ne.paypay.model.JwtResponseDto;
import jp.ne.paypay.model.QRCode;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class JwtUtilTest {

    @Test
    public void createJwtTokenTest() throws JwtException {
        String apiKeySecret = "secret";
        JwtRequestDto jwtRequestDto = new JwtRequestDto();
        jwtRequestDto.setAud("jp.ne.paypay");
        jwtRequestDto.setDeviceId("deviceId");
        jwtRequestDto.setExp(Date.from(LocalDateTime.now().plusMinutes(5L).toInstant(ZoneOffset.UTC)));
        jwtRequestDto.setIss("paypay");
        jwtRequestDto.setNonce(RandomStringUtils.randomAlphanumeric(8).toLowerCase());
        jwtRequestDto.setPhoneNumber("9999999999");
        jwtRequestDto.setRedirectUrl("http://paypay/orderpayment");
        jwtRequestDto.setScope("ORDER_QR");
        jwtRequestDto.setReferenceId("referenceId");
        String jwtToken = JwtUtil.createJwtToken(jwtRequestDto, apiKeySecret);
        Assertions.assertNotNull(jwtToken);
        JwtResponseDto jwtResponseDto = JwtUtil.validateJWT(jwtToken, jwtRequestDto.getAud(), apiKeySecret);
        Assertions.assertNotNull(jwtResponseDto);
        Assertions.assertEquals(jwtResponseDto.getReferenceId(), jwtRequestDto.getReferenceId());

    }

    @Test
    public void createJwtTokenExceptionTest() {
        Assert.assertThrows(JwtException.class, ()->JwtUtil.createJwtToken(new JwtRequestDto(), null));
    }

    @Test
    public void createJwtTokenInvalidTest() throws JwtException {
        String apiKeySecret = "secret";
        JwtRequestDto jwtRequestDto = new JwtRequestDto();
        jwtRequestDto.setAud("jp.ne.paypay");
        jwtRequestDto.setDeviceId("deviceId");
        jwtRequestDto.setExp(Date.from(LocalDateTime.now().plusMinutes(5L).toInstant(ZoneOffset.UTC)));
        jwtRequestDto.setIss("paypay");
        jwtRequestDto.setNonce(RandomStringUtils.randomAlphanumeric(8).toLowerCase());
        jwtRequestDto.setPhoneNumber("9999999999");
        jwtRequestDto.setRedirectUrl("http://paypay/orderpayment");
        jwtRequestDto.setScope("ORDER_QR");
        jwtRequestDto.setReferenceId("referenceId");
        String jwtToken = JwtUtil.createJwtToken(jwtRequestDto, apiKeySecret);
        Assertions.assertNotNull(jwtToken);
        Assert.assertThrows(JwtException.class, () -> JwtUtil.validateJWT(jwtToken+"invalid", jwtRequestDto.getAud(), apiKeySecret));

    }
    @Test
    public void jwtExceptioinTest(){
        JwtException jwtException = new JwtException();
        Assertions.assertEquals(jwtException.getCode(), 0);
        jwtException = new JwtException(100, "Exception");
        Assertions.assertEquals(jwtException.getCode(), 100);
        jwtException = new JwtException(new Throwable("throwable"));
        Assertions.assertEquals(jwtException.getCause().getMessage(), "throwable");
    }
}
