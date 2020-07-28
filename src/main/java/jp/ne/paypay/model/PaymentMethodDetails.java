package jp.ne.paypay.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * PaymentMethodDetails
 */

public class PaymentMethodDetails {

  @SerializedName("paymentMethodType")
  private String paymentMethodType = null;
  
  @SerializedName("id")
  private String id = null;
  
  @SerializedName("issuerName")
  private String issuerName = null;
  
  @SerializedName("issuerName2")
  private String issuerName2 = null;
  
  @SerializedName("accountNo")
  private String accountNo = null;
  
  @SerializedName("logoUrl")
  private String logoUrl = null;
  
  @SerializedName("balance")
  private String balance = null;
  
  @SerializedName("verified")
  private Boolean verified = null;
  
  @SerializedName("disabled")
  private Boolean disabled = null;
  
  public PaymentMethodDetails paymentMethodType(String paymentMethodType) {
    this.paymentMethodType = paymentMethodType;
    return this;
  }

  
  /**
  * The payment method type WALLET &lt;/br&gt; Credit cards are not currently supported.
  * @return paymentMethodType
  **/
  @ApiModelProperty(value = "The payment method type WALLET </br> Credit cards are not currently supported.")
  public String getPaymentMethodType() {
    return paymentMethodType;
  }
  public void setPaymentMethodType(String paymentMethodType) {
    this.paymentMethodType = paymentMethodType;
  }
  
  public PaymentMethodDetails id(String id) {
    this.id = id;
    return this;
  }

  
  /**
  * Encrypted Payment method id to be passed to create payment
  * @return id
  **/
  @ApiModelProperty(value = "Encrypted Payment method id to be passed to create payment")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  
  public PaymentMethodDetails issuerName(String issuerName) {
    this.issuerName = issuerName;
    return this;
  }

  
  /**
  * Details of the issuer of the instrument
  * @return issuerName
  **/
  @ApiModelProperty(value = "Details of the issuer of the instrument")
  public String getIssuerName() {
    return issuerName;
  }
  public void setIssuerName(String issuerName) {
    this.issuerName = issuerName;
  }
  
  public PaymentMethodDetails issuerName2(String issuerName2) {
    this.issuerName2 = issuerName2;
    return this;
  }

  
  /**
  * More details of the issuer of the instrument
  * @return issuerName2
  **/
  @ApiModelProperty(value = "More details of the issuer of the instrument")
  public String getIssuerName2() {
    return issuerName2;
  }
  public void setIssuerName2(String issuerName2) {
    this.issuerName2 = issuerName2;
  }
  
  public PaymentMethodDetails accountNo(String accountNo) {
    this.accountNo = accountNo;
    return this;
  }

  
  /**
  * Masked account no/ credit card number
  * @return accountNo
  **/
  @ApiModelProperty(value = "Masked account no/ credit card number")
  public String getAccountNo() {
    return accountNo;
  }
  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
  }
  
  public PaymentMethodDetails logoUrl(String logoUrl) {
    this.logoUrl = logoUrl;
    return this;
  }

  
  /**
  * Logo URL of the issuer
  * @return logoUrl
  **/
  @ApiModelProperty(value = "Logo URL of the issuer")
  public String getLogoUrl() {
    return logoUrl;
  }
  public void setLogoUrl(String logoUrl) {
    this.logoUrl = logoUrl;
  }
  
  public PaymentMethodDetails balance(String balance) {
    this.balance = balance;
    return this;
  }

  
  /**
  * Balance of the payment instrument, only provided for PayPay Wallet
  * @return balance
  **/
  @ApiModelProperty(value = "Balance of the payment instrument, only provided for PayPay Wallet")
  public String getBalance() {
    return balance;
  }
  public void setBalance(String balance) {
    this.balance = balance;
  }
  
  public PaymentMethodDetails verified(Boolean verified) {
    this.verified = verified;
    return this;
  }

  
  /**
  * If the card has been verified using 3D secure
  * @return verified
  **/
  @ApiModelProperty(value = "If the card has been verified using 3D secure")
  public Boolean isVerified() {
    return verified;
  }
  public void setVerified(Boolean verified) {
    this.verified = verified;
  }
  
  public PaymentMethodDetails disabled(Boolean disabled) {
    this.disabled = disabled;
    return this;
  }

  
  /**
  * If the card has been disabled by the user
  * @return disabled
  **/
  @ApiModelProperty(value = "If the card has been disabled by the user")
  public Boolean isDisabled() {
    return disabled;
  }
  public void setDisabled(Boolean disabled) {
    this.disabled = disabled;
  }
  
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentMethodDetails paymentMethodDetails = (PaymentMethodDetails) o;
    return Objects.equals(this.paymentMethodType, paymentMethodDetails.paymentMethodType) &&
        Objects.equals(this.id, paymentMethodDetails.id) &&
        Objects.equals(this.issuerName, paymentMethodDetails.issuerName) &&
        Objects.equals(this.issuerName2, paymentMethodDetails.issuerName2) &&
        Objects.equals(this.accountNo, paymentMethodDetails.accountNo) &&
        Objects.equals(this.logoUrl, paymentMethodDetails.logoUrl) &&
        Objects.equals(this.balance, paymentMethodDetails.balance) &&
        Objects.equals(this.verified, paymentMethodDetails.verified) &&
        Objects.equals(this.disabled, paymentMethodDetails.disabled);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paymentMethodType, id, issuerName, issuerName2, accountNo, logoUrl, balance, verified, disabled);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentMethodDetails {\n");
    
    sb.append("    paymentMethodType: ").append(toIndentedString(paymentMethodType)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    issuerName: ").append(toIndentedString(issuerName)).append("\n");
    sb.append("    issuerName2: ").append(toIndentedString(issuerName2)).append("\n");
    sb.append("    accountNo: ").append(toIndentedString(accountNo)).append("\n");
    sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("    verified: ").append(toIndentedString(verified)).append("\n");
    sb.append("    disabled: ").append(toIndentedString(disabled)).append("\n");
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



