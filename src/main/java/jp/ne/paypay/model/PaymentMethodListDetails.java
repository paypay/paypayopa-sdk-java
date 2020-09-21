package jp.ne.paypay.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * PaymentMethodListDetails
 */

public class PaymentMethodListDetails {

  @SerializedName("paymentMethods")
  private List<PaymentMethodDetails> paymentMethods = null;
  
  public PaymentMethodListDetails paymentMethods(List<PaymentMethodDetails> paymentMethods) {
    this.paymentMethods = paymentMethods;
    return this;
  }

  public PaymentMethodListDetails addPaymentMethodsItem(PaymentMethodDetails paymentMethodsItem) {
    
    if (this.paymentMethods == null) {
      this.paymentMethods = new ArrayList<PaymentMethodDetails>();
    }
    
    this.paymentMethods.add(paymentMethodsItem);
    return this;
  }
  
  /**
  * Get paymentMethods
  * @return paymentMethods
  **/
  @ApiModelProperty(value = "")
  public List<PaymentMethodDetails> getPaymentMethods() {
    return paymentMethods;
  }
  public void setPaymentMethods(List<PaymentMethodDetails> paymentMethods) {
    this.paymentMethods = paymentMethods;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentMethodListDetails paymentMethodListDetails = (PaymentMethodListDetails) o;
    return Objects.equals(this.paymentMethods, paymentMethodListDetails.paymentMethods);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paymentMethods);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentMethodListDetails {\n");
    
    sb.append("    paymentMethods: ").append(toIndentedString(paymentMethods)).append("\n");
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



