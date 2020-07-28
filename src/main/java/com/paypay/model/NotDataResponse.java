package com.paypay.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * NotDataResponse
 */

public class NotDataResponse {

  @SerializedName("resultInfo")
  private ResultInfo resultInfo = null;
  
  public NotDataResponse resultInfo(ResultInfo resultInfo) {
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
  
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NotDataResponse notDataResponse = (NotDataResponse) o;
    return Objects.equals(this.resultInfo, notDataResponse.resultInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(resultInfo);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NotDataResponse {\n");
    
    sb.append("    resultInfo: ").append(toIndentedString(resultInfo)).append("\n");
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



