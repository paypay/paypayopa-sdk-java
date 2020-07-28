package com.paypay.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * RevertAuthResponseData
 */

public class RevertAuthResponseData {

  /**
   * Gets or Sets status
   */
  @JsonAdapter(StatusEnum.Adapter.class)
  public enum StatusEnum {
    
    CANCELED("CANCELED");

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
  
  @SerializedName("acceptedAt")
  private Integer acceptedAt = null;
  
  @SerializedName("paymentId")
  private String paymentId = null;
  
  @SerializedName("requestedAt")
  private Long requestedAt = null;
  
  @SerializedName("reason")
  private String reason = null;
  
  public RevertAuthResponseData status(StatusEnum status) {
    this.status = status;
    return this;
  }

  
  /**
  * Get status
  * @return status
  **/
  @ApiModelProperty(value = "")
  public StatusEnum getStatus() {
    return status;
  }
  public void setStatus(StatusEnum status) {
    this.status = status;
  }
  
  public RevertAuthResponseData acceptedAt(Integer acceptedAt) {
    this.acceptedAt = acceptedAt;
    return this;
  }

  
  /**
  * Get acceptedAt
  * @return acceptedAt
  **/
  @ApiModelProperty(value = "")
  public Integer getAcceptedAt() {
    return acceptedAt;
  }
  public void setAcceptedAt(Integer acceptedAt) {
    this.acceptedAt = acceptedAt;
  }
  
  public RevertAuthResponseData paymentId(String paymentId) {
    this.paymentId = paymentId;
    return this;
  }

  
  /**
  * Get paymentId
  * @return paymentId
  **/
  @ApiModelProperty(value = "")
  public String getPaymentId() {
    return paymentId;
  }
  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }
  
  public RevertAuthResponseData requestedAt(Long requestedAt) {
    this.requestedAt = requestedAt;
    return this;
  }

  
  /**
  * Get requestedAt
  * @return requestedAt
  **/
  @ApiModelProperty(value = "")
  public Long getRequestedAt() {
    return requestedAt;
  }
  public void setRequestedAt(Long requestedAt) {
    this.requestedAt = requestedAt;
  }
  
  public RevertAuthResponseData reason(String reason) {
    this.reason = reason;
    return this;
  }

  
  /**
  * Get reason
  * @return reason
  **/
  @ApiModelProperty(value = "")
  public String getReason() {
    return reason;
  }
  public void setReason(String reason) {
    this.reason = reason;
  }
  
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RevertAuthResponseData revertAuthResponseData = (RevertAuthResponseData) o;
    return Objects.equals(this.status, revertAuthResponseData.status) &&
        Objects.equals(this.acceptedAt, revertAuthResponseData.acceptedAt) &&
        Objects.equals(this.paymentId, revertAuthResponseData.paymentId) &&
        Objects.equals(this.requestedAt, revertAuthResponseData.requestedAt) &&
        Objects.equals(this.reason, revertAuthResponseData.reason);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, acceptedAt, paymentId, requestedAt, reason);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RevertAuthResponseInfo {\n");
    
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    acceptedAt: ").append(toIndentedString(acceptedAt)).append("\n");
    sb.append("    paymentId: ").append(toIndentedString(paymentId)).append("\n");
    sb.append("    requestedAt: ").append(toIndentedString(requestedAt)).append("\n");
    sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

  
}



