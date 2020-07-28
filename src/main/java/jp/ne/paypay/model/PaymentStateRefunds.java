package jp.ne.paypay.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * PaymentStateRefunds
 */

public class PaymentStateRefunds {

  @SerializedName("data")
  private List<Refund> data = null;
  
  public PaymentStateRefunds data(List<Refund> data) {
    this.data = data;
    return this;
  }

  public PaymentStateRefunds addDataItem(Refund dataItem) {
    
    if (this.data == null) {
      this.data = new ArrayList<Refund>();
    }
    
    this.data.add(dataItem);
    return this;
  }
  
  /**
  * Get data
  * @return data
  **/
  @ApiModelProperty(value = "")
  public List<Refund> getData() {
    return data;
  }
  public void setData(List<Refund> data) {
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
    PaymentStateRefunds paymentStateRefunds = (PaymentStateRefunds) o;
    return Objects.equals(this.data, paymentStateRefunds.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentStateRefunds {\n");
    
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



