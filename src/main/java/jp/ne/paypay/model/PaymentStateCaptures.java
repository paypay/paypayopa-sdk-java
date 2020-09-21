package jp.ne.paypay.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * captures field contains data only when the user request is ongoing.
 */@ApiModel(description = "captures field contains data only when the user request is ongoing.")

public class PaymentStateCaptures {

  @SerializedName("data")
  private List<Capture> data = null;
  
  public PaymentStateCaptures data(List<Capture> data) {
    this.data = data;
    return this;
  }

  public PaymentStateCaptures addDataItem(Capture dataItem) {
    
    if (this.data == null) {
      this.data = new ArrayList<Capture>();
    }
    
    this.data.add(dataItem);
    return this;
  }
  
  /**
  * Get data
  * @return data
  **/
  @ApiModelProperty(value = "")
  public List<Capture> getData() {
    return data;
  }
  public void setData(List<Capture> data) {
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
    PaymentStateCaptures paymentStateCaptures = (PaymentStateCaptures) o;
    return Objects.equals(this.data, paymentStateCaptures.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentStateCaptures {\n");
    
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



