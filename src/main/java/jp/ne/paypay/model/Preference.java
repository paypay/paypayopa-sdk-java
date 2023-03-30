package jp.ne.paypay.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * Preference
 */

public class Preference {

  @SerializedName("useCashback")
  private Boolean useCashback = null;
  
  @SerializedName("cashbackAutoInvestment")
  private Boolean cashbackAutoInvestment = null;
  
  /**
  * Get useCashback flag
  * @return useCashback
  **/
  @ApiModelProperty(value = "Use cashback amount for transaction")
  public Boolean isUseCashback() {
    return useCashback;
  }
  public void setUseCashback(Boolean useCashback) {
    this.useCashback = useCashback;
  }

  /**
  * Get cashbackAutoInvestment flag
  * @return cashbackAutoInvestment
  **/
  @ApiModelProperty(value = "Use cashback for auto investment")
  public Boolean isCashbackAutoInvestment() {
    return cashbackAutoInvestment;
  }
  public void setCashbackAutoInvestment(Boolean cashbackAutoInvestment) {
    this.cashbackAutoInvestment = cashbackAutoInvestment;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Preference preference = (Preference) o;
    return Objects.equals(this.useCashback, preference.useCashback) &&
        Objects.equals(this.cashbackAutoInvestment, preference.cashbackAutoInvestment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(useCashback, cashbackAutoInvestment);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Preference {\n");
    sb.append("    useCashback: ").append(toIndentedString(useCashback)).append("\n");
    sb.append("    cashbackAutoInvestment: ").append(toIndentedString(cashbackAutoInvestment)).append("\n");
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



