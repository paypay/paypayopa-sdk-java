package jp.ne.paypay.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class PaymentMethod {

  @SerializedName("paymentMethodType")
  private final String paymentMethodType = null;

  @SerializedName("id")
  private final String paymentMethodId = null;

  @SerializedName("issuerName")
  private final String issuerName = null;

  @SerializedName("accountNo")
  private final String accountNo = null;

  @SerializedName("logoUrl")
  private final String logoUrl = null;

  @SerializedName("disabled")
  private final Boolean disabled = null;

  @SerializedName("underMaintenance")
  private final Boolean underMaintenance = null;

  @SerializedName("authenticated")
  private final Boolean authenticated = null;

  @SerializedName("limited")
  private final Boolean limited = null;

  @SerializedName("paymentMethodStatus")
  private final PaymentMethodStatus paymentMethodStatus = null;

  public enum PaymentMethodStatus {
    MAINTENANCE,
    OFF,
    ACTIVATED,
    APPLYING,
    SUSPENDED,
    EXPIRED;
  }


  public String getPaymentMethodType() {
    return paymentMethodType;
  }

  public String getPaymentMethodId() {
    return paymentMethodId;
  }

  public String getIssuerName() {
    return issuerName;
  }

  public String getAccountNo() {
    return accountNo;
  }

  public String getLogoUrl() {
    return logoUrl;
  }

  public Boolean getDisabled() {
    return disabled;
  }

  public Boolean getUnderMaintenance() {
    return underMaintenance;
  }

  public Boolean getAuthenticated() {
    return authenticated;
  }

  public Boolean getLimited() {
    return limited;
  }

  public PaymentMethodStatus getPaymentMethodStatus() {
    return paymentMethodStatus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentMethod another = (PaymentMethod) o;

    return Objects.equals(this.paymentMethodType, another.paymentMethodType) &&
      Objects.equals(this.paymentMethodId, another.paymentMethodId) &&
      Objects.equals(this.issuerName, another.issuerName) &&
      Objects.equals(this.accountNo, another.accountNo) &&
      Objects.equals(this.logoUrl, another.logoUrl) &&
      Objects.equals(this.disabled, another.disabled) &&
      Objects.equals(this.underMaintenance, another.underMaintenance) &&
      Objects.equals(this.authenticated, another.authenticated) &&
      Objects.equals(this.limited, another.limited) &&
      Objects.equals(this.paymentMethodStatus, another.paymentMethodStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paymentMethodType, paymentMethodId, issuerName,
        accountNo, logoUrl, disabled, underMaintenance, authenticated, limited, paymentMethodStatus);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentMethod {\n");
    sb.append("    paymentMethodType: ").append(toIndentedString(paymentMethodType)).append("\n");
    sb.append("    issuerName: ").append(toIndentedString(issuerName)).append("\n");
    sb.append("    accountNo: ").append(toIndentedString(accountNo)).append("\n");
    sb.append("    logoUrl: ").append(toIndentedString(logoUrl)).append("\n");
    sb.append("    disabled: ").append(toIndentedString(disabled)).append("\n");
    sb.append("    underMaintenance: ").append(toIndentedString(underMaintenance)).append("\n");
    sb.append("    authenticated: ").append(toIndentedString(authenticated)).append("\n");
    sb.append("    limited: ").append(toIndentedString(limited)).append("\n");
    sb.append("    paymentMethodStatus: ").append(toIndentedString(paymentMethodStatus)).append("\n");
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
