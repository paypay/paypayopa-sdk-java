package jp.ne.paypay.api;

import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Call;
import jp.ne.paypay.ApiClient;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.ApiResponse;
import jp.ne.paypay.Configuration;
import jp.ne.paypay.Pair;
import jp.ne.paypay.model.ProductType;
import jp.ne.paypay.model.WalletBalance;
import jp.ne.paypay.model.GetWalletBalance;

import jakarta.validation.constraints.NotNull;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WalletApi {
    private ApiClient apiClient;

    public WalletApi() {
        this(new Configuration().getDefaultApiClient());
    }

    public WalletApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    
    /**
     * Build call for checkWalletBalance
     * @param userAuthorizationId  (required)
     * @param amount  (required)
     * @param currency  (required)
     * @param productType  (optional)
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
        
     */
    private Call walletBalanceCall(String endpoint, String userAuthorizationId, Integer amount, String currency,
            ProductType productType) throws ApiException {

        List<Pair> localVarQueryParams = new ArrayList<>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<>();
        if (userAuthorizationId != null)
            localVarQueryParams.addAll(apiClient.parameterToPair("userAuthorizationId", userAuthorizationId));
        if (amount != null)
            localVarQueryParams.addAll(apiClient.parameterToPair("amount", amount));
        if (currency != null)
            localVarQueryParams.addAll(apiClient.parameterToPair("currency", currency));
        if (productType != null)
            localVarQueryParams.addAll(apiClient.parameterToPair("productType", productType));

        Map<String, String> localVarHeaderParams = new HashMap<>();

        Map<String, Object> localVarFormParams = new HashMap<>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "HmacAuth" };
        apiClient.setReadTimeout(15);
        return apiClient.buildCall(endpoint, "GET", localVarQueryParams, localVarCollectionQueryParams,
                null, localVarHeaderParams, localVarFormParams, localVarAuthNames);
    }
    
    private Call validateCheckWalletBalanceParamsBeforeCall(String endpoint, String userAuthorizationId, Integer amount, String currency, ProductType productType) throws ApiException {
        
        // verify the required parameter 'userAuthorizationId' is set
        if (userAuthorizationId == null) {
            throw new ApiException("Missing the required parameter 'userAuthorizationId' when calling checkWalletBalance(Async)");
        }
        
        // verify the required parameter 'amount' is set
        if (amount == null) {
            throw new ApiException("Missing the required parameter 'amount' when calling checkWalletBalance(Async)");
        }
        
        // verify the required parameter 'currency' is set
        if (currency == null) {
            throw new ApiException("Missing the required parameter 'currency' when calling checkWalletBalance(Async)");
        }
        
        return walletBalanceCall(endpoint, userAuthorizationId, amount, currency, productType);
    }

    private Call validateGetWalletBalanceParamsBeforeCall(String endpoint, String userAuthorizationId, String currency, ProductType productType) throws ApiException {

        // verify the required parameter 'userAuthorizationId' is set
        if (userAuthorizationId == null) {
            throw new ApiException("Missing the required parameter 'userAuthorizationId' when calling checkWalletBalance(Async)");
        }
        
        // verify the required parameter 'currency' is set
        if (currency == null) {
            throw new ApiException("Missing the required parameter 'currency' when calling checkWalletBalance(Async)");
        }

        return walletBalanceCall(endpoint, userAuthorizationId, null, currency, productType);
    }

    /**
     * Check user wallet balance
     * Check if user has enough balance to make a payment  **Timeout: 15s**
     * @param userAuthorizationId  (required)
     * @param amount  (required)
     * @param currency  (required)
     * @param productType  (optional)
     * @return WalletBalance
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public WalletBalance checkWalletBalance( String userAuthorizationId, Integer amount, String currency, @NotNull ProductType productType) throws ApiException {
        ApiResponse<WalletBalance> resp = checkWalletBalanceWithHttpInfo(userAuthorizationId, amount, currency, productType);
        return resp.getData();
    }

    /**
     * Check user wallet balance
     * Check if user has enough balance to make a payment  **Timeout: 15s**
     * @param userAuthorizationId  (required)
     * @param amount  (required)
     * @param currency  (required)
     * @param productType  (optional)
     * @return ApiResponse&lt;WalletBalance&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    protected ApiResponse<WalletBalance> checkWalletBalanceWithHttpInfo(String userAuthorizationId, Integer amount,
            String currency, ProductType productType) throws ApiException {
        Call call = validateCheckWalletBalanceParamsBeforeCall("/v2/wallet/check_balance", userAuthorizationId, amount, currency, productType);
        Type localVarReturnType = new TypeToken<WalletBalance>(){}.getType();
        return apiClient.execute(call, localVarReturnType, ApiNameConstants.CHECK_BALANCE);
    }

    /**
     * Get user wallet balance
     * Get the user's total balance and preference  **Timeout: 15s**
     * @param userAuthorizationId  (required)
     * @param currency  (required)
     * @param productType  (optional)
     * @return GetWalletBalance
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public GetWalletBalance getWalletBalance(String userAuthorizationId, String currency, @NotNull ProductType productType) throws ApiException {
        ApiResponse<GetWalletBalance> resp = getWalletBalanceWithHttpInfo(userAuthorizationId, currency, productType);
        return resp.getData();
    }

    /**
     * Get user wallet balance
     * Get the user's total balance and preference  **Timeout: 15s**
     * @param userAuthorizationId  (required)
     * @param currency  (required)
     * @param productType  (optional)
     * @return ApiResponse&lt;GetWalletBalance&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    protected ApiResponse<GetWalletBalance> getWalletBalanceWithHttpInfo(String userAuthorizationId,
            String currency, ProductType productType) throws ApiException {
        Call call = validateGetWalletBalanceParamsBeforeCall("/v6/wallet/balance", userAuthorizationId, currency, productType);
        Type localVarReturnType = new TypeToken<GetWalletBalance>(){}.getType();
        return apiClient.execute(call, localVarReturnType, ApiNameConstants.GET_BALANCE);
    }
}