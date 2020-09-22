package jp.ne.paypay.api;

import jp.ne.paypay.Pair;
import jp.ne.paypay.auth.HmacAuth;
import jp.ne.paypay.model.QRCode;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class HmacAuthTest {

    @Test
    public void hmacAuthTest(){
        HmacAuth hmacAuth = new HmacAuth();
        hmacAuth.setApiKey("API_KEY");
        hmacAuth.setApiSecretKey("SECRET_KEY");
        hmacAuth.setContentType("Application/Json");
        hmacAuth.setHttpMethod("POST");
        hmacAuth.setRequestUrl("/v2/api/test");
        QRCode qrCode = new QRCode();
        qrCode.setMerchantPaymentId(UUID.randomUUID().toString());
        hmacAuth.setRequestBody(qrCode.toString());
        Map<String, String> headerParams = new HashMap<>();
        List<Pair> queryParams = new ArrayList<>();
        hmacAuth.applyToParams(queryParams, headerParams);
        System.out.println(headerParams.get("Authorization"));
        Assert.assertTrue(headerParams.get("Authorization").startsWith("hmac"));
        Assert.assertNotNull(hmacAuth.getApiKey());
        Assert.assertNotNull(hmacAuth.getApiSecretKey());
        Assert.assertNotNull(hmacAuth.getContentType());
        Assert.assertNotNull(hmacAuth.getHttpMethod());
        Assert.assertNotNull(hmacAuth.getRequestBody());
        Assert.assertNotNull(hmacAuth.getRequestUrl());

    }
}
