package jp.ne.paypay.model;

import com.google.gson.annotations.SerializedName;

import java.time.Instant;
import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Refund
 */

public class Refund extends RefundState {

    @SerializedName("merchantRefundId")
    @NotEmpty(message = "merchantRefundId is required")
    @Size(max = 64, message = "maximum 64 characters are allowed for merchantRefundtId")
    private String merchantRefundId = null;

    @SerializedName("paymentId")
    @NotEmpty(message = "paymentId is required")
    @Size(max = 64, message = "mmaximum 64 characters are allowed for paymentId")
    private String paymentId = null;

    @SerializedName("amount")
    @NotNull(message = "amount is required")
    private MoneyAmount amount = null;

    @SerializedName("requestedAt")
    @NotNull(message = "requestedAt is required")
    private Long requestedAt = Instant.now().getEpochSecond();

    @SerializedName("reason")
    @Size(max = 255, message = "maximum 255 characters allowed for reason")
    private String reason = null;

    @SerializedName("assumeMerchant")
    private String assumeMerchant = null;

    public Refund merchantRefundId(String merchantRefundId) {
        this.merchantRefundId = merchantRefundId;
        return this;
    }


    /**
     * Get merchantRefundId
     *
     * @return merchantRefundId
     **/
    @ApiModelProperty(value = "")
    public String getMerchantRefundId() {
        return merchantRefundId;
    }

    public void setMerchantRefundId(String merchantRefundId) {
        this.merchantRefundId = merchantRefundId;
    }

    public Refund paymentId(String paymentId) {
        this.paymentId = paymentId;
        return this;
    }


    /**
     * Get paymentId
     *
     * @return paymentId
     **/
    @ApiModelProperty(value = "")
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Refund amount(MoneyAmount amount) {
        this.amount = amount;
        return this;
    }


    /**
     * Get amount
     *
     * @return amount
     **/
    @ApiModelProperty(value = "")
    public MoneyAmount getAmount() {
        return amount;
    }

    public void setAmount(MoneyAmount amount) {
        this.amount = amount;
    }

    public Refund requestedAt(Long requestedAt) {
        this.requestedAt = requestedAt;
        return this;
    }


    /**
     * Get requestedAt
     *
     * @return requestedAt
     **/
    @ApiModelProperty(value = "")
    public Long getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(Long requestedAt) {
        this.requestedAt = requestedAt;
    }

    public Refund reason(String reason) {
        this.reason = reason;
        return this;
    }


    /**
     * Get reason
     *
     * @return reason
     **/
    @ApiModelProperty(value = "")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Refund assumeMerchant(String assumeMerchant) {
        this.assumeMerchant = assumeMerchant;
        return this;
    }


    /**
     * Get assumeMerchant
     *
     * @return assumeMerchant
     **/
    @ApiModelProperty(value = "")
    public String getAssumeMerchant() {
        return assumeMerchant;
    }

    public void setAssumeMerchant(String assumeMerchant) {
        this.assumeMerchant = assumeMerchant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Refund refund = (Refund) o;
        return Objects.equals(this.merchantRefundId, refund.merchantRefundId) &&
                Objects.equals(this.paymentId, refund.paymentId) &&
                Objects.equals(this.amount, refund.amount) &&
                Objects.equals(this.requestedAt, refund.requestedAt) &&
                Objects.equals(this.reason, refund.reason) &&
                Objects.equals(this.assumeMerchant, refund.assumeMerchant) &&
                super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(merchantRefundId, paymentId, amount, requestedAt, reason, assumeMerchant, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Refund {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    merchantRefundId: ").append(toIndentedString(merchantRefundId)).append("\n");
        sb.append("    paymentId: ").append(toIndentedString(paymentId)).append("\n");
        sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
        sb.append("    requestedAt: ").append(toIndentedString(requestedAt)).append("\n");
        sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
        sb.append("    assumeMerchant: ").append(toIndentedString(assumeMerchant)).append("\n");
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



