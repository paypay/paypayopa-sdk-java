package jp.ne.paypay.model;

import com.google.gson.annotations.SerializedName;

/**
 * ReverseCashbackState
 */

public class ReverseCashbackState {

  @SerializedName("cashbackReversalId")
  private String cashbackReversalId = null;
  
  @SerializedName("status")
  private String status = null;

  @SerializedName("acceptedAt")
  private Integer acceptedAt = null;

  @SerializedName("merchantAlias")
  private Integer merchantAlias = null;

  public String getCashbackReversalId() {
    return cashbackReversalId;
  }

  public ReverseCashbackState setCashbackReversalId(String cashbackReversalId) {
    this.cashbackReversalId = cashbackReversalId;
    return this;
  }

  public String getStatus() {
    return status;
  }

  public ReverseCashbackState setStatus(String status) {
    this.status = status;
    return this;
  }

  public Integer getAcceptedAt() {
    return acceptedAt;
  }

  public ReverseCashbackState setAcceptedAt(Integer acceptedAt) {
    this.acceptedAt = acceptedAt;
    return this;
  }

  public Integer getMerchantAlias() {
    return merchantAlias;
  }

  public ReverseCashbackState setMerchantAlias(Integer merchantAlias) {
    this.merchantAlias = merchantAlias;
    return this;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReverseCashbackState {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    cashbackReversalId: ").append(toIndentedString(cashbackReversalId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    acceptedAt: ").append(toIndentedString(acceptedAt)).append("\n");
    sb.append("    merchantAlias: ").append(toIndentedString(merchantAlias)).append("\n");
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



