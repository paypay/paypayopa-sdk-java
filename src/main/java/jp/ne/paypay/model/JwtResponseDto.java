package jp.ne.paypay.model;

public class JwtResponseDto {

    private String referenceId;
    private String userAuthorizationId;

    public JwtResponseDto() {
    }
    public JwtResponseDto(String referenceId, String userAuthorizationId) {
        this.referenceId = referenceId;
        this.userAuthorizationId = userAuthorizationId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public JwtResponseDto setReferenceId(String referenceId) {
        this.referenceId = referenceId;
        return this;
    }

    public String getUserAuthorizationId() {
        return userAuthorizationId;
    }

    public JwtResponseDto setUserAuthorizationId(String userAuthorizationId) {
        this.userAuthorizationId = userAuthorizationId;
        return this;
    }

    @Override
    public String toString() {
        return "JwtResponseDto{" +
                "referenceId='" + referenceId + '\'' +
                ", userAuthorizationId='" + userAuthorizationId + '\'' +
                '}';
    }
}
