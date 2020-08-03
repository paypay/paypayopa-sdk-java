package jp.ne.paypay.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * CaptureObject
 */

public class CaptureObject {

  @SerializedName("merchantPaymentId")
  @NotEmpty(message = "merchantPaymentId is required")
  @Size(max = 64, message = "maximum 64 characters are allowed for merchantPaymentId")
  private String merchantPaymentId = null;
  
  @SerializedName("amount")
  @NotNull(message = "amount is required")
  private MoneyAmount amount = null;
  
  @SerializedName("merchantCaptureId")
  @NotEmpty(message = "merchantCaptureId is required")
  @Size(max = 64, message = "maximum 64 characters are allowed for merchantCaptureId")
  private String merchantCaptureId = null;
  
  @SerializedName("requestedAt")
  @NotNull(message = "requestedAt is required")
  private Long requestedAt = null;
  
  @SerializedName("orderDescription")
  @NotEmpty(message = "orderDescription is required")
  @Size(max =255 ,message = "maximum 255 characters allowed for orderDescription")
  private String orderDescription = null;
  
  public CaptureObject merchantPaymentId(String merchantPaymentId) {
    this.merchantPaymentId = merchantPaymentId;
    return this;
  }

  
  /**
  * Get merchantPaymentId
  * @return merchantPaymentId
  **/
  @ApiModelProperty(value = "")
  public String getMerchantPaymentId() {
    return merchantPaymentId;
  }
  public void setMerchantPaymentId(String merchantPaymentId) {
    this.merchantPaymentId = merchantPaymentId;
  }
  
  public CaptureObject amount(MoneyAmount amount) {
    this.amount = amount;
    return this;
  }

  
  /**
  * Get amount
  * @return amount
  **/
  @ApiModelProperty(required = true, value = "")
  public MoneyAmount getAmount() {
    return amount;
  }
  public void setAmount(MoneyAmount amount) {
    this.amount = amount;
  }
  
  public CaptureObject merchantCaptureId(String merchantCaptureId) {
    this.merchantCaptureId = merchantCaptureId;
    return this;
  }

  
  /**
  * Get merchantCaptureId
  * @return merchantCaptureId
  **/
  @ApiModelProperty(required = true, value = "")
  public String getMerchantCaptureId() {
    return merchantCaptureId;
  }
  public void setMerchantCaptureId(String merchantCaptureId) {
    this.merchantCaptureId = merchantCaptureId;
  }
  
  public CaptureObject requestedAt(Long requestedAt) {
    this.requestedAt = requestedAt;
    return this;
  }

  
  /**
  * Get requestedAt
  * @return requestedAt
  **/
  @ApiModelProperty(required = true, value = "")
  public Long getRequestedAt() {
    return requestedAt;
  }
  public void setRequestedAt(Long requestedAt) {
    this.requestedAt = requestedAt;
  }
  
  public CaptureObject orderDescription(String orderDescription) {
    this.orderDescription = orderDescription;
    return this;
  }

  
  /**
  * Description for Capture
  * @return orderDescription
  **/
  @ApiModelProperty(required = true, value = "Description for Capture")
  public String getOrderDescription() {
    return orderDescription;
  }
  public void setOrderDescription(String orderDescription) {
    this.orderDescription = orderDescription;
  }
  
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CaptureObject captureObject = (CaptureObject) o;
    return Objects.equals(this.merchantPaymentId, captureObject.merchantPaymentId) &&
        Objects.equals(this.amount, captureObject.amount) &&
        Objects.equals(this.merchantCaptureId, captureObject.merchantCaptureId) &&
        Objects.equals(this.requestedAt, captureObject.requestedAt) &&
        Objects.equals(this.orderDescription, captureObject.orderDescription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(merchantPaymentId, amount, merchantCaptureId, requestedAt, orderDescription);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CaptureObject {\n");
    
    sb.append("    merchantPaymentId: ").append(toIndentedString(merchantPaymentId)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    merchantCaptureId: ").append(toIndentedString(merchantCaptureId)).append("\n");
    sb.append("    requestedAt: ").append(toIndentedString(requestedAt)).append("\n");
    sb.append("    orderDescription: ").append(toIndentedString(orderDescription)).append("\n");
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



