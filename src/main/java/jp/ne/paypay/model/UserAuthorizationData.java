package jp.ne.paypay.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * UserAuthorizationData
 */

public class UserAuthorizationData {

  @SerializedName("userAuthorizationId")
  private String userAuthorizationId = null;
  
  @SerializedName("referenceIds")
  private List referenceIds = null;
  
  /**
   * User authorization status
   */
  @JsonAdapter(StatusEnum.Adapter.class)
  public enum StatusEnum {
    
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }
    
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
    
    public static class Adapter extends TypeAdapter<StatusEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final StatusEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public StatusEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return StatusEnum.fromValue(String.valueOf(value));
      }
    }
  }
  
  @SerializedName("status")
  private StatusEnum status = null;
  
  /**
   * Gets or Sets scopes
   */
  @JsonAdapter(ScopesEnum.Adapter.class)
  public enum ScopesEnum {
    
    DIRECT_DEBIT("direct_debit"),
    SHOW_BALANCE("show_balance"),
    TOPUP("topup");

    private String value;

    ScopesEnum(String value) {
      this.value = value;
    }
    
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    
    public static ScopesEnum fromValue(String text) {
      for (ScopesEnum b : ScopesEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
    
    public static class Adapter extends TypeAdapter<ScopesEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final ScopesEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public ScopesEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return ScopesEnum.fromValue(String.valueOf(value));
      }
    }
  }
  
  @SerializedName("scopes")
  private List<ScopesEnum> scopes = null;
  
  @SerializedName("expireAt")
  private BigDecimal expireAt = null;
  
  @SerializedName("issuedAt")
  private BigDecimal issuedAt = null;
  
  public UserAuthorizationData userAuthorizationId(String userAuthorizationId) {
    this.userAuthorizationId = userAuthorizationId;
    return this;
  }

  
  /**
  * PayPay user reference id
  * @return userAuthorizationId
  **/
  @ApiModelProperty(value = "PayPay user reference id")
  public String getUserAuthorizationId() {
    return userAuthorizationId;
  }
  public void setUserAuthorizationId(String userAuthorizationId) {
    this.userAuthorizationId = userAuthorizationId;
  }
  
  public UserAuthorizationData referenceIds(List referenceIds) {
    this.referenceIds = referenceIds;
    return this;
  }

  
  /**
  * The id used to identify the user in merchant system
  * @return referenceIds
  **/
  @ApiModelProperty(value = "The id used to identify the user in merchant system")
  public List getReferenceIds() {
    return referenceIds;
  }
  public void setReferenceIds(List referenceIds) {
    this.referenceIds = referenceIds;
  }
  
  public UserAuthorizationData status(StatusEnum status) {
    this.status = status;
    return this;
  }

  
  /**
  * User authorization status
  * @return status
  **/
  @ApiModelProperty(value = "User authorization status")
  public StatusEnum getStatus() {
    return status;
  }
  public void setStatus(StatusEnum status) {
    this.status = status;
  }
  
  public UserAuthorizationData scopes(List<ScopesEnum> scopes) {
    this.scopes = scopes;
    return this;
  }

  public UserAuthorizationData addScopesItem(ScopesEnum scopesItem) {
    
    if (this.scopes == null) {
      this.scopes = new ArrayList<ScopesEnum>();
    }
    
    this.scopes.add(scopesItem);
    return this;
  }
  
  /**
  * Scopes of the user authorization
  * @return scopes
  **/
  @ApiModelProperty(value = "Scopes of the user authorization")
  public List<ScopesEnum> getScopes() {
    return scopes;
  }
  public void setScopes(List<ScopesEnum> scopes) {
    this.scopes = scopes;
  }
  
  public UserAuthorizationData expireAt(BigDecimal expireAt) {
    this.expireAt = expireAt;
    return this;
  }

  
  /**
  * Expiration time in the number of seconds since January 1, 1970, 00:00:00 GTM.
  * @return expireAt
  **/
  @ApiModelProperty(value = "Expiration time in the number of seconds since January 1, 1970, 00:00:00 GTM.")
  public BigDecimal getExpireAt() {
    return expireAt;
  }
  public void setExpireAt(BigDecimal expireAt) {
    this.expireAt = expireAt;
  }
  
  public UserAuthorizationData issuedAt(BigDecimal issuedAt) {
    this.issuedAt = issuedAt;
    return this;
  }

  
  /**
  * Issuing time in the number of seconds since January 1, 1970, 00:00:00 GTM.
  * @return issuedAt
  **/
  @ApiModelProperty(value = "Issuing time in the number of seconds since January 1, 1970, 00:00:00 GTM.")
  public BigDecimal getIssuedAt() {
    return issuedAt;
  }
  public void setIssuedAt(BigDecimal issuedAt) {
    this.issuedAt = issuedAt;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserAuthorizationData userAuthorizationData = (UserAuthorizationData) o;
    return Objects.equals(this.userAuthorizationId, userAuthorizationData.userAuthorizationId) &&
        Objects.equals(this.referenceIds, userAuthorizationData.referenceIds) &&
        Objects.equals(this.status, userAuthorizationData.status) &&
        Objects.equals(this.scopes, userAuthorizationData.scopes) &&
        Objects.equals(this.expireAt, userAuthorizationData.expireAt) &&
        Objects.equals(this.issuedAt, userAuthorizationData.issuedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userAuthorizationId, referenceIds, status, scopes, expireAt, issuedAt);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserAuthorizationData {\n");
    
    sb.append("    userAuthorizationId: ").append(toIndentedString(userAuthorizationId)).append("\n");
    sb.append("    referenceIds: ").append(toIndentedString(referenceIds)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    scopes: ").append(toIndentedString(scopes)).append("\n");
    sb.append("    expireAt: ").append(toIndentedString(expireAt)).append("\n");
    sb.append("    issuedAt: ").append(toIndentedString(issuedAt)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

  
}



