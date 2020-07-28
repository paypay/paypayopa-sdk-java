package com.paypay.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * PaymentOrder
 */

public class PaymentOrder {

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
  
  public PaymentOrder merchantPaymentId(String merchantPaymentId) {
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
  
  public PaymentOrder userAuthorizationId(String userAuthorizationId) {
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
  
  public PaymentOrder amount(MoneyAmount amount) {
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
  
  public PaymentOrder requestedAt(Long requestedAt) {
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
  
  public PaymentOrder storeId(String storeId) {
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
  
  public PaymentOrder terminalId(String terminalId) {
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
  
  public PaymentOrder orderReceiptNumber(String orderReceiptNumber) {
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
  
  public PaymentOrder orderDescription(String orderDescription) {
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
  
  public PaymentOrder orderItems(List<MerchantOrderItem> orderItems) {
    this.orderItems = orderItems;
    return this;
  }

  public PaymentOrder addOrderItemsItem(MerchantOrderItem orderItemsItem) {
    
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
  
  public PaymentOrder metadata(Object metadata) {
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
  
  public PaymentOrder expiresAt(Integer expiresAt) {
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
  
  public PaymentOrder amountescription(String amountescription) {
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
    PaymentOrder paymentOrder = (PaymentOrder) o;
    return Objects.equals(this.merchantPaymentId, paymentOrder.merchantPaymentId) &&
        Objects.equals(this.userAuthorizationId, paymentOrder.userAuthorizationId) &&
        Objects.equals(this.amount, paymentOrder.amount) &&
        Objects.equals(this.requestedAt, paymentOrder.requestedAt) &&
        Objects.equals(this.storeId, paymentOrder.storeId) &&
        Objects.equals(this.terminalId, paymentOrder.terminalId) &&
        Objects.equals(this.orderReceiptNumber, paymentOrder.orderReceiptNumber) &&
        Objects.equals(this.orderDescription, paymentOrder.orderDescription) &&
        Objects.equals(this.orderItems, paymentOrder.orderItems) &&
        Objects.equals(this.metadata, paymentOrder.metadata) &&
        Objects.equals(this.expiresAt, paymentOrder.expiresAt) &&
        Objects.equals(this.amountescription, paymentOrder.amountescription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(merchantPaymentId, userAuthorizationId, amount, requestedAt, storeId, terminalId, orderReceiptNumber, orderDescription, orderItems, metadata, expiresAt, amountescription);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentOrder {\n");
    
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



