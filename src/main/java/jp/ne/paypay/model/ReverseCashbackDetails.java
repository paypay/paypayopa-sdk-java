package jp.ne.paypay.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * ReverseCashbackDetails
 */

public class ReverseCashbackDetails {

  @SerializedName("resultInfo")
  private ResultInfo resultInfo = null;
  
  @SerializedName("data")
  private ReverseCashback data = null;
  
  public ReverseCashbackDetails resultInfo(ResultInfo resultInfo) {
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
  
  public ReverseCashbackDetails data(ReverseCashback data) {
    this.data = data;
    return this;
  }

  
  /**
  * Get data
  * @return data
  **/
  @ApiModelProperty(value = "")
  public ReverseCashback getData() {
    return data;
  }
  public void setData(ReverseCashback data) {
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
    ReverseCashbackDetails paymentDetails = (ReverseCashbackDetails) o;
    return Objects.equals(this.resultInfo, paymentDetails.resultInfo) &&
        Objects.equals(this.data, paymentDetails.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(resultInfo, data);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReverseCashbackDetails {\n");
    
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



