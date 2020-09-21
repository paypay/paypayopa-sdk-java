package jp.ne.paypay.api;

import jp.ne.paypay.ApiClient;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.ApiResponse;
import jp.ne.paypay.model.MaskedUserProfileResponse;
import jp.ne.paypay.model.NotDataResponse;
import jp.ne.paypay.model.ResultInfo;
import jp.ne.paypay.model.UserAuthorizationStatus;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * API tests for UserApi
 */

public class UserApiTest {

    private final UserApi api = Mockito.spy(new UserApi());
    private ResultInfo resultInfo;
    private ApiClient apiClient;
    @BeforeEach
    public void setUp(){
        apiClient = Mockito.mock(ApiClient.class);
        api.setApiClient(apiClient);
        resultInfo = new ResultInfo();
        resultInfo.setMessage("SUCCESS");
    }
    /**
     * Get masked user profile
     *
     * Get the masked phone number of the user 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getMaskedUserProfileTest() throws ApiException {
        
        String userAuthorizationId = "userAuthorizationId";
        MaskedUserProfileResponse maskedUserProfileResponse = new MaskedUserProfileResponse();
        maskedUserProfileResponse.setResultInfo(resultInfo);
        ApiResponse<MaskedUserProfileResponse> maskedUserProfileResponseApiResponse = new ApiResponse<>(00001, null, maskedUserProfileResponse);
        Mockito.when(apiClient.escapeString(userAuthorizationId)).thenReturn(userAuthorizationId);
        Mockito.when(api.getMaskedUserProfileWithHttpInfo(userAuthorizationId)).thenReturn(maskedUserProfileResponseApiResponse);
        MaskedUserProfileResponse response = api.getMaskedUserProfile(userAuthorizationId);

        Assert.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
    }
    
    /**
     * Get user authorization status
     *
     * Get the authorization status of a user  **Timeout: 15s** 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getUserAuthorizationStatusTest() throws ApiException {

        String userAuthorizationId = "userAuthorizationId";
        UserAuthorizationStatus userAuthorizationStatus = new UserAuthorizationStatus();
        userAuthorizationStatus.setResultInfo(resultInfo);
        ApiResponse<UserAuthorizationStatus> userAuthorizationStatusApiResponse = new ApiResponse<>(00001, null, userAuthorizationStatus);
        Mockito.when(apiClient.escapeString(userAuthorizationId)).thenReturn(userAuthorizationId);
        Mockito.when(api.getUserAuthorizationStatusWithHttpInfo(userAuthorizationId)).thenReturn(userAuthorizationStatusApiResponse);
        
        UserAuthorizationStatus response = api.getUserAuthorizationStatus(userAuthorizationId);

        Assert.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
    }
    
    /**
     * Unlink user
     *
     * Unlink a user from the client  **Timeout: 15s** 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void unlinkUserTest() throws ApiException {

        String userAuthorizationId = "userAuthorizationId";
        NotDataResponse notDataResponse = new NotDataResponse();
        notDataResponse.setResultInfo(resultInfo);
        ApiResponse<NotDataResponse> notDataResponseApiResponse = new ApiResponse<>(00001, null, notDataResponse);
        Mockito.when(apiClient.escapeString(userAuthorizationId)).thenReturn(userAuthorizationId);
        Mockito.when(api.unlinkUserWithHttpInfo(userAuthorizationId)).thenReturn(notDataResponseApiResponse);
        
        NotDataResponse response = api.unlinkUser(userAuthorizationId);

        Assert.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
    }
    
}
