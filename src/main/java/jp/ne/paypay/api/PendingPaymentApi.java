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

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PendingPaymentApi {
    private ApiClient apiClient;
    private static final String APPLICATION_JSON = "application/json";
    private static final String ACCEPT = "Accept";
    private static final String MERCHANT_PAYMENT_ID = "merchantPaymentId";
    private static final String HMAC_AUTH = "HmacAuth";
    private static final String CONTENT_TYPE = "Content-Type";
    private final Validator validator = Validator.getInstance();

    public PendingPaymentApi() {
        this(new Configuration().getDefaultApiClient());
    }

    public PendingPaymentApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create a pending payment
     * Sends a push notification to the user requesting payment.  **Timeout: 30s**
     *
     * @param payment                    Payment
     * @return PaymentDetails
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public PaymentDetails createPendingPayment(Payment payment) throws ApiException {
        String message = validator.validate(payment);
        if (message != null) {
            throw new IllegalArgumentException(message);
        }
        ApiResponse<PaymentDetails> resp = createPendingPaymentWithHttpInfo(payment);
        return resp.getData();
    }

    /**
     * Create a pending payment
     * Sends a push notification to the user requesting payment.  **Timeout: 30s**
     *
     * @param payment                    Payment
     * @return ApiResponse&lt;PaymentDetails&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    protected ApiResponse<PaymentDetails> createPendingPaymentWithHttpInfo(Payment payment) throws ApiException {
        Call call = createPaymentCall(payment);
        Type localVarReturnType = new TypeToken<PaymentDetails>() {
        }.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Build call for PendingPayment
     *
     * @param payment                    Payment
     * @return Call to execute
     * @throws ApiException If fail to serialize the request payment object
     */
    private Call createPaymentCall(Payment payment) throws ApiException {

        // create path and map variables
        String localVarPath = "/v1/requestOrder";

        List<Pair> localVarQueryParams = new ArrayList<>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<>();
        Map<String, String> localVarHeaderParams = new HashMap<>();
        Map<String, Object> localVarFormParams = new HashMap<>();

        final String[] localVarAccepts = {
                APPLICATION_JSON
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put(ACCEPT, localVarAccept);

        final String[] localVarContentTypes = {

        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put(CONTENT_TYPE, localVarContentType);
        String[] localVarAuthNames = new String[]{HMAC_AUTH};
        apiClient.setReadTimeout(30);
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, payment, localVarHeaderParams, localVarFormParams, localVarAuthNames);
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

    /**
     * Build call for getPendingPaymentDetails
     *
     * @param merchantPaymentId (required)
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    private Call getPendingPaymentDetailsCall(String merchantPaymentId) throws ApiException {
        // create path and map variables
        String localVarPath = "/v1/requestOrder/{merchantPaymentId}"
                .replaceAll("\\{" + MERCHANT_PAYMENT_ID + "}", apiClient.escapeString(merchantPaymentId));

        List<Pair> localVarQueryParams = new ArrayList<>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<>();

        Map<String, String> localVarHeaderParams = new HashMap<>();

        Map<String, Object> localVarFormParams = new HashMap<>();

        final String[] localVarAccepts = {
                APPLICATION_JSON
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put(ACCEPT, localVarAccept);

        final String[] localVarContentTypes = {

        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put(CONTENT_TYPE, localVarContentType);
        String[] localVarAuthNames = new String[]{HMAC_AUTH};
        apiClient.setReadTimeout(15);
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams,
                null, localVarHeaderParams, localVarFormParams, localVarAuthNames);
    }

    private Call getPaymentDetailsValidateBeforeCall(String merchantPaymentId) throws ApiException {
        // verify the required parameter 'merchantPaymentId' is set
        if (merchantPaymentId == null) {
            throw new ApiException("Missing the required parameter 'merchantPaymentId' when calling getPaymentDetails");
        }
        return getPendingPaymentDetailsCall(merchantPaymentId);
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

    /**
     * Build call for cancelPendingOrder
     *
     * @param merchantPaymentId (required)
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    private Call cancelPendingOrderCall(String merchantPaymentId) throws ApiException {
        // create path and map variables
        String localVarPath = "/v1/requestOrder/{merchantPaymentId}"
                .replaceAll("\\{" + MERCHANT_PAYMENT_ID + "}", apiClient.escapeString(merchantPaymentId));

        List<Pair> localVarQueryParams = new ArrayList<>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<>();

        Map<String, String> localVarHeaderParams = new HashMap<>();

        Map<String, Object> localVarFormParams = new HashMap<>();

        final String[] localVarAccepts = {
                APPLICATION_JSON
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put(ACCEPT, localVarAccept);

        final String[] localVarContentTypes = {

        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put(CONTENT_TYPE, localVarContentType);
        String[] localVarAuthNames = new String[]{HMAC_AUTH};
        apiClient.setReadTimeout(15);
        return apiClient.buildCall(localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, null, localVarHeaderParams, localVarFormParams, localVarAuthNames);
    }

    private Call cancelPendingOrderValidateBeforeCall(String merchantPaymentId) throws ApiException {
        // verify the required parameter 'merchantPaymentId' is set
        if (merchantPaymentId == null) {
            throw new ApiException("Missing the required parameter 'merchantPaymentId' when calling cancelPayment");
        }
        return cancelPendingOrderCall(merchantPaymentId);
    }

}
