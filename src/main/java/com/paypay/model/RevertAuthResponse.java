package com.paypay.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * RevertAuthResponse
 */

public class RevertAuthResponse {

  @SerializedName("resultInfo")
  private ResultInfo resultInfo = null;
  
  @SerializedName("data")
  private RevertAuthResponseData data = null;
  
  public RevertAuthResponse resultInfo(ResultInfo resultInfo) {
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
  
  public RevertAuthResponse data(RevertAuthResponseData data) {
    this.data = data;
    return this;
  }

  
  /**
  * Get data
  * @return data
  **/
  @ApiModelProperty(value = "")
  public RevertAuthResponseData getData() {
    return data;
  }
  public void setData(RevertAuthResponseData data) {
    this.data = data;
  }
  
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RevertAuthResponse revertAuthResponse = (RevertAuthResponse) o;
    return Objects.equals(this.resultInfo, revertAuthResponse.resultInfo) &&
        Objects.equals(this.data, revertAuthResponse.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(resultInfo, data);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RevertAuthResponse {\n");
    
    sb.append("    resultInfo: ").append(toIndentedString(resultInfo)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
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



