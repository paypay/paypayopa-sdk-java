package jp.ne.paypay.api;

import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Call;
import jp.ne.paypay.ApiClient;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.ApiResponse;
import jp.ne.paypay.Configuration;
import jp.ne.paypay.Pair;
import jp.ne.paypay.Validator;
import jp.ne.paypay.model.Cashback;
import jp.ne.paypay.model.CashbackDetails;
import jp.ne.paypay.model.ReverseCashback;
import jp.ne.paypay.model.ReverseCashbackDetails;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;

public class CashbackApi {
    private ApiClient apiClient;
    private final Validator validator = Validator.getInstance();

    public CashbackApi() {
        this(new Configuration().getDefaultApiClient());
    }

    public CashbackApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Give Cashback to User
     * Transfer money from merchants campaign wallet to user wallet.  **Timeout: 30s**
     *
     * @param cashback Cashback
     * @return CashbackDetails
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public CashbackDetails createCashbackRequest(Cashback cashback) throws ApiException {
        ApiUtil.validateObject(validator, cashback);
        ApiResponse<CashbackDetails> resp = createCashbackRequestWithHttpInfo(cashback);
        return resp.getData();
    }

    /**
     * Give Cashback to User
     * Transfer money from merchants campaign wallet to user wallet.  **Timeout: 30s**
     *
     * @param cashback Cashback
     * @return ApiResponse&lt;CashbackDetails&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    protected ApiResponse<CashbackDetails> createCashbackRequestWithHttpInfo(Cashback cashback) throws ApiException {
        Call call = ApiUtil.postCallObject(apiClient, "/v2/cashback", cashback, null);
        Type localVarReturnType = new TypeToken<CashbackDetails>() {
        }.getType();
        return apiClient.execute(call, localVarReturnType, ApiNameConstants.CREATE_CASHBACK_REQUEST);
    }

    /**
     * Check Cashback Details
     * Check the cashback details of the cashback given  **Timeout: 15s**
     *
     * @param merchantCashbackId (required)
     * @return CashbackDetails
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public CashbackDetails getCashbackDetails(String merchantCashbackId) throws ApiException {
        ApiResponse<CashbackDetails> resp = getCashbackDetailsWithHttpInfo(merchantCashbackId);
        return resp.getData();
    }

    /**
     * Check Cashback Details
     * Check the cashback details of the cashback given  **Timeout: 15s**
     *
     * @param merchantCashbackId (required)
     * @return ApiResponse&lt;CashbackDetails&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    protected ApiResponse<CashbackDetails> getCashbackDetailsWithHttpInfo(String merchantCashbackId) throws ApiException {
        Call call = ApiUtil.getCallObject(apiClient, "/v2/cashback/{merchantCashbackId}", new Pair(ApiConstants.MERCHANT_CASHBACK_ID,
                merchantCashbackId), "GET");
        Type localVarReturnType = new TypeToken<CashbackDetails>() {
        }.getType();
        return apiClient.execute(call, localVarReturnType, ApiNameConstants.GET_CASHBACK_DETAILS);
    }

    /**
     * Reverse a given cashback
     * Transfer money back from user wallet to merchants campaign wallet.  **Timeout: 30s**
     *
     * @param reverseCashback ReverseCashback
     * @return ReverseCashbackDetails
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ReverseCashbackDetails createReverseCashbackRequest(ReverseCashback reverseCashback) throws ApiException {
        ApiUtil.validateObject(validator, reverseCashback);
        ApiResponse<ReverseCashbackDetails> resp = createReverseCashbackRequestWithHttpInfo(reverseCashback);
        return resp.getData();
    }

    /**
     * Reverse a given cashback
     * Transfer money back from user wallet to merchants campaign wallet.  **Timeout: 30s**
     *
     * @param reverseCashback ReverseCashback
     * @return ApiResponse&lt;ReverseCashbackDetails&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    protected ApiResponse<ReverseCashbackDetails> createReverseCashbackRequestWithHttpInfo(ReverseCashback reverseCashback) throws ApiException {
        Call call = ApiUtil.postCallObject(apiClient, "/v2/cashback_reversal", reverseCashback, null);
        Type localVarReturnType = new TypeToken<ReverseCashbackDetails>() {
        }.getType();
        return apiClient.execute(call, localVarReturnType, ApiNameConstants.CREATE_REVERSE_CASHBACK_REQUEST);
    }

    /**
     * Check Cashback Reversal Details
     * Check the cashback reversal details of the cashback reversed  **Timeout: 15s**
     *
     * @param merchantCashbackId (required)
     * @return ReverseCashbackDetails
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ReverseCashbackDetails getReversedCashbackDetails(String merchantCashbackReversalId, String merchantCashbackId) throws ApiException {
        ApiResponse<ReverseCashbackDetails> resp = getReversedCashbackDetailsWithHttpInfo(merchantCashbackReversalId, merchantCashbackId);
        return resp.getData();
    }

    /**
     * Check Cashback Reversal Details
     * Check the cashback reversal details of the cashback reversed  **Timeout: 15s**
     *
     * @param merchantCashbackReversalId (required)
     * @param merchantCashbackId (required)
     * @return ApiResponse&lt;ReverseCashbackDetails&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    protected ApiResponse<ReverseCashbackDetails> getReversedCashbackDetailsWithHttpInfo(String merchantCashbackReversalId, String merchantCashbackId) throws ApiException {
        if (StringUtils.isEmpty(merchantCashbackReversalId)) {
            throw new IllegalArgumentException("Missing the required parameter merchantCashbackReversalId");
        }
        Call call = ApiUtil.getCallObject(apiClient, "/v2/cashback_reversal/"+apiClient.escapeString(merchantCashbackReversalId)+"/{merchantCashbackId}", new Pair(ApiConstants.MERCHANT_CASHBACK_ID,
                merchantCashbackId), "GET");
        Type localVarReturnType = new TypeToken<ReverseCashbackDetails>() {
        }.getType();
        return apiClient.execute(call, localVarReturnType, ApiNameConstants.GET_REVERSED_CASHBACK_DETAILS);
    }
}
