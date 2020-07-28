package com.paypay.api;

import com.google.gson.reflect.TypeToken;
import com.paypay.ApiCallback;
import com.paypay.ApiClient;
import com.paypay.ApiException;
import com.paypay.ApiResponse;
import com.paypay.Configuration;
import com.paypay.Pair;
import com.paypay.model.MaskedUserProfileResponse;
import com.paypay.model.NotDataResponse;
import com.paypay.model.UserAuthorizationStatus;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class UserApi {
    private ApiClient apiClient;
    private static final  String APPLICATION_JSON = "application/json";
    private static final  String ACCEPT = "Accept";
    private static final  String HMAC_AUTH = "HmacAuth";
    private static final  String CONTENT_TYPE = "Content-Type";
    private static final  String USER_AUTHORIZATION_ID = "userAuthorizationId";

    public UserApi() {
        this(Configuration.getDefaultApiClient());
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

    
    /**
     * Build call for getMaskedUserProfile
     * @param userAuthorizationId  (required)
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
        
     */
    private com.squareup.okhttp.Call getMaskedUserProfileCall(String userAuthorizationId) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/v2/user/profile/secure?userAuthorizationId={userAuthorizationId}"
                .replaceAll("\\{" + USER_AUTHORIZATION_ID + "}", apiClient.escapeString(userAuthorizationId));;

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

        String[] localVarAuthNames = new String[] { HMAC_AUTH };
        apiClient.setReadTimeout(15);
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames);
    }
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getMaskedUserProfileValidateBeforeCall(String userAuthorizationId) throws ApiException {
        // verify the required parameter 'userAuthorizationId' is set
        if (userAuthorizationId == null) {
            throw new ApiException("Missing the required parameter 'userAuthorizationId' when calling getMaskedUserProfile(Async)");
        }
        return getMaskedUserProfileCall(userAuthorizationId);
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
    private ApiResponse<MaskedUserProfileResponse> getMaskedUserProfileWithHttpInfo(String userAuthorizationId) throws ApiException {
        com.squareup.okhttp.Call call = getMaskedUserProfileValidateBeforeCall(userAuthorizationId);
        Type localVarReturnType = new TypeToken<MaskedUserProfileResponse>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Get masked user profile (asynchronously)
     * Get the masked phone number of the user 
     * @param userAuthorizationId  (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
        
     */
    public com.squareup.okhttp.Call getMaskedUserProfileAsync(String userAuthorizationId, final ApiCallback<MaskedUserProfileResponse> callback) throws ApiException {
        com.squareup.okhttp.Call call = getMaskedUserProfileValidateBeforeCall(userAuthorizationId);
        Type localVarReturnType = new TypeToken<MaskedUserProfileResponse>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    
    /**
     * Build call for getUserAuthorizationStatus
     * @param userAuthorizationId  (required)
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
        
     */
    private com.squareup.okhttp.Call getUserAuthorizationStatusCall(String userAuthorizationId) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/v2/user/authorizations?userAuthorizationId={userAuthorizationId}"
            .replaceAll("\\{" + USER_AUTHORIZATION_ID + "}", apiClient.escapeString(userAuthorizationId));

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
        String[] localVarAuthNames = new String[] { HMAC_AUTH };
        apiClient.setReadTimeout(15);
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getUserAuthorizationStatusValidateBeforeCall(String userAuthorizationId) throws ApiException {
        // verify the required parameter 'userAuthorizationId' is set
        if (userAuthorizationId == null) {
            throw new ApiException("Missing the required parameter 'userAuthorizationId' when calling getUserAuthorizationStatus(Async)");
        }
        return getUserAuthorizationStatusCall(userAuthorizationId);
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
    private ApiResponse<UserAuthorizationStatus> getUserAuthorizationStatusWithHttpInfo(String userAuthorizationId) throws ApiException {
        com.squareup.okhttp.Call call = getUserAuthorizationStatusValidateBeforeCall(userAuthorizationId);
        Type localVarReturnType = new TypeToken<UserAuthorizationStatus>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Get user authorization status (asynchronously)
     * Get the authorization status of a user  **Timeout: 15s** 
     * @param userAuthorizationId  (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
        
     */
    public com.squareup.okhttp.Call getUserAuthorizationStatusAsync(String userAuthorizationId, final ApiCallback<UserAuthorizationStatus> callback) throws ApiException {
        com.squareup.okhttp.Call call = getUserAuthorizationStatusValidateBeforeCall(userAuthorizationId);
        Type localVarReturnType = new TypeToken<UserAuthorizationStatus>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    
    /**
     * Build call for unlinkUser
     * @param userAuthorizationId  (required)
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
        
     */
    private com.squareup.okhttp.Call unlinkUserCall(String userAuthorizationId) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/v2/user/authorizations/{userAuthorizationId}"
            .replaceAll("\\{" + USER_AUTHORIZATION_ID + "}", apiClient.escapeString(userAuthorizationId));

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
        String[] localVarAuthNames = new String[] { HMAC_AUTH };
        apiClient.setReadTimeout(15);
        return apiClient.buildCall(localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call unlinkUserValidateBeforeCall(String userAuthorizationId) throws ApiException {
        // verify the required parameter 'userAuthorizationId' is set
        if (userAuthorizationId == null) {
            throw new ApiException("Missing the required parameter 'userAuthorizationId' when calling unlinkUser(Async)");
        }
        return unlinkUserCall(userAuthorizationId);
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
    private ApiResponse<NotDataResponse> unlinkUserWithHttpInfo(String userAuthorizationId) throws ApiException {
        com.squareup.okhttp.Call call = unlinkUserValidateBeforeCall(userAuthorizationId);
        Type localVarReturnType = new TypeToken<NotDataResponse>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Unlink user (asynchronously)
     * Unlink a user from the client  **Timeout: 15s** 
     * @param userAuthorizationId  (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
        
     */
    public com.squareup.okhttp.Call unlinkUserAsync(String userAuthorizationId, final ApiCallback<NotDataResponse> callback) throws ApiException {
        com.squareup.okhttp.Call call = unlinkUserValidateBeforeCall(userAuthorizationId);
        Type localVarReturnType = new TypeToken<NotDataResponse>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    
}
