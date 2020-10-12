package jp.ne.paypay.auth;

import jp.ne.paypay.Pair;
import org.apache.commons.lang3.RandomStringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class HmacAuth implements Authentication {
    private String apiKey;
    private String apiSecretKey;
    private String requestUrl;
    private String requestBody;
    private String httpMethod;
    private String contentType;
    private static final String HMAC_KEY = "HmacSHA256";
    private static final String AUTH_HEADER_PREFIX = "hmac OPA-Auth:";
    private static final String AUTH_HEADER_DELIMITER = ":";
    private static final String HASH_METHOD = "MD5";
    private static final String EMPTY = "empty";

    private String getHashKey() throws NoSuchAlgorithmException {
        String hash = EMPTY;
        if(requestBody != null){
            MessageDigest md = MessageDigest.getInstance(HASH_METHOD);
            md.update(this.contentType.getBytes(StandardCharsets.UTF_8));
            md.update(requestBody.getBytes(StandardCharsets.UTF_8));
             hash = new String(
                    Base64.getEncoder().encode(md.digest()),
                    StandardCharsets.UTF_8);
        }
        return hash;
    }

    private  String hmacAuthHeader() throws NoSuchAlgorithmException {
        String nonce = RandomStringUtils.randomAlphanumeric(8).toLowerCase();
        long epoch = Instant.now().getEpochSecond();
        String hash = getHashKey();
        String macData = toBase64HmacString(this.apiSecretKey, nonce, epoch, hash);

        return AUTH_HEADER_PREFIX + this.apiKey + AUTH_HEADER_DELIMITER + macData + AUTH_HEADER_DELIMITER + nonce +
                        AUTH_HEADER_DELIMITER + epoch + AUTH_HEADER_DELIMITER + hash;
    }
    private String toBase64HmacString(String apiKeySecret, String nonce, long epoch, String hash) {
        byte[] dataToSign = getHmacData(nonce, epoch, hash); //Output from step 2
        try {
            SecretKeySpec signingKey = new SecretKeySpec(apiKeySecret.getBytes(StandardCharsets.UTF_8),
                    HMAC_KEY);
            Mac sha256HMAC = Mac.getInstance(HMAC_KEY);
            sha256HMAC.init(signingKey);
            byte[] rawHmac = sha256HMAC.doFinal(dataToSign);
            return Base64.getEncoder().encodeToString(rawHmac);
        } catch (GeneralSecurityException e) {
            System.err.println("Unexpected error while creating hash: " + e.getMessage());
            throw new IllegalArgumentException(e);
        }
    }
    private  byte[]  getHmacData(String nonce, long epoch, String hash){
        String localContentType = EMPTY;
        if(requestBody != null){
            localContentType = this.contentType;
        }
        String  DELIMITER = "\n";
        return (this.requestUrl + DELIMITER + this.httpMethod + DELIMITER + nonce + DELIMITER + epoch + DELIMITER
                + localContentType + DELIMITER + hash)
                .getBytes(StandardCharsets.UTF_8);
    }
    @Override
    public void applyToParams(List<Pair> queryParams, Map<String, String> headerParams) {
        if (apiKey == null ||  apiSecretKey == null) {
            return;
        }
        try{
            String hmacAuth = hmacAuthHeader();
            headerParams.put("Authorization", hmacAuth);
        }catch (Exception e){
            System.err.println("Error in getting Authorization: "+e);
        }

    }

    public String getApiKey() {
        return apiKey;
    }

    public HmacAuth setApiKey(final String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public String getApiSecretKey() {
        return apiSecretKey;
    }

    public HmacAuth setApiSecretKey(final String apiSecretKey) {
        this.apiSecretKey = apiSecretKey;
        return this;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public HmacAuth setRequestUrl(final String requestUrl) {
        this.requestUrl = requestUrl;
        return this;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public HmacAuth setRequestBody(final String requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public HmacAuth setHttpMethod(final String httpMethod) {
        this.httpMethod = httpMethod;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public HmacAuth setContentType(final String contentType) {
        this.contentType = contentType;
        return this;
    }
}
