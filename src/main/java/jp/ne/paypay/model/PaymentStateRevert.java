package jp.ne.paypay.model;

import com.google.gson.annotations.SerializedName;

import java.time.Instant;
import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * PaymentStateRevert
 */

public class PaymentStateRevert {

  @SerializedName("acceptedAt")
  private Integer acceptedAt = null;
  
  @SerializedName("merchantRevertId")
  private String merchantRevertId = null;

  @SerializedName("paymentId")
  private String paymentId = null;
  
  @SerializedName("requestedAt")
  private Long requestedAt = Instant.now().getEpochSecond();
  
  @SerializedName("reason")
  private String reason = null;
  
  public PaymentStateRevert acceptedAt(Integer acceptedAt) {
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
  
  public PaymentStateRevert merchantRevertId(String merchantRevertId) {
    this.merchantRevertId = merchantRevertId;
    return this;
  }

  
  /**
  * Get merchantRevertId
  * @return merchantRevertId
  **/
  @ApiModelProperty(value = "")
  public String getMerchantRevertId() {
    return merchantRevertId;
  }
  public void setMerchantRevertId(String merchantRevertId) {
    this.merchantRevertId = merchantRevertId;
  }
  
  public PaymentStateRevert requestedAt(Long requestedAt) {
    this.requestedAt = requestedAt;
    return this;
  }


  /**
   * Get payment ID
   * @return paymentId
   **/
  @ApiModelProperty(value = "")
  public String getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
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
  
  public PaymentStateRevert reason(String reason) {
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentStateRevert paymentStateRevert = (PaymentStateRevert) o;
    return Objects.equals(this.acceptedAt, paymentStateRevert.acceptedAt) &&
        Objects.equals(this.merchantRevertId, paymentStateRevert.merchantRevertId) &&
        Objects.equals(this.requestedAt, paymentStateRevert.requestedAt) &&
        Objects.equals(this.reason, paymentStateRevert.reason);
  }

  @Override
  public int hashCode() {
    return Objects.hash(acceptedAt, merchantRevertId, requestedAt, reason);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentStateRevert {\n");
    
    sb.append("    acceptedAt: ").append(toIndentedString(acceptedAt)).append("\n");
    sb.append("    merchantRevertId: ").append(toIndentedString(merchantRevertId)).append("\n");
    sb.append("    requestedAt: ").append(toIndentedString(requestedAt)).append("\n");
    sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
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



