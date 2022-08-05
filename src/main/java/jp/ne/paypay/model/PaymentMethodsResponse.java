package jp.ne.paypay.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class PaymentMethodsResponse {

    @SerializedName("resultInfo")
    private final ResultInfo resultInfo = null;

    @SerializedName("data")
    private final Data data = null;

    public ResultInfo getResultInfo() {
        return resultInfo;
    }

    public Data getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PaymentMethodsResponse paymentMethodsResponse = (PaymentMethodsResponse) o;
        return Objects.equals(this.resultInfo, paymentMethodsResponse.resultInfo) &&
            Objects.equals(this.data, paymentMethodsResponse.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultInfo, data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PaymentMethodsResponse {\n");
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

    public class Data {

        @SerializedName("walletInfo")
        private final WalletInfo walletInfo = null;

        @SerializedName("paymentMethods")
        private final List<PaymentMethod> paymentMethods = null;

        public WalletInfo getWalletInfo() {
            return walletInfo;
        }

        public List<PaymentMethod> getPaymentMethods() {
            return paymentMethods;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            PaymentMethodsResponse.Data another = (PaymentMethodsResponse.Data) o;
            return Objects.equals(this.walletInfo, another.walletInfo) &&
                Objects.equals(this.paymentMethods, another.paymentMethods);
        }

        @Override
        public int hashCode() {
            return Objects.hash(walletInfo, paymentMethods);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("class PaymentMethodsResponse.Data {\n");
            sb.append("    walletInfo: ").append(toIndentedString(walletInfo)).append("\n");
            sb.append("    paymentMethods: ").append(toIndentedString(paymentMethods)).append("\n");
            sb.append("}");
            return sb.toString();
        }

        private String toIndentedString(Object o) {
            if (o == null) {
                return "null";
            }
            return o.toString().replace("\n", "\n    ");
        }
    }
}
