package jp.ne.paypay.model;

import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

/**
 * Cashback
 */

public class Cashback extends CashbackState {

  @SerializedName("merchantCashbackId")
  @NotEmpty(message = "merchantCashbackId is required")
  @Size(max = 64, message = "maximum 64 characters are allowed for merchantCashbackId")
  private String merchantCashbackId = null;
  
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
  
  @SerializedName("orderDescription")
  @Size(max =255 ,message = "maximum 255 characters allowed for orderDescription")
  private String orderDescription = null;
  
  @SerializedName("metadata")
  private Object metadata = null;
  
  @SerializedName("expiryDate")
  private Integer expiryDate = null;

  @SerializedName("walletType")
  private WalletType walletType = null;

  public String getMerchantCashbackId() {
    return merchantCashbackId;
  }

  public Cashback setMerchantCashbackId(String merchantCashbackId) {
    this.merchantCashbackId = merchantCashbackId;
    return this;
  }

  public String getUserAuthorizationId() {
    return userAuthorizationId;
  }

  public Cashback setUserAuthorizationId(String userAuthorizationId) {
    this.userAuthorizationId = userAuthorizationId;
    return this;
  }

  public MoneyAmount getAmount() {
    return amount;
  }

  public Cashback setAmount(MoneyAmount amount) {
    this.amount = amount;
    return this;
  }

  public Long getRequestedAt() {
    return requestedAt;
  }

  public Cashback setRequestedAt(Long requestedAt) {
    this.requestedAt = requestedAt;
    return this;
  }

  public String getOrderDescription() {
    return orderDescription;
  }

  public Cashback setOrderDescription(String orderDescription) {
    this.orderDescription = orderDescription;
    return this;
  }

  public Object getMetadata() {
    return metadata;
  }

  public Cashback setMetadata(Object metadata) {
    this.metadata = metadata;
    return this;
  }

  public Integer getExpiryDate() {
    return expiryDate;
  }

  public Cashback setExpiryDate(Integer expiryDate) {
    this.expiryDate = expiryDate;
    return this;
  }

  public WalletType getWalletType() {
    return walletType;
  }

  public Cashback setWalletType(WalletType walletType) {
    this.walletType = walletType;
    return this;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CashbackRequest {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    merchantCashbackId: ").append(toIndentedString(merchantCashbackId)).append("\n");
    sb.append("    userAuthorizationId: ").append(toIndentedString(userAuthorizationId)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    requestedAt: ").append(toIndentedString(requestedAt)).append("\n");
    sb.append("    orderDescription: ").append(toIndentedString(orderDescription)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    expiryDate: ").append(toIndentedString(expiryDate)).append("\n");
    sb.append("    walletType: ").append(toIndentedString(walletType)).append("\n");
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



