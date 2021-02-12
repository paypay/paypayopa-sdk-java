package jp.ne.paypay.model;

import com.google.gson.annotations.SerializedName;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;

/**
 * ReverseCashback
 */

public class ReverseCashback extends ReverseCashbackState {

  @SerializedName("merchantCashbackReversalId")
  @NotEmpty(message = "merchantCashbackReversalId is required")
  @Size(max = 64, message = "maximum 64 characters are allowed for merchantCashbackReversalId")
  private String merchantCashbackReversalId = null;

  @SerializedName("merchantCashbackId")
  @NotEmpty(message = "merchantCashbackId is required")
  @Size(max = 64, message = "maximum 64 characters are allowed for merchantCashbackId")
  private String merchantCashbackId = null;
  
  @SerializedName("amount")
  @NotNull(message = "amount is  required")
  private MoneyAmount amount = null;
  
  @SerializedName("requestedAt")
  @NotNull(message =  "requestedAt is required")
  private Long requestedAt = Instant.now().getEpochSecond();
  
  @SerializedName("reason")
  @Size(max =255 ,message = "maximum 255 characters allowed for reason")
  private String reason = null;
  
  @SerializedName("metadata")
  private Object metadata = null;

  public String getMerchantCashbackReversalId() {
    return merchantCashbackReversalId;
  }

  public ReverseCashback setMerchantCashbackReversalId(String merchantCashbackReversalId) {
    this.merchantCashbackReversalId = merchantCashbackReversalId;
    return this;
  }

  public String getMerchantCashbackId() {
    return merchantCashbackId;
  }

  public ReverseCashback setMerchantCashbackId(String merchantCashbackId) {
    this.merchantCashbackId = merchantCashbackId;
    return this;
  }

  public MoneyAmount getAmount() {
    return amount;
  }

  public ReverseCashback setAmount(MoneyAmount amount) {
    this.amount = amount;
    return this;
  }

  public Long getRequestedAt() {
    return requestedAt;
  }

  public ReverseCashback setRequestedAt(Long requestedAt) {
    this.requestedAt = requestedAt;
    return this;
  }

  public String getReason() {
    return reason;
  }

  public ReverseCashback setReason(String reason) {
    this.reason = reason;
    return this;
  }

  public Object getMetadata() {
    return metadata;
  }

  public ReverseCashback setMetadata(Object metadata) {
    this.metadata = metadata;
    return this;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReverseCashback {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    merchantCashbackReversalId: ").append(toIndentedString(merchantCashbackReversalId)).append("\n");
    sb.append("    merchantCashbackId: ").append(toIndentedString(merchantCashbackId)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    requestedAt: ").append(toIndentedString(requestedAt)).append("\n");
    sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
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



