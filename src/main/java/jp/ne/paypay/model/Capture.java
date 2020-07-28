package jp.ne.paypay.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * Capture
 */
public class Capture {

  @SerializedName("merchantCaptureId")
  private String merchantCaptureId = null;
  
  @SerializedName("amount")
  private MoneyAmount amount = null;
  
  @SerializedName("orderDescription")
  private String orderDescription = null;
  
  @SerializedName("requestedAt")
  private Long requestedAt = null;
  
  @SerializedName("expiresAt")
  private Integer expiresAt = null;
  
  /**
   * Status is always USER_REQUESTED.
   */
  @JsonAdapter(StatusEnum.Adapter.class)
  public enum StatusEnum {
    
    USER_REQUESTED("USER_REQUESTED"),
    COMPLETED("COMPLETED");

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
  
  @SerializedName("amountescription")
  private String amountescription = null;
  
  public Capture merchantCaptureId(String merchantCaptureId) {
    this.merchantCaptureId = merchantCaptureId;
    return this;
  }

  
  /**
  * Get merchantCaptureId
  * @return merchantCaptureId
  **/
  @ApiModelProperty(value = "")
  public String getMerchantCaptureId() {
    return merchantCaptureId;
  }
  public void setMerchantCaptureId(String merchantCaptureId) {
    this.merchantCaptureId = merchantCaptureId;
  }
  
  public Capture amount(MoneyAmount amount) {
    this.amount = amount;
    return this;
  }

  
  /**
  * Get amount
  * @return amount
  **/
  @ApiModelProperty(value = "")
  public MoneyAmount getAmount() {
    return amount;
  }
  public void setAmount(MoneyAmount amount) {
    this.amount = amount;
  }
  
  public Capture orderDescription(String orderDescription) {
    this.orderDescription = orderDescription;
    return this;
  }

  
  /**
  * Description for Capture
  * @return orderDescription
  **/
  @ApiModelProperty(value = "Description for Capture")
  public String getOrderDescription() {
    return orderDescription;
  }
  public void setOrderDescription(String orderDescription) {
    this.orderDescription = orderDescription;
  }
  
  public Capture requestedAt(Long requestedAt) {
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
  
  public Capture expiresAt(Integer expiresAt) {
    this.expiresAt = expiresAt;
    return this;
  }

  
  /**
  * Epoch timestamp in seconds, expiry duration must be less then the expiry granted to the merchant
  * @return expiresAt
  **/
  @ApiModelProperty(value = "Epoch timestamp in seconds, expiry duration must be less then the expiry granted to the merchant")
  public Integer getExpiresAt() {
    return expiresAt;
  }
  public void setExpiresAt(Integer expiresAt) {
    this.expiresAt = expiresAt;
  }
  
  public Capture status(StatusEnum status) {
    this.status = status;
    return this;
  }

  
  /**
  * Status is always USER_REQUESTED.
  * @return status
  **/
  @ApiModelProperty(value = "Status is always USER_REQUESTED.")
  public StatusEnum getStatus() {
    return status;
  }
  public void setStatus(StatusEnum status) {
    this.status = status;
  }
  
  public Capture acceptedAt(Integer acceptedAt) {
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
  
  public Capture amountescription(String amountescription) {
    this.amountescription = amountescription;
    return this;
  }

  
  /**
  * Description for Capture
  * @return amountescription
  **/
  @ApiModelProperty(value = "Description for Capture")
  public String getAmountescription() {
    return amountescription;
  }
  public void setAmountescription(String amountescription) {
    this.amountescription = amountescription;
  }
  
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Capture capture = (Capture) o;
    return Objects.equals(this.merchantCaptureId, capture.merchantCaptureId) &&
        Objects.equals(this.amount, capture.amount) &&
        Objects.equals(this.orderDescription, capture.orderDescription) &&
        Objects.equals(this.requestedAt, capture.requestedAt) &&
        Objects.equals(this.expiresAt, capture.expiresAt) &&
        Objects.equals(this.status, capture.status) &&
        Objects.equals(this.acceptedAt, capture.acceptedAt) &&
        Objects.equals(this.amountescription, capture.amountescription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(merchantCaptureId, amount, orderDescription, requestedAt, expiresAt, status, acceptedAt, amountescription);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Capture {\n");
    
    sb.append("    merchantCaptureId: ").append(toIndentedString(merchantCaptureId)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    orderDescription: ").append(toIndentedString(orderDescription)).append("\n");
    sb.append("    requestedAt: ").append(toIndentedString(requestedAt)).append("\n");
    sb.append("    expiresAt: ").append(toIndentedString(expiresAt)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    acceptedAt: ").append(toIndentedString(acceptedAt)).append("\n");
    sb.append("    amountescription: ").append(toIndentedString(amountescription)).append("\n");
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



