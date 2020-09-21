package jp.ne.paypay.api;

import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Call;
import jp.ne.paypay.ApiClient;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.ApiResponse;
import jp.ne.paypay.Configuration;
import jp.ne.paypay.Pair;
import jp.ne.paypay.Validator;
import jp.ne.paypay.model.AccountLinkQRCode;
import jp.ne.paypay.model.CaptureObject;
import jp.ne.paypay.model.LinkQRCodeResponse;
import jp.ne.paypay.model.NotDataResponse;
import jp.ne.paypay.model.Payment;
import jp.ne.paypay.model.PaymentDetails;
import jp.ne.paypay.model.QRCode;
import jp.ne.paypay.model.QRCodeDetails;
import jp.ne.paypay.model.Refund;
import jp.ne.paypay.model.RefundDetails;
import jp.ne.paypay.model.RevertAuthResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentApi {
    private ApiClient apiClient;

    private final Validator validator = Validator.getInstance();

    public PaymentApi() {
        this(new Configuration().getDefaultApiClient());
    }

    public PaymentApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }


    private Call cancelPaymentValidateBeforeCall(String merchantPaymentId) throws ApiException {
        // verify the required parameter 'merchantPaymentId' is set
        if (merchantPaymentId == null) {
            throw new ApiException("Missing the required parameter 'merchantPaymentId' when calling cancelPayment");
        }
        return ApiUtil.getCallObject(apiClient, "/v2/payments/{merchantPaymentId}", Constants.MERCHANT_PAYMENT_ID,
                merchantPaymentId, "DELETE");
    }

    /**
     * Cancel a payment
     * This api is used in case, while creating a payment, the client can not determine the status of the payment. For example, client get timeout or the response cannot contain the information to indicate the exact payment status.  By calling this api, if accepted, the OPA will guarantee the money eventually goes back to user&#x27;s account.  &lt;/br&gt;&lt;b style&#x3D;\&quot;color:red\&quot;&gt;Note:&lt;/b&gt; The Cancel API can be used until 00:14:59 AM the day after the Payment has happened. &lt;/br&gt;For 00:15 AM or later, please call the refund API to refund the payment.  **Timeout: 15s**
     *
     * @param merchantPaymentId (required)
     * @return NotDataResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public NotDataResponse cancelPayment(String merchantPaymentId) throws ApiException {
        ApiResponse<NotDataResponse> resp = cancelPaymentWithHttpInfo(merchantPaymentId);
        return resp.getData();
    }

    /**
     * Cancel a payment
     * This api is used in case, while creating a payment, the client can not determine the status of the payment. For example, client get timeout or the response cannot contain the information to indicate the exact payment status.  By calling this api, if accepted, the OPA will guarantee the money eventually goes back to user&#x27;s account.  &lt;/br&gt;&lt;b style&#x3D;\&quot;color:red\&quot;&gt;Note:&lt;/b&gt; The Cancel API can be used until 00:14:59 AM the day after the Payment has happened. &lt;/br&gt;For 00:15 AM or later, please call the refund API to refund the payment.  **Timeout: 15s**
     *
     * @param merchantPaymentId (required)
     * @return ApiResponse&lt;NotDataResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    protected ApiResponse<NotDataResponse> cancelPaymentWithHttpInfo(String merchantPaymentId) throws ApiException {
        Call call = cancelPaymentValidateBeforeCall(merchantPaymentId);
        Type localVarReturnType = new TypeToken<NotDataResponse>() {
        }.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Build call for capturePaymentAuth
     *
     * @param body (optional)
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    private Call capturePaymentAuthCall(Object body) throws ApiException {

        // create path and map variables
        String capturePaymentUrl = "/v2/payments/capture";

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
        return apiClient.buildCall(capturePaymentUrl, "POST", localVarQueryParams, localVarCollectionQueryParams, body, localVarHeaderParams, localVarFormParams, localVarAuthNames);
    }

    private Call capturePaymentAuthValidateBeforeCall(Object body) throws ApiException {
        return capturePaymentAuthCall(body);
    }

    /**
     * Capture a payment authorization
     * This api is used to capture the payment authorization for a payment  **Timeout: 30s**
     *
     * @param body (optional)
     * @return PaymentDetails
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public PaymentDetails capturePaymentAuth(CaptureObject body) throws ApiException {
        String message = validator.validate(body);
        if (message != null)
            throw new IllegalArgumentException(message);
        ApiResponse<PaymentDetails> resp = capturePaymentAuthWithHttpInfo(body);
        return resp.getData();
    }

    /**
     * Capture a payment authorization
     * This api is used to capture the payment authorization for a payment  **Timeout: 30s**
     *
     * @param body (optional)
     * @return ApiResponse&lt;PaymentDetails&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    protected ApiResponse<PaymentDetails> capturePaymentAuthWithHttpInfo(Object body) throws ApiException {
        Call call = capturePaymentAuthValidateBeforeCall(body);
        Type localVarReturnType = new TypeToken<PaymentDetails>() {
        }.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Build call for createPayment
     *
     * @param body                    Payment (optional)
     * @param agreeSimilarTransaction (Optional) If the parameter is set to \&quot;true\&quot;, the payment duplication check will be bypassed.  (optional)
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    private Call createPaymentCall(Object body, String agreeSimilarTransaction) throws ApiException {

        // create path and map variables
        String createPaymentUrl = "/v2/payments";

        List<Pair> localVarQueryParams = new ArrayList<>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<>();
        if (agreeSimilarTransaction != null)
            localVarQueryParams.addAll(apiClient.parameterToPair("agreeSimilarTransaction", agreeSimilarTransaction));

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
        return apiClient.buildCall(createPaymentUrl, "POST", localVarQueryParams, localVarCollectionQueryParams, body, localVarHeaderParams, localVarFormParams, localVarAuthNames);
    }

    private Call createPaymentValidateBeforeCall(Object body, String agreeSimilarTransaction) throws ApiException {
        return createPaymentCall(body, agreeSimilarTransaction);
    }

    /**
     * Create a payment
     * Create a direct debit payment and start the money transfer.  **Timeout: 30s**
     *
     * @param body                    Payment (optional)
     * @param agreeSimilarTransaction (Optional) If the parameter is set to \&quot;true\&quot;, the payment duplication check will be bypassed.  (optional)
     * @return PaymentDetails
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public PaymentDetails createPayment(Payment body, String agreeSimilarTransaction) throws ApiException {
        String message = validator.validate(body);
        if (message != null) {
            throw new IllegalArgumentException(message);
        }
        ApiResponse<PaymentDetails> resp = createPaymentWithHttpInfo(body, agreeSimilarTransaction);
        return resp.getData();
    }

    /**
     * Create a payment
     * Create a direct debit payment and start the money transfer.  **Timeout: 30s**
     *
     * @param body                    Payment (optional)
     * @param agreeSimilarTransaction (Optional) If the parameter is set to \&quot;true\&quot;, the payment duplication check will be bypassed.  (optional)
     * @return ApiResponse&lt;PaymentDetails&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    protected ApiResponse<PaymentDetails> createPaymentWithHttpInfo(Object body, String agreeSimilarTransaction) throws ApiException {
        Call call = createPaymentValidateBeforeCall(body, agreeSimilarTransaction);
        Type localVarReturnType = new TypeToken<PaymentDetails>() {
        }.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Build call for createQRCode
     *
     * @param body Code Creation (optional)
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    private Call createQRCodeCall(Object body) throws ApiException {

        // create path and map variables
        String createQrCodeUrl = "/v2/codes";

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
        return apiClient.buildCall(createQrCodeUrl, "POST", localVarQueryParams, localVarCollectionQueryParams, body,
                localVarHeaderParams, localVarFormParams, localVarAuthNames);
    }

    private Call createQRCodeValidateBeforeCall(Object body) throws ApiException {
        return createQRCodeCall(body);
    }

    /**
     * Create a Code
     * Create a Code to receive payments.  **Timeout: 30s**
     *
     * @param body Code Creation (optional)
     * @return QRCodeDetails
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public QRCodeDetails createQRCode(QRCode body) throws ApiException {
        String message = validator.validate(body);
        if (message!=null) {
            throw new IllegalArgumentException(message);
        }
        ApiResponse<QRCodeDetails> resp = createQRCodeWithHttpInfo(body);
        return resp.getData();
    }

    /**
     * Create a Code
     * Create a Code to receive payments.  **Timeout: 30s**
     *
     * @param body Code Creation (optional)
     * @return ApiResponse&lt;QRCodeDetails&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    protected ApiResponse<QRCodeDetails> createQRCodeWithHttpInfo(Object body) throws ApiException {
        Call call = createQRCodeValidateBeforeCall(body);
        Type localVarReturnType = new TypeToken<QRCodeDetails>() {
        }.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    private Call deleteQRCodeValidateBeforeCall(String codeId) throws ApiException {
        // verify the required parameter 'codeId' is set
        if (codeId == null) {
            throw new ApiException("Missing the required parameter 'codeId' when calling deleteQRCode");
        }
        return ApiUtil.getCallObject(apiClient, "/v2/codes/{codeId}", Constants.CODE_ID,
                codeId, "DELETE");
    }

    /**
     * Delete a Code
     * Delete a created Code.  **Timeout: 15s**
     *
     * @param codeId (required)
     * @return NotDataResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public NotDataResponse deleteQRCode(String codeId) throws ApiException {
        ApiResponse<NotDataResponse> resp = deleteQRCodeWithHttpInfo(codeId);
        return resp.getData();
    }

    /**
     * Delete a Code
     * Delete a created Code.  **Timeout: 15s**
     *
     * @param codeId (required)
     * @return ApiResponse&lt;NotDataResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    protected ApiResponse<NotDataResponse> deleteQRCodeWithHttpInfo(String codeId) throws ApiException {
        Call call = deleteQRCodeValidateBeforeCall(codeId);
        Type localVarReturnType = new TypeToken<NotDataResponse>() {
        }.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    private Call getPaymentDetailsValidateBeforeCall(String merchantPaymentId) throws ApiException {
        // verify the required parameter 'merchantPaymentId' is set
        if (merchantPaymentId == null) {
            throw new ApiException("Missing the required parameter 'merchantPaymentId' when calling getPaymentDetails");
        }
        return ApiUtil.getCallObject(apiClient, "/v2/payments/{merchantPaymentId}", Constants.MERCHANT_PAYMENT_ID,
                merchantPaymentId, "GET");
    }

    /**
     * Get payment details
     * Get payment details.  **Timeout: 15s**
     *
     * @param merchantPaymentId (required)
     * @return PaymentDetails
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public PaymentDetails getPaymentDetails(String merchantPaymentId) throws ApiException {
        ApiResponse<PaymentDetails> resp = getPaymentDetailsWithHttpInfo(merchantPaymentId);
        return resp.getData();
    }

    /**
     * Get payment details
     * Get payment details.  **Timeout: 15s**
     *
     * @param merchantPaymentId (required)
     * @return ApiResponse&lt;PaymentDetails&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    protected ApiResponse<PaymentDetails> getPaymentDetailsWithHttpInfo(String merchantPaymentId) throws ApiException {
        Call call = getPaymentDetailsValidateBeforeCall(merchantPaymentId);
        Type localVarReturnType = new TypeToken<PaymentDetails>() {
        }.getType();
        return apiClient.execute(call, localVarReturnType);
    }



    private Call getCodesPaymentDetailsValidateBeforeCall(String merchantPaymentId) throws ApiException {
        // verify the required parameter 'merchantPaymentId' is set
        if (merchantPaymentId == null) {
            throw new ApiException("Missing the required parameter 'merchantPaymentId' when calling "
                    + "getCodesPaymentDetails");
        }
        return ApiUtil.getCallObject(apiClient, "/v2/codes/payments/{merchantPaymentId}", Constants.MERCHANT_PAYMENT_ID,
                merchantPaymentId, "GET");
    }

    /**
     * Get payment details
     * Get payment details.  **Timeout: 15s**
     *
     * @param merchantPaymentId (required)
     * @return PaymentDetails
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public PaymentDetails getCodesPaymentDetails(String merchantPaymentId) throws ApiException {
        ApiResponse<PaymentDetails> resp = getCodesPaymentDetailsWithHttpInfo(merchantPaymentId);
        return resp.getData();
    }

    /**
     * Get payment details
     * Get payment details.  **Timeout: 15s**
     *
     * @param merchantPaymentId (required)
     * @return ApiResponse&lt;PaymentDetails&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    protected ApiResponse<PaymentDetails> getCodesPaymentDetailsWithHttpInfo(String merchantPaymentId) throws ApiException {
        Call call = getCodesPaymentDetailsValidateBeforeCall(merchantPaymentId);
        Type localVarReturnType = new TypeToken<PaymentDetails>() {
        }.getType();
        return apiClient.execute(call, localVarReturnType);
    }



    private Call getRefundDetailsValidateBeforeCall(String merchantRefundId) throws ApiException {
        // verify the required parameter 'merchantRefundId' is set
        if (merchantRefundId == null) {
            throw new ApiException("Missing the required parameter 'merchantRefundId' when calling getRefundDetails");
        }
        return ApiUtil.getCallObject(apiClient, "/v2/refunds/{merchantRefundId}", Constants.MERCHANT_REFUND_ID,
                merchantRefundId, "GET");
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
        ApiResponse<RefundDetails> resp = getRefundDetailsWithHttpInfo(merchantRefundId);
        return resp.getData();
    }

    /**
     * Get refund details
     * Get refund details.  **Timeout: 15s**
     *
     * @param merchantRefundId (required)
     * @return ApiResponse&lt;RefundDetails&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    protected ApiResponse<RefundDetails> getRefundDetailsWithHttpInfo(String merchantRefundId) throws ApiException {
        Call call = getRefundDetailsValidateBeforeCall(merchantRefundId);
        Type localVarReturnType = new TypeToken<RefundDetails>() {
        }.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Build call for refundPayment
     *
     * @param body Refund (optional)
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    private Call refundPaymentCall(Object body) throws ApiException {

        // create path and map variables
        String refundPaymentUrl = "/v2/refunds";

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
        return apiClient.buildCall(refundPaymentUrl, "POST", localVarQueryParams, localVarCollectionQueryParams, body, localVarHeaderParams, localVarFormParams, localVarAuthNames);
    }

    private Call refundPaymentValidateBeforeCall(Object body) throws ApiException {
        return refundPaymentCall(body);
    }

    /**
     * Refund a payment
     * Refund a payment.  **Timeout: 30s**
     *
     * @param body Refund (optional)
     * @return RefundDetails
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public RefundDetails refundPayment(Refund body) throws ApiException {
        ApiUtil.validateObject(validator, body);
        ApiResponse<RefundDetails> resp = refundPaymentWithHttpInfo(body);
        return resp.getData();
    }

    /**
     * Refund a payment
     * Refund a payment.  **Timeout: 30s**
     *
     * @param body Refund (optional)
     * @return ApiResponse&lt;RefundDetails&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    protected ApiResponse<RefundDetails> refundPaymentWithHttpInfo(Object body) throws ApiException {
        Call call = refundPaymentValidateBeforeCall(body);
        Type localVarReturnType = new TypeToken<RefundDetails>() {
        }.getType();
        return apiClient.execute(call, localVarReturnType);
    }


    /**
     * Build call for revertAuth
     *
     * @param body Revert Authorized Order Request (optional)
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    private Call revertAuthCall(Object body) throws ApiException {

        // create path and map variables
        String revertAuthUrl = "/v2/payments/preauthorize/revert";

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
        return apiClient.buildCall(revertAuthUrl, "POST", localVarQueryParams, localVarCollectionQueryParams, body, localVarHeaderParams, localVarFormParams, localVarAuthNames);
    }

    private Call revertAuthValidateBeforeCall(Object body) throws ApiException {
        return revertAuthCall(body);
    }

    /**
     * Revert a payment authorization
     * This api is used in case, the merchant wants to cancel the payment authorization because of cancellation of the order by the user.  **Timeout: 30s**
     *
     * @param body Revert Authorized Order Request (optional)
     * @return RevertAuthResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public RevertAuthResponse revertAuth(Object body) throws ApiException {
        ApiResponse<RevertAuthResponse> resp = revertAuthWithHttpInfo(body);
        return resp.getData();
    }

    /**
     * Revert a payment authorization
     * This api is used in case, the merchant wants to cancel the payment authorization because of cancellation of the order by the user.  **Timeout: 30s**
     *
     * @param body Revert Authorized Order Request (optional)
     * @return ApiResponse&lt;RevertAuthResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    protected ApiResponse<RevertAuthResponse> revertAuthWithHttpInfo(Object body) throws ApiException {
        Call call = revertAuthValidateBeforeCall(body);
        Type localVarReturnType = new TypeToken<RevertAuthResponse>() {
        }.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Create a Account Link QRCode
     * Create a ACCOUNT LINK QR and display it to the user.  **Timeout: 10s**
     *
     * @param body Account Link Code Creation
     * @return LinkQRCodeResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public LinkQRCodeResponse createAccountLinkQRCode(AccountLinkQRCode body) throws ApiException {
        ApiUtil.validateObject(validator, body);
        ApiResponse<LinkQRCodeResponse> resp = createAccountLinkQRCodeWithHttpInfo(body);
        return resp.getData();
    }

    /**
     * Create an Account Link QRCode
     * Create an ACCOUNT LINK QR and display it to the user.  **Timeout: 10s**
     *
     * @param body Account Link Code Creation
     * @return ApiResponse&lt;LinkQRCodeResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    protected ApiResponse<LinkQRCodeResponse> createAccountLinkQRCodeWithHttpInfo(Object body) throws ApiException {
        Call call = createAccountLinkQRCodeCall(body);
        Type localVarReturnType = new TypeToken<LinkQRCodeResponse>() {
        }.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Build call for CreateAccountLinkQRCode
     *
     * @param body Account Link Code Creation
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    private Call createAccountLinkQRCodeCall(Object body) throws ApiException {

        // create path and map variables
        String createAccountLinkUrl = "/v1/qr/sessions";

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
        apiClient.setReadTimeout(10);
        return apiClient.buildCall(createAccountLinkUrl, "POST", localVarQueryParams, localVarCollectionQueryParams, body,
                localVarHeaderParams, localVarFormParams, localVarAuthNames);
    }

    /**
     * Create a payment authorization
     * Create a payment authorization to block the money.  **Timeout: 30s**
     *
     * @param body                    Payment
     * @param agreeSimilarTransaction (Optional) If the parameter is set to \&quot;true\&quot;, the payment duplication check will be bypassed.  (optional)
     * @return PaymentDetails
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public PaymentDetails createPaymentAuthorization(Payment body, String agreeSimilarTransaction) throws ApiException {
        ApiUtil.validateObject(validator, body);
        ApiResponse<PaymentDetails> resp = createPaymentAuthorizationWithHttpInfo(body, agreeSimilarTransaction);
        return resp.getData();
    }

    /**
     * Create a payment authorization
     * Create a payment authorization to block the money.  **Timeout: 30s**
     *
     * @param body                    Payment
     * @param agreeSimilarTransaction (Optional) If the parameter is set to \&quot;true\&quot;, the payment duplication check will be bypassed.  (optional)
     * @return ApiResponse&lt;PaymentDetails&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    protected ApiResponse<PaymentDetails> createPaymentAuthorizationWithHttpInfo(Object body, String agreeSimilarTransaction) throws ApiException {
        Call call = createPaymentAuthorizationCall(body, agreeSimilarTransaction);
        Type localVarReturnType = new TypeToken<PaymentDetails>() {
        }.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Build call for createPaymentAuthorization
     *
     * @param body                    Payment
     * @param agreeSimilarTransaction (Optional) If the parameter is set to \&quot;true\&quot;, the payment duplication check will be bypassed.  (optional)
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    private Call createPaymentAuthorizationCall(Object body, String agreeSimilarTransaction) throws ApiException {

        // create path and map variables
        String localVarPath = "/v2/payments/preauthorize";

        List<Pair> localVarQueryParams = new ArrayList<>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<>();
        if (agreeSimilarTransaction != null)
            localVarQueryParams.addAll(apiClient.parameterToPair("agreeSimilarTransaction", agreeSimilarTransaction));

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
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, body, localVarHeaderParams, localVarFormParams, localVarAuthNames);
    }

    /**
     * Create a continuous payment
     * Create a continuous payment and start the money transfer..  **Timeout: 30s**
     *
     * @param body                    Payment
     * @return PaymentDetails
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public PaymentDetails createContinuousPayment(Payment body) throws ApiException {
        ApiUtil.validateObject(validator, body);
        ApiResponse<PaymentDetails> resp = createContinuousPaymentWithHttpInfo(body);
        return resp.getData();
    }

    /**
     * Create a continuous payment
     * Create a continuous payment and start the money transfer.  **Timeout: 30s**
     *
     * @param body                    Payment
     * @return ApiResponse&lt;PaymentDetails&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    protected ApiResponse<PaymentDetails> createContinuousPaymentWithHttpInfo(Object body) throws ApiException {
        Call call = createContinuousPaymentCall(body);
        Type localVarReturnType = new TypeToken<PaymentDetails>() {
        }.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Build call for createContinuousPayment
     *
     * @param body                    Payment
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    private Call createContinuousPaymentCall(Object body) throws ApiException {

        // create path and map variables
        String continuousPaymentUrl = "/v1/subscription/payments";

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
        return apiClient.buildCall(continuousPaymentUrl, "POST", localVarQueryParams, localVarCollectionQueryParams, body, localVarHeaderParams, localVarFormParams, localVarAuthNames);
    }
}
