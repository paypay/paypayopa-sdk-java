package jp.ne.paypay.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * RefundOrder
 */

public class RefundOrder {

  @SerializedName("merchantRefundId")
  private String merchantRefundId = null;
  
  @SerializedName("paymentId")
  private String paymentId = null;
  
  @SerializedName("amount")
  private MoneyAmount amount = null;
  
  @SerializedName("requestedAt")
  private Long requestedAt = null;
  
  @SerializedName("reason")
  private String reason = null;
  
  @SerializedName("assumeMerchant")
  private String assumeMerchant = null;
  
  public RefundOrder merchantRefundId(String merchantRefundId) {
    this.merchantRefundId = merchantRefundId;
    return this;
  }

  
  /**
  * Get merchantRefundId
  * @return merchantRefundId
  **/
  @ApiModelProperty(value = "")
  public String getMerchantRefundId() {
    return merchantRefundId;
  }
  public void setMerchantRefundId(String merchantRefundId) {
    this.merchantRefundId = merchantRefundId;
  }
  
  public RefundOrder paymentId(String paymentId) {
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
  
  public RefundOrder amount(MoneyAmount amount) {
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
  
  public RefundOrder requestedAt(Long requestedAt) {
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
  
  public RefundOrder reason(String reason) {
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
  
  public RefundOrder assumeMerchant(String assumeMerchant) {
    this.assumeMerchant = assumeMerchant;
    return this;
  }

  
  /**
  * Get assumeMerchant
  * @return assumeMerchant
  **/
  @ApiModelProperty(value = "")
  public String getAssumeMerchant() {
    return assumeMerchant;
  }
  public void setAssumeMerchant(String assumeMerchant) {
    this.assumeMerchant = assumeMerchant;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RefundOrder refundOrder = (RefundOrder) o;
    return Objects.equals(this.merchantRefundId, refundOrder.merchantRefundId) &&
        Objects.equals(this.paymentId, refundOrder.paymentId) &&
        Objects.equals(this.amount, refundOrder.amount) &&
        Objects.equals(this.requestedAt, refundOrder.requestedAt) &&
        Objects.equals(this.reason, refundOrder.reason) &&
        Objects.equals(this.assumeMerchant, refundOrder.assumeMerchant);
  }

  @Override
  public int hashCode() {
    return Objects.hash(merchantRefundId, paymentId, amount, requestedAt, reason, assumeMerchant);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RefundOrder {\n");
    
    sb.append("    merchantRefundId: ").append(toIndentedString(merchantRefundId)).append("\n");
    sb.append("    paymentId: ").append(toIndentedString(paymentId)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    requestedAt: ").append(toIndentedString(requestedAt)).append("\n");
    sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
    sb.append("    assumeMerchant: ").append(toIndentedString(assumeMerchant)).append("\n");
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



