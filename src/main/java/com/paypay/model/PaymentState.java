package com.paypay.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * PaymentState
 */

public class PaymentState {

  @SerializedName("paymentId")
  private String paymentId = null;
  
  /**
   * Gets or Sets status
   */
  @JsonAdapter(StatusEnum.Adapter.class)
  public enum StatusEnum {
    
    CREATED("CREATED"),
    AUTHORIZED("AUTHORIZED"),
    REAUTHORIZING("REAUTHORIZING"),
    COMPLETED("COMPLETED"),
    REFUNDED("REFUNDED"),
    FAILED("FAILED"),
    CANCELED("CANCELED"),
    EXPIRED("EXPIRED");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }
    
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
    
    public static class Adapter extends TypeAdapter<StatusEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final StatusEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public StatusEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return StatusEnum.fromValue(String.valueOf(value));
      }
    }
  }
  
  @SerializedName("status")
  private StatusEnum status = null;
  
  @SerializedName("acceptedAt")
  private Integer acceptedAt = null;
  
  @SerializedName("refunds")
  private PaymentStateRefunds refunds = null;
  
  @SerializedName("authorizedAt")
  private Integer authorizedAt = null;
  
  @SerializedName("canceledAt")
  private Integer canceledAt = null;
  
  @SerializedName("captures")
  private PaymentStateCaptures captures = null;
  
  @SerializedName("revert")
  private PaymentStateRevert revert = null;
  
  public PaymentState paymentId(String paymentId) {
    this.paymentId = paymentId;
    return this;
  }

  
  /**
  * Get paymentId
  * @return paymentId
  **/
  @ApiModelProperty(value = "")
  public String getPaymentId() {
    return paymentId;
  }
  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }
  
  public PaymentState status(StatusEnum status) {
    this.status = status;
    return this;
  }

  
  /**
  * Get status
  * @return status
  **/
  @ApiModelProperty(value = "")
  public StatusEnum getStatus() {
    return status;
  }
  public void setStatus(StatusEnum status) {
    this.status = status;
  }
  
  public PaymentState acceptedAt(Integer acceptedAt) {
    this.acceptedAt = acceptedAt;
    return this;
  }

  
  /**
  * Get acceptedAt
  * @return acceptedAt
  **/
  @ApiModelProperty(value = "")
  public Integer getAcceptedAt() {
    return acceptedAt;
  }
  public void setAcceptedAt(Integer acceptedAt) {
    this.acceptedAt = acceptedAt;
  }
  
  public PaymentState refunds(PaymentStateRefunds refunds) {
    this.refunds = refunds;
    return this;
  }

  
  /**
  * Get refunds
  * @return refunds
  **/
  @ApiModelProperty(value = "")
  public PaymentStateRefunds getRefunds() {
    return refunds;
  }
  public void setRefunds(PaymentStateRefunds refunds) {
    this.refunds = refunds;
  }
  
  public PaymentState authorizedAt(Integer authorizedAt) {
    this.authorizedAt = authorizedAt;
    return this;
  }

  
  /**
  * Get authorizedAt
  * @return authorizedAt
  **/
  @ApiModelProperty(value = "")
  public Integer getAuthorizedAt() {
    return authorizedAt;
  }
  public void setAuthorizedAt(Integer authorizedAt) {
    this.authorizedAt = authorizedAt;
  }
  
  public PaymentState canceledAt(Integer canceledAt) {
    this.canceledAt = canceledAt;
    return this;
  }

  
  /**
  * Get canceledAt
  * @return canceledAt
  **/
  @ApiModelProperty(value = "")
  public Integer getCanceledAt() {
    return canceledAt;
  }
  public void setCanceledAt(Integer canceledAt) {
    this.canceledAt = canceledAt;
  }
  
  public PaymentState captures(PaymentStateCaptures captures) {
    this.captures = captures;
    return this;
  }

  
  /**
  * Get captures
  * @return captures
  **/
  @ApiModelProperty(value = "")
  public PaymentStateCaptures getCaptures() {
    return captures;
  }
  public void setCaptures(PaymentStateCaptures captures) {
    this.captures = captures;
  }
  
  public PaymentState revert(PaymentStateRevert revert) {
    this.revert = revert;
    return this;
  }

  
  /**
  * Get revert
  * @return revert
  **/
  @ApiModelProperty(value = "")
  public PaymentStateRevert getRevert() {
    return revert;
  }
  public void setRevert(PaymentStateRevert revert) {
    this.revert = revert;
  }
  
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentState paymentState = (PaymentState) o;
    return Objects.equals(this.paymentId, paymentState.paymentId) &&
        Objects.equals(this.status, paymentState.status) &&
        Objects.equals(this.acceptedAt, paymentState.acceptedAt) &&
        Objects.equals(this.refunds, paymentState.refunds) &&
        Objects.equals(this.authorizedAt, paymentState.authorizedAt) &&
        Objects.equals(this.canceledAt, paymentState.canceledAt) &&
        Objects.equals(this.captures, paymentState.captures) &&
        Objects.equals(this.revert, paymentState.revert);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paymentId, status, acceptedAt, refunds, authorizedAt, canceledAt, captures, revert);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentState {\n");
    
    sb.append("    paymentId: ").append(toIndentedString(paymentId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    acceptedAt: ").append(toIndentedString(acceptedAt)).append("\n");
    sb.append("    refunds: ").append(toIndentedString(refunds)).append("\n");
    sb.append("    authorizedAt: ").append(toIndentedString(authorizedAt)).append("\n");
    sb.append("    canceledAt: ").append(toIndentedString(canceledAt)).append("\n");
    sb.append("    captures: ").append(toIndentedString(captures)).append("\n");
    sb.append("    revert: ").append(toIndentedString(revert)).append("\n");
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



