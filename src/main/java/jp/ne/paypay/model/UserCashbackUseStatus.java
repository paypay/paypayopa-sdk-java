package jp.ne.paypay.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * UserCashbackUseStatus
 */

public class UserCashbackUseStatus {

  @SerializedName("userAuthorizationId")
  @NotEmpty(message = "userAuthorizationId is required")
  @Size(max = 64, message = "maximum 64 characters are allowed for userAuthorizationId")
  private String userAuthorizationId = null;
  
  @SerializedName("useCashback")
  @NotNull(message = "useCashback is required")
  private Boolean useCashback = null;

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
   * Get useCashback
   * @return useCashback
   **/
  @ApiModelProperty(value = "")
  public Boolean getUseCashback() {
    return useCashback;
  }

  public void setUseCashback(Boolean useCashback) {
    this.useCashback = useCashback;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserCashbackUseStatus userCashbackUseStatus = (UserCashbackUseStatus) o;
    return Objects.equals(this.userAuthorizationId, userCashbackUseStatus.userAuthorizationId) &&
            Objects.equals(this.useCashback, userCashbackUseStatus.useCashback);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userAuthorizationId, useCashback);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserCashbackUseStatus {\n");
    sb.append("    userAuthorizationId: ").append(toIndentedString(userAuthorizationId)).append("\n");
    sb.append("    useCashback: ").append(toIndentedString(useCashback)).append("\n");
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



