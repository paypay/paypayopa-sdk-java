package com.paypay.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * BalanceData
 */
public class BalanceData {

  @SerializedName("hasEnoughBalance")
  private Boolean hasEnoughBalance = null;
  
  public BalanceData hasEnoughBalance(Boolean hasEnoughBalance) {
    this.hasEnoughBalance = hasEnoughBalance;
    return this;
  }

  
  /**
  * Get hasEnoughBalance
  * @return hasEnoughBalance
  **/
  @ApiModelProperty(value = "")
  public Boolean isHasEnoughBalance() {
    return hasEnoughBalance;
  }
  public void setHasEnoughBalance(Boolean hasEnoughBalance) {
    this.hasEnoughBalance = hasEnoughBalance;
  }
  
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BalanceData balanceData = (BalanceData) o;
    return Objects.equals(this.hasEnoughBalance, balanceData.hasEnoughBalance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hasEnoughBalance);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BalanceData {\n");
    
    sb.append("    hasEnoughBalance: ").append(toIndentedString(hasEnoughBalance)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

  
}



