package jp.ne.paypay.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * UserCashbackSettingStatus
 */

public class UserCashbackSettingStatus {

  @SerializedName("resultInfo")
  private ResultInfo resultInfo = null;
  
  @SerializedName("data")
  private UserCashbackStatus data = null;
  
  public UserCashbackSettingStatus resultInfo(ResultInfo resultInfo) {
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
  
  public UserCashbackSettingStatus data(UserCashbackStatus data) {
    this.data = data;
    return this;
  }

  
  /**
  * Get data
  * @return data
  **/
  @ApiModelProperty(value = "")
  public UserCashbackStatus getData() {
    return data;
  }
  public void setData(UserCashbackStatus data) {
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
    UserCashbackSettingStatus userCashbackSettingStatus = (UserCashbackSettingStatus) o;
    return Objects.equals(this.resultInfo, userCashbackSettingStatus.resultInfo) &&
        Objects.equals(this.data, userCashbackSettingStatus.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(resultInfo, data);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserCashbackSettingStatus {\n");
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



