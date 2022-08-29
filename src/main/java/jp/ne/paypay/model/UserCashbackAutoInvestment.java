package jp.ne.paypay.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.Objects;

/**
 * UserCashbackAutoInvestment
 */

public class UserCashbackAutoInvestment {

  @SerializedName("userAuthorizationId")
  @NotEmpty(message = "userAuthorizationId is required")
  @Size(max = 64, message = "maximum 64 characters are allowed for userAuthorizationId")
  private String userAuthorizationId = null;
  
  @SerializedName("cashbackAutoInvestment")
  @NotNull(message = "cashbackAutoInvestment is required")
  private Boolean cashbackAutoInvestment = null;

  @SerializedName("updatedAt")
  private Long updatedAt = Instant.now().getEpochSecond();

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

  /**
   * Get cashbackAutoInvestment
   * @return cashbackAutoInvestment
   **/
  @ApiModelProperty(value = "")
  public Boolean getCashbackAutoInvestment() {
    return cashbackAutoInvestment;
  }

  public void setCashbackAutoInvestment(Boolean cashbackAutoInvestment) {
    this.cashbackAutoInvestment = cashbackAutoInvestment;
  }

  /**
   * Get updatedAt
   * @return updatedAt
   **/
  @ApiModelProperty(value = "")
  public Long getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Long updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserCashbackAutoInvestment userCashbackAutoInvestment = (UserCashbackAutoInvestment) o;
    return Objects.equals(this.userAuthorizationId, userCashbackAutoInvestment.userAuthorizationId) &&
            Objects.equals(this.cashbackAutoInvestment, userCashbackAutoInvestment.cashbackAutoInvestment) &&
            Objects.equals(this.updatedAt, userCashbackAutoInvestment.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userAuthorizationId, cashbackAutoInvestment, updatedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserCashbackAutoInvestment {\n");
    sb.append("    userAuthorizationId: ").append(toIndentedString(userAuthorizationId)).append("\n");
    sb.append("    useCashback: ").append(toIndentedString(cashbackAutoInvestment)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
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



