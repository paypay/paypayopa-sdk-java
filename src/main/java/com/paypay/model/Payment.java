package com.paypay.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * Payment
 */

public class Payment extends PaymentState {

  @SerializedName("merchantPaymentId")
  private String merchantPaymentId = null;
  
  @SerializedName("userAuthorizationId")
  private String userAuthorizationId = null;
  
  @SerializedName("amount")
  private MoneyAmount amount = null;
  
  @SerializedName("requestedAt")
  private Long requestedAt = null;
  
  @SerializedName("storeId")
  private String storeId = null;
  
  @SerializedName("terminalId")
  private String terminalId = null;
  
  @SerializedName("orderReceiptNumber")
  private String orderReceiptNumber = null;
  
  @SerializedName("orderDescription")
  private String orderDescription = null;
  
  @SerializedName("orderItems")
  private List<MerchantOrderItem> orderItems = null;
  
  @SerializedName("metadata")
  private Object metadata = null;
  
  @SerializedName("expiresAt")
  private Integer expiresAt = null;
  
  @SerializedName("amountescription")
  private String amountescription = null;
  
  public Payment merchantPaymentId(String merchantPaymentId) {
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
  
  public Payment userAuthorizationId(String userAuthorizationId) {
    this.userAuthorizationId = userAuthorizationId;
    return this;
  }

  
  /**
  * Get userAuthorizationId
  * @return userAuthorizationId
  **/
  @ApiModelProperty(value = "")
  public String getUserAuthorizationId() {
    return userAuthorizationId;
  }
  public void setUserAuthorizationId(String userAuthorizationId) {
    this.userAuthorizationId = userAuthorizationId;
  }
  
  public Payment amount(MoneyAmount amount) {
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
  
  public Payment requestedAt(Long requestedAt) {
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
  
  public Payment storeId(String storeId) {
    this.storeId = storeId;
    return this;
  }

  
  /**
  * Id to identify store under merchant
  * @return storeId
  **/
  @ApiModelProperty(value = "Id to identify store under merchant")
  public String getStoreId() {
    return storeId;
  }
  public void setStoreId(String storeId) {
    this.storeId = storeId;
  }
  
  public Payment terminalId(String terminalId) {
    this.terminalId = terminalId;
    return this;
  }

  
  /**
  * Id to identify terminal device under store
  * @return terminalId
  **/
  @ApiModelProperty(value = "Id to identify terminal device under store")
  public String getTerminalId() {
    return terminalId;
  }
  public void setTerminalId(String terminalId) {
    this.terminalId = terminalId;
  }
  
  public Payment orderReceiptNumber(String orderReceiptNumber) {
    this.orderReceiptNumber = orderReceiptNumber;
    return this;
  }

  
  /**
  * Receipt number provided by merchant
  * @return orderReceiptNumber
  **/
  @ApiModelProperty(value = "Receipt number provided by merchant")
  public String getOrderReceiptNumber() {
    return orderReceiptNumber;
  }
  public void setOrderReceiptNumber(String orderReceiptNumber) {
    this.orderReceiptNumber = orderReceiptNumber;
  }
  
  public Payment orderDescription(String orderDescription) {
    this.orderDescription = orderDescription;
    return this;
  }

  
  /**
  * Description of the order
  * @return orderDescription
  **/
  @ApiModelProperty(value = "Description of the order")
  public String getOrderDescription() {
    return orderDescription;
  }
  public void setOrderDescription(String orderDescription) {
    this.orderDescription = orderDescription;
  }
  
  public Payment orderItems(List<MerchantOrderItem> orderItems) {
    this.orderItems = orderItems;
    return this;
  }

  public Payment addOrderItemsItem(MerchantOrderItem orderItemsItem) {
    
    if (this.orderItems == null) {
      this.orderItems = new ArrayList<MerchantOrderItem>();
    }
    
    this.orderItems.add(orderItemsItem);
    return this;
  }
  
  /**
  * Get orderItems
  * @return orderItems
  **/
  @ApiModelProperty(value = "")
  public List<MerchantOrderItem> getOrderItems() {
    return orderItems;
  }
  public void setOrderItems(List<MerchantOrderItem> orderItems) {
    this.orderItems = orderItems;
  }
  
  public Payment metadata(Object metadata) {
    this.metadata = metadata;
    return this;
  }

  
  /**
  * Extra information the merchant want to add
  * @return metadata
  **/
  @ApiModelProperty(value = "Extra information the merchant want to add")
  public Object getMetadata() {
    return metadata;
  }
  public void setMetadata(Object metadata) {
    this.metadata = metadata;
  }
  
  public Payment expiresAt(Integer expiresAt) {
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
  
  public Payment amountescription(String amountescription) {
    this.amountescription = amountescription;
    return this;
  }

  
  /**
  * Description of the order
  * @return amountescription
  **/
  @ApiModelProperty(value = "Description of the order")
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
    Payment payment = (Payment) o;
    return Objects.equals(this.merchantPaymentId, payment.merchantPaymentId) &&
        Objects.equals(this.userAuthorizationId, payment.userAuthorizationId) &&
        Objects.equals(this.amount, payment.amount) &&
        Objects.equals(this.requestedAt, payment.requestedAt) &&
        Objects.equals(this.storeId, payment.storeId) &&
        Objects.equals(this.terminalId, payment.terminalId) &&
        Objects.equals(this.orderReceiptNumber, payment.orderReceiptNumber) &&
        Objects.equals(this.orderDescription, payment.orderDescription) &&
        Objects.equals(this.orderItems, payment.orderItems) &&
        Objects.equals(this.metadata, payment.metadata) &&
        Objects.equals(this.expiresAt, payment.expiresAt) &&
        Objects.equals(this.amountescription, payment.amountescription) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(merchantPaymentId, userAuthorizationId, amount, requestedAt, storeId, terminalId, orderReceiptNumber, orderDescription, orderItems, metadata, expiresAt, amountescription, super.hashCode());
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Payment {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    merchantPaymentId: ").append(toIndentedString(merchantPaymentId)).append("\n");
    sb.append("    userAuthorizationId: ").append(toIndentedString(userAuthorizationId)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    requestedAt: ").append(toIndentedString(requestedAt)).append("\n");
    sb.append("    storeId: ").append(toIndentedString(storeId)).append("\n");
    sb.append("    terminalId: ").append(toIndentedString(terminalId)).append("\n");
    sb.append("    orderReceiptNumber: ").append(toIndentedString(orderReceiptNumber)).append("\n");
    sb.append("    orderDescription: ").append(toIndentedString(orderDescription)).append("\n");
    sb.append("    orderItems: ").append(toIndentedString(orderItems)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    expiresAt: ").append(toIndentedString(expiresAt)).append("\n");
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



