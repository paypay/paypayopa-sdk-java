package jp.ne.paypay.api;

import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Call;
import jp.ne.paypay.ApiClient;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.ApiResponse;
import jp.ne.paypay.Configuration;
import jp.ne.paypay.Pair;
import jp.ne.paypay.Validator;
import jp.ne.paypay.model.MaskedUserProfileResponse;
import jp.ne.paypay.model.NotDataResponse;
import jp.ne.paypay.model.UserAuthorizationStatus;
import jp.ne.paypay.model.UserCashbackSettingStatus;
import jp.ne.paypay.model.UserCashbackUseStatus;

import java.lang.reflect.Type;



public class UserApi {
    private ApiClient apiClient;

    private final Validator validator = Validator.getInstance();

    public UserApi() {
        this(new Configuration().getDefaultApiClient());
    }

    public UserApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    private Call getMaskedUserProfileValidateBeforeCall(String userAuthorizationId) throws ApiException {
        return ApiUtil.getCallObject(apiClient, "/v2/user/profile/secure?userAuthorizationId={userAuthorizationId}", new Pair(ApiConstants.USER_AUTHORIZATION_ID,
                userAuthorizationId), "GET");
    }

    /**
     * Get masked user profile
     * Get the masked phone number of the user 
     * @param userAuthorizationId  (required)
     * @return MaskedUserProfileResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
        
     */
    public MaskedUserProfileResponse getMaskedUserProfile(String userAuthorizationId) throws ApiException {
        ApiResponse<MaskedUserProfileResponse> resp = getMaskedUserProfileWithHttpInfo(userAuthorizationId);
        return resp.getData();
    }

    /**
     * Get masked user profile
     * Get the masked phone number of the user 
     * @param userAuthorizationId  (required)
     * @return ApiResponse&lt;MaskedUserProfileResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
        
     */
    protected ApiResponse<MaskedUserProfileResponse> getMaskedUserProfileWithHttpInfo(String userAuthorizationId) throws ApiException {
        Call call = getMaskedUserProfileValidateBeforeCall(userAuthorizationId);
        Type localVarReturnType = new TypeToken<MaskedUserProfileResponse>(){}.getType();
        return apiClient.execute(call, localVarReturnType, ApiNameConstants.GET_SECURE_USER_PROFILE);
    }

    
    private Call getUserAuthorizationStatusValidateBeforeCall(String userAuthorizationId) throws ApiException {
        return ApiUtil.getCallObject(apiClient, "/v2/user/authorizations?userAuthorizationId={userAuthorizationId}", new Pair(ApiConstants.USER_AUTHORIZATION_ID,
                userAuthorizationId), "GET");
    }

    /**
     * Get user authorization status
     * Get the authorization status of a user  **Timeout: 15s** 
     * @param userAuthorizationId  (required)
     * @return UserAuthorizationStatus
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
        
     */
    public UserAuthorizationStatus getUserAuthorizationStatus(String userAuthorizationId) throws ApiException {
        ApiResponse<UserAuthorizationStatus> resp = getUserAuthorizationStatusWithHttpInfo(userAuthorizationId);
        return resp.getData();
    }

    /**
     * Get user authorization status
     * Get the authorization status of a user  **Timeout: 15s** 
     * @param userAuthorizationId  (required)
     * @return ApiResponse&lt;UserAuthorizationStatus&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
        
     */
    protected ApiResponse<UserAuthorizationStatus> getUserAuthorizationStatusWithHttpInfo(String userAuthorizationId) throws ApiException {
        Call call = getUserAuthorizationStatusValidateBeforeCall(userAuthorizationId);
        Type localVarReturnType = new TypeToken<UserAuthorizationStatus>(){}.getType();
        return apiClient.execute(call, localVarReturnType, ApiNameConstants.GET_USER_AUTH_STATUS);
    }

    
    private Call unlinkUserValidateBeforeCall(String userAuthorizationId) throws ApiException {
        return ApiUtil.getCallObject(apiClient, "/v2/user/authorizations/{userAuthorizationId}", new Pair(ApiConstants.USER_AUTHORIZATION_ID,
                userAuthorizationId), "DELETE");
    }

    /**
     * Unlink user
     * Unlink a user from the client  **Timeout: 15s** 
     * @param userAuthorizationId  (required)
     * @return NotDataResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
        
     */
    public NotDataResponse unlinkUser(String userAuthorizationId) throws ApiException {
        ApiResponse<NotDataResponse> resp = unlinkUserWithHttpInfo(userAuthorizationId);
        return resp.getData();
    }

    /**
     * Unlink user
     * Unlink a user from the client  **Timeout: 15s** 
     * @param userAuthorizationId  (required)
     * @return ApiResponse&lt;NotDataResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
        
     */
    protected ApiResponse<NotDataResponse> unlinkUserWithHttpInfo(String userAuthorizationId) throws ApiException {
        Call call = unlinkUserValidateBeforeCall(userAuthorizationId);
        Type localVarReturnType = new TypeToken<NotDataResponse>(){}.getType();
        return apiClient.execute(call, localVarReturnType, ApiNameConstants.UNLINK_USER);
    }

    /**
     * Get user cashback setting status
     * Get cashback setting state of specified user  **Timeout: 15s**
     * @param userAuthorizationId  (required)
     * @return UserCashbackSettingStatus
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body

     */
    public UserCashbackSettingStatus getUserCashbackSettingStatus(String userAuthorizationId) throws ApiException {
        ApiResponse<UserCashbackSettingStatus> resp = getUserCashbackSettingStatusWithHttpInfo(userAuthorizationId);
        return resp.getData();
    }

    /**
     * Get user cashback setting status
     * Get cashback setting state of specified user  **Timeout: 15s**
     * @param userAuthorizationId  (required)
     * @return ApiResponse&lt;UserCashbackSettingStatus&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body

     */
    protected ApiResponse<UserCashbackSettingStatus> getUserCashbackSettingStatusWithHttpInfo(String userAuthorizationId) throws ApiException {
        Call call = getUserCashbackSettingStatusValidateBeforeCall(userAuthorizationId);
        Type localVarReturnType = new TypeToken<UserCashbackSettingStatus>(){}.getType();
        return apiClient.execute(call, localVarReturnType, ApiNameConstants.GET_USER_CASHBACK_SETTING_STATUS);
    }

    private Call getUserCashbackSettingStatusValidateBeforeCall(String userAuthorizationId) throws ApiException {
        return ApiUtil.getCallObject(apiClient, "/v1/user/cashback_setting_state/{userAuthorizationId}", new Pair(ApiConstants.USER_AUTHORIZATION_ID,
                userAuthorizationId), "GET");
    }

    /**
     * Set useCashback flag
     * Set the useCashback flag of specified user.  **Timeout: 15s**
     *
     * @param body                    UserCashbackUseStatus
     * @return NotDataResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public NotDataResponse updateUserCashbackUseStatus(UserCashbackUseStatus body) throws ApiException {
        String message = validator.validate(body);
        if (message != null) {
            throw new IllegalArgumentException(message);
        }
        ApiResponse<NotDataResponse> resp = updateUserCashbackUseStatusWithHttpInfo(body);
        return resp.getData();
    }

    /**
     * Set useCashback flag
     *      * Set the useCashback flag of specified user.  **Timeout: 15s**
     *
     * @param body                    UserCashbackUseStatus
     * @return ApiResponse&lt;NotDataResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    protected ApiResponse<NotDataResponse> updateUserCashbackUseStatusWithHttpInfo(Object body) throws ApiException {
        Call call = updateUserCashbackUseStatusBeforeCall(body);
        Type localVarReturnType = new TypeToken<NotDataResponse>() {
        }.getType();
        return apiClient.execute(call, localVarReturnType, ApiNameConstants.UPDATE_USER_CASHBACK_USE_STATUS);
    }

    private Call updateUserCashbackUseStatusBeforeCall(Object body) throws ApiException {
        return ApiUtil.postCallObject(apiClient, "/v1/user/use_cashback", body, null);
    }

}
