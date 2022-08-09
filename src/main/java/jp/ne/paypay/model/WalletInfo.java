package jp.ne.paypay.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class WalletInfo {
  @SerializedName("userAuthorizationId")
  private final String userAuthorizationId = null;

  @SerializedName("totalBalance")
  private final MoneyAmount totalBalance = null;

  public String getUserAuthorizationId() {
    return userAuthorizationId;
  }

  public MoneyAmount getTotalBalance() {
    return totalBalance;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WalletInfo another = (WalletInfo) o;

    return Objects.equals(this.userAuthorizationId, another.userAuthorizationId) &&
        Objects.equals(this.totalBalance, another.totalBalance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userAuthorizationId, totalBalance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WalletInfo {\n");
    sb.append("    userAuthorizationId: ").append(toIndentedString(userAuthorizationId)).append("\n");
    sb.append("    totalBalance: ").append(toIndentedString(totalBalance)).append("\n");
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
