package jp.ne.paypay.example;

import jp.ne.paypay.JwtException;
import jp.ne.paypay.JwtUtil;
import jp.ne.paypay.model.JwtRequestDto;
import jp.ne.paypay.model.JwtResponseDto;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;

public class JwtUtilExample {


  public static void main(String[] args) {
    String apiKeySecret = "L24NeSrLDnpxEKq6z4y1QTuhl1/j4RdYZsLeN6cZ";
    createJwtToken(apiKeySecret);
    String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJwYXlwYXkubmUuanAiLCJyZWRpcmVjdFVybCI6Imh0dHA6Ly9zdGctbWVyY2hhbnQuZG9tYWluL3dlYmhvb2svcyIsInBob25lTnVtYmVyIjoiOTUzOTUzOTA5MiIsInNjb3BlIjoiZGlyZWN0X2RlYml0IiwiaXNzIjoiTWVyY2hhbnQiLCJleHAiOjE1OTM4MTI3MjksIm5vbmNlIjoiOTE5Mjk3OTMiLCJkZXZpY2VJZCI6InRlc3QgZGV2aWNlIiwicmVmZXJlbmNlSWQiOiJlZmVlZWIxNi03YWJlLTQyZDEtOTNhMC1lNzQwMGMzYWExY2EifQ.JYvb-DL3m-rz4hx6TRcCmCMN1PydR1b3BOLmUKI_3I8";
    validateJwt(jwt, apiKeySecret);


  }

  private static void validateJwt(String jwt1, String apiSecretKey1) {

    JwtResponseDto jwtResponseDto = null;
    try {
      String jwtAudience = "paypay.ne.jp";
      String jwtToken = "JWT_TOKEN";
      String apiSecretKey = "YOUR_API_SECRET_KEY";
      jwtResponseDto = JwtUtil.validateJWT(jwtToken, jwtAudience, apiSecretKey);
    } catch (JwtException e) {
      e.printStackTrace();
      System.err.println("Exception:"+e);
    }
    System.out.println(jwtResponseDto);
  }

  private static void createJwtToken(String apiKeySecret) {
    try{
      JwtRequestDto jwtRequestDto = new JwtRequestDto();
      String nonce = RandomStringUtils.randomNumeric(8);
      System.out.println(nonce);
      jwtRequestDto.setAud("paypay.ne.jp")
              .setIss("Merchant")
              .setExp(Date.from(LocalDateTime.now().plusMinutes(5L).toInstant(ZoneOffset.UTC)))
              .setNonce(nonce)
              .setScope("direct_debit")
              .setRedirectUrl("http://stg-merchant.domain/webhook/s")
              .setReferenceId(UUID.randomUUID().toString())
              .setDeviceId("test device")
              .setPhoneNumber("9539539092");

      String jwtToken = JwtUtil.createJwtToken(jwtRequestDto, apiKeySecret);
      System.out.println(jwtToken);
    }catch(Exception e){
      System.out.println(e);
    }
  }

}
