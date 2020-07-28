package jp.ne.paypay.model;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Date;

public class JwtRequestDto {
    private String aud;
    private String iss;
    private Date exp;
    private String scope;
    private String nonce;
    private String redirectUrl;
    private String referenceId;
    private String deviceId;
    private String phoneNumber;


    public String getAud() {
        return aud;
    }

    public JwtRequestDto setAud(String aud) {
        this.aud = aud;
        return this;
    }

    public String getIss() {
        return iss;
    }

    public JwtRequestDto setIss(String iss) {
        this.iss = iss;
        return this;
    }

    public Date getExp() {
        return exp;
    }

    public JwtRequestDto setExp(Date exp) {
        this.exp = exp;
        return this;
    }

    public String getScope() {
        return scope;
    }

    public JwtRequestDto setScope(String scope) {
        this.scope = scope;
        return this;
    }

    public String getNonce() {
        if(nonce == null){
            return RandomStringUtils.random(10);
        }
        return nonce;
    }

    public JwtRequestDto setNonce(String nonce) {
        this.nonce = nonce;
        return this;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public JwtRequestDto setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
        return this;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public JwtRequestDto setReferenceId(String referenceId) {
        this.referenceId = referenceId;
        return this;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public JwtRequestDto setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public JwtRequestDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
