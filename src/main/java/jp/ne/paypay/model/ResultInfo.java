package jp.ne.paypay.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * ResultInfo
 */

public class ResultInfo {

  @SerializedName("code")
  private String code = null;
  
  @SerializedName("message")
  private String message = null;
  
  @SerializedName("codeId")
  private String codeId = null;
  
  public ResultInfo code(String code) {
    this.code = code;
    return this;
  }

  
  /**
  * Get code
  * @return code
  **/
  @ApiModelProperty(value = "")
  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
  }
  
  public ResultInfo message(String message) {
    this.message = message;
    return this;
  }

  
  /**
  * Get message
  * @return message
  **/
  @ApiModelProperty(value = "")
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
  
  public ResultInfo codeId(String codeId) {
    this.codeId = codeId;
    return this;
  }

  
  /**
  * The code for more specific error inspection
  * @return codeId
  **/
  @ApiModelProperty(value = "The code for more specific error inspection")
  public String getCodeId() {
    return codeId;
  }
  public void setCodeId(String codeId) {
    this.codeId = codeId;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResultInfo resultInfo = (ResultInfo) o;
    return Objects.equals(this.code, resultInfo.code) &&
        Objects.equals(this.message, resultInfo.message) &&
        Objects.equals(this.codeId, resultInfo.codeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message, codeId);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResultInfo {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    codeId: ").append(toIndentedString(codeId)).append("\n");
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



