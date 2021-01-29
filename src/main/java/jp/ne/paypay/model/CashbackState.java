package jp.ne.paypay.model;

import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

/**
 * CashbackState
 */

public class CashbackState {

  @SerializedName("cashbackId")
  private String cashbackId = null;
  
  @SerializedName("status")
  private String status = null;

  @SerializedName("acceptedAt")
  private Integer acceptedAt = null;

  @SerializedName("merchantAlias")
  private Integer merchantAlias = null;

  public String getCashbackId() {
    return cashbackId;
  }

  public CashbackState setCashbackId(String cashbackId) {
    this.cashbackId = cashbackId;
    return this;
  }

  public String getStatus() {
    return status;
  }

  public CashbackState setStatus(String status) {
    this.status = status;
    return this;
  }

  public Integer getAcceptedAt() {
    return acceptedAt;
  }

  public CashbackState setAcceptedAt(Integer acceptedAt) {
    this.acceptedAt = acceptedAt;
    return this;
  }

  public Integer getMerchantAlias() {
    return merchantAlias;
  }

  public CashbackState setMerchantAlias(Integer merchantAlias) {
    this.merchantAlias = merchantAlias;
    return this;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CashbackState {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    cashbackId: ").append(toIndentedString(cashbackId)).append("\n");
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



