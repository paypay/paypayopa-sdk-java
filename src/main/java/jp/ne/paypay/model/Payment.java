package jp.ne.paypay.model;

import com.google.gson.annotations.SerializedName;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Payment
 */

public class Payment extends PaymentState {

  @SerializedName("merchantPaymentId")
  @NotEmpty(message = "merchantPaymentId is required")
  @Size(max = 64, message = "maximum 64 characters are allowed for merchantPaymentId")
  private String merchantPaymentId = null;
  
  @SerializedName("userAuthorizationId")
  @NotEmpty(message = "userAuthorizationId is required")
  @Size(max = 64, message = "maximum 64 characters are allowed for userAuthorizationId")
  private String userAuthorizationId = null;
  
  @SerializedName("amount")
  @NotNull(message = "amount is  required")
  private MoneyAmount amount = null;
  
  @SerializedName("requestedAt")
  @NotNull(message =  "requestedAt is required")
  private Long requestedAt = Instant.now().getEpochSecond();
  
  @SerializedName("storeId")
  @Size(max =255 ,message = "maximum 255 characters allowed for storeId")
  private String storeId = null;
  
  @SerializedName("terminalId")
  @Size(max =255 ,message = "maximum 255 characters allowed for terminalId")
  private String terminalId = null;
  
  @SerializedName("orderReceiptNumber")
  @Size(max =255 ,message = "maximum 255 characters allowed for orderReceiptNumber")
  private String orderReceiptNumber = null;
  
  @SerializedName("orderDescription")
  @Size(max =255 ,message = "maximum 255 characters allowed for orderDescription")
  private String orderDescription = null;
  
  @SerializedName("orderItems")
  private List<MerchantOrderItem> orderItems = null;
  
  @SerializedName("metadata")
  private Object metadata = null;
  
  @SerializedName("expiresAt")
  private Integer expiresAt = null;
  
  @SerializedName("amountescription")
  private String amountDescription = null;

  @SerializedName("paymentMethodType")
  private String paymentMethodType = null;

  @SerializedName("paymentMethodId")
  private String paymentMethodId = null;


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
  
  public Payment amountDescription(String amountDescription) {
    this.amountDescription = amountDescription;
    return this;
  }

  
  /**
  * Description of the order
  * @return amountescription
  **/
  @ApiModelProperty(value = "Description of the order")
  public String getAmountDescription() {
    return amountDescription;
  }
  public void setAmountDescription(String amountDescription) {
    this.amountDescription = amountDescription;
  }


  public String getPaymentMethodType() {
    return paymentMethodType;
  }

  public String getPaymentMethodId() {
    return paymentMethodId;
  }

  public void setPaymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethodType = paymentMethod.getPaymentMethodType();
    this.paymentMethodId = paymentMethod.getPaymentMethodId();
  }

  /**
   * Set WALLET as payment method.
   * As of now, WALLET is the default payment method, and
   * it is not required to send paymentMethodId if "WALLET" is passed as paymentMethodType.
   */
  public void setPaymentMethodAsWallet() {
    this.paymentMethodType = "WALLET";
    this.paymentMethodId = null;
  }

  @Override
  public boolean equals(Object o) {
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
        Objects.equals(this.amountDescription, payment.amountDescription) &&
        Objects.equals(this.paymentMethodType, payment.paymentMethodType) &&
        Objects.equals(this.paymentMethodId, payment.paymentMethodId) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(merchantPaymentId, userAuthorizationId, amount, requestedAt,
        storeId, terminalId, orderReceiptNumber, orderDescription, orderItems, metadata,
        expiresAt, amountDescription, paymentMethodType, paymentMethodId,  super.hashCode());
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
    sb.append("    amountDescription: ").append(toIndentedString(amountDescription)).append("\n");
    sb.append("    paymentMethodType: ").append(toIndentedString(paymentMethodType)).append("\n");
    sb.append("    paymentMethodId: ").append(toIndentedString(paymentMethodId)).append("\n");
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
