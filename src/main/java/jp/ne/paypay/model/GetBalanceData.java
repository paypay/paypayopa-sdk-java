package jp.ne.paypay.model;

import com.google.gson.annotations.SerializedName;

import io.swagger.annotations.ApiModelProperty;

/**
 * BalanceData
 */
public class GetBalanceData {

  @SerializedName("userAuthorizationId")
  private String userAuthorizationId = null;
  
  @SerializedName("totalBalance")
  private MoneyAmount totalBalance = null;

  @SerializedName("preference")
  private Preference preference = null;

  /**
  * User's userAuthorizationId
  * @return userAuthorizationId
  **/
  @ApiModelProperty(value = "User's userAuthorizationId")
  public String getUserAuthorizationId() {
    return userAuthorizationId;
  }

  public GetBalanceData setUserAuthorizationId(String userAuthorizationId) {
    this.userAuthorizationId = userAuthorizationId;
    return this;
  }

  /**
  * User's total balance
  * @return totalAmount
  **/

  public MoneyAmount getTotalBalance() {
    return totalBalance;
  }
  public void setTotalBalance(MoneyAmount totalBalance) {
    this.totalBalance = totalBalance;
  }

  /**
  * User's Preference
  * @return preference
  **/

  public Preference getPreference() {
    return preference;
  }
  public void setPreference(Preference preference) {
    this.preference = preference;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetBalanceData {\n");
    
    sb.append("    userAuthorizationId: ").append(toIndentedString(userAuthorizationId)).append("\n");
    sb.append("    totalBalance: ").append(toIndentedString(totalBalance)).append("\n");
    sb.append("    preference: ").append(toIndentedString(preference)).append("\n");
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