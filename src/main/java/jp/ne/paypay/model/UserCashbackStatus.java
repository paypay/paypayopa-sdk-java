package jp.ne.paypay.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * UserCashbackStatus
 */

public class UserCashbackStatus {

  @SerializedName("useCashback")
  private Boolean useCashback = null;
  
  @SerializedName("cashbackAutoInvestment")
  private Boolean cashbackAutoInvestment = null;

  /**
   * The flag whether user use cashback for payment
   * @return useCashback
   **/
  @ApiModelProperty(value = "The flag whether user use cashback for payment")
  public Boolean getUseCashback() {
    return useCashback;
  }

  public void setUseCashback(Boolean useCashback) {
    this.useCashback = useCashback;
  }

  /**
   * The flag whether user auto-invests points for Point Investment
   * @return cashbackAutoInvestment
   **/
  @ApiModelProperty(value = "The flag whether user auto-invests points for Point Investment")
  public Boolean getCashbackAutoInvestment() {
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
    UserCashbackStatus userCashbackStatus = (UserCashbackStatus) o;
    return Objects.equals(this.useCashback, userCashbackStatus.useCashback) &&
        Objects.equals(this.cashbackAutoInvestment, userCashbackStatus.cashbackAutoInvestment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(useCashback, cashbackAutoInvestment);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserCashbackStatus {\n");
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



