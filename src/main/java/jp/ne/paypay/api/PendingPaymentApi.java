package jp.ne.paypay.api;

import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Call;
import jp.ne.paypay.ApiClient;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.ApiResponse;
import jp.ne.paypay.Configuration;
import jp.ne.paypay.Pair;
import jp.ne.paypay.Validator;
import jp.ne.paypay.model.NotDataResponse;
import jp.ne.paypay.model.Payment;
import jp.ne.paypay.model.PaymentDetails;
import jp.ne.paypay.model.Refund;
import jp.ne.paypay.model.RefundDetails;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PendingPaymentApi {
    private ApiClient apiClient;
    private PaymentApi paymentApi;
    private final Validator validator = Validator.getInstance();

    public PendingPaymentApi() {
        this(new Configuration().getDefaultApiClient());
    }

    public PendingPaymentApi(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.paymentApi = new PaymentApi(apiClient);
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.paymentApi = new PaymentApi(apiClient);
    }

    /**
     * Create a pending payment
     * Sends a push notification to the user requesting payment.  **Timeout: 30s**
     *
     * @param payment Payment
     * @return PaymentDetails
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public PaymentDetails createPendingPayment(Payment payment) throws ApiException {
        ApiUtil.validateObject(validator, payment);
        ApiResponse<PaymentDetails> resp = createPendingPaymentWithHttpInfo(payment);
        return resp.getData();
    }

    /**
     * Create a pending payment
     * Sends a push notification to the user requesting payment.  **Timeout: 30s**
     *
     * @param payment Payment
     * @return ApiResponse&lt;PaymentDetails&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    protected ApiResponse<PaymentDetails> createPendingPaymentWithHttpInfo(Payment payment) throws ApiException {
        Call call = ApiUtil.postCallObject(apiClient, "/v1/requestOrder", payment, null);
        Type localVarReturnType = new TypeToken<PaymentDetails>() {
        }.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Get payment details for pending payments
     * Get payment details for pending payments  **Timeout: 15s**
     *
     * @param merchantPaymentId (required)
     * @return PaymentDetails
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public PaymentDetails getPaymentDetails(String merchantPaymentId) throws ApiException {
        ApiResponse<PaymentDetails> resp = getPendingPaymentDetailsWithHttpInfo(merchantPaymentId);
        return resp.getData();
    }

    /**
     * Get payment details for pending payments
     * Get payment details for pending payments.  **Timeout: 15s**
     *
     * @param merchantPaymentId (required)
     * @return ApiResponse&lt;PaymentDetails&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    protected ApiResponse<PaymentDetails> getPendingPaymentDetailsWithHttpInfo(String merchantPaymentId) throws ApiException {
        Call call = getPaymentDetailsValidateBeforeCall(merchantPaymentId);
        Type localVarReturnType = new TypeToken<PaymentDetails>() {
        }.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    private Call getPaymentDetailsValidateBeforeCall(String merchantPaymentId) throws ApiException {
        return ApiUtil.getCallObject(apiClient, "/v1/requestOrder/{merchantPaymentId}", new Pair(Constants.MERCHANT_PAYMENT_ID,
                merchantPaymentId), "GET");
    }

    /**
     * Cancel a pending order
     * This api is used delete the pending order  **Timeout: 15s**
     *
     * @param merchantPaymentId (required)
     * @return NotDataResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public NotDataResponse cancelPendingOrder(String merchantPaymentId) throws ApiException {
        ApiResponse<NotDataResponse> resp = cancelPendingOrderWithHttpInfo(merchantPaymentId);
        return resp.getData();
    }

    /**
     * Cancel a pending order
     * This api is used delete the pending order.  **Timeout: 15s**
     *
     * @param merchantPaymentId (required)
     * @return ApiResponse&lt;NotDataResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    protected ApiResponse<NotDataResponse> cancelPendingOrderWithHttpInfo(String merchantPaymentId) throws ApiException {
        Call call = cancelPendingOrderValidateBeforeCall(merchantPaymentId);
        Type localVarReturnType = new TypeToken<NotDataResponse>() {
        }.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    private Call cancelPendingOrderValidateBeforeCall(String merchantPaymentId) throws ApiException {
        return ApiUtil.getCallObject(apiClient, "/v1/requestOrder/{merchantPaymentId}", new Pair(Constants.MERCHANT_PAYMENT_ID,
                merchantPaymentId), "DELETE");
    }

    /**
     * Refund a payment
     *
     * @param refund Refund
     * @return PaymentDetails
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public RefundDetails refundPayment(Refund refund) throws ApiException {
        ApiUtil.validateObject(validator, refund);
        ApiResponse<RefundDetails> resp = refundPaymentWithHttpInfo(refund);
        return resp.getData();
    }

    /**
     * Refund a payment
     *
     * @param refund Refund
     * @return ApiResponse&lt;PaymentDetails&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    protected ApiResponse<RefundDetails> refundPaymentWithHttpInfo(Refund refund) throws ApiException {
        Call call = refundPaymentCall(refund);
        Type localVarReturnType = new TypeToken<RefundDetails>() {
        }.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Build call for refundPayment
     *
     * @param refund Refund
     * @return Call to execute
     * @throws ApiException If fail to serialize the request payment object
     */
    private Call refundPaymentCall(Refund refund) throws ApiException {

        String localVarPath = "/v1/requestOrder/refunds";
        List<Pair> localVarQueryParams = new ArrayList<>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<>();
        Map<String, String> localVarHeaderParams = new HashMap<>();
        Map<String, Object> localVarFormParams = new HashMap<>();

        final String[] localVarAccepts = {
                Constants.APPLICATION_JSON
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put(Constants.ACCEPT, localVarAccept);

        final String[] localVarContentTypes = {

        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put(Constants.CONTENT_TYPE, localVarContentType);
        String[] localVarAuthNames = new String[]{Constants.HMAC_AUTH};
        apiClient.setReadTimeout(30);
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, refund, localVarHeaderParams, localVarFormParams, localVarAuthNames);
    }

    /**
     * Get refund details
     * Get refund details.  **Timeout: 15s**
     *
     * @param merchantRefundId (required)
     * @return RefundDetails
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public RefundDetails getRefundDetails(String merchantRefundId) throws ApiException {
        return paymentApi.getRefundDetails(merchantRefundId);
    }
}
