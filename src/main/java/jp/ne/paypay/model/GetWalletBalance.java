package jp.ne.paypay.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * GetWalletBalance
 */

public class GetWalletBalance {

  @SerializedName("resultInfo")
  private ResultInfo resultInfo = null;
  
  @SerializedName("data")
  private GetBalanceData data = null;
  
  public GetWalletBalance resultInfo(ResultInfo resultInfo) {
    this.resultInfo = resultInfo;
    return this;
  }

  
  /**
  * Get resultInfo
  * @return resultInfo
  **/
  @ApiModelProperty(value = "")
  public ResultInfo getResultInfo() {
    return resultInfo;
  }
  public void setResultInfo(ResultInfo resultInfo) {
    this.resultInfo = resultInfo;
  }
  
  public GetWalletBalance data(GetBalanceData data) {
    this.data = data;
    return this;
  }

  
  /**
  * Get data
  * @return data
  **/
  @ApiModelProperty(value = "")
  public GetBalanceData getData() {
    return data;
  }
  public void setData(GetBalanceData data) {
    this.data = data;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetWalletBalance walletBalance = (GetWalletBalance) o;
    return Objects.equals(this.resultInfo, walletBalance.resultInfo) &&
        Objects.equals(this.data, walletBalance.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(resultInfo, data);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetWalletBalance {\n");
    
    sb.append("    resultInfo: ").append(toIndentedString(resultInfo)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
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



