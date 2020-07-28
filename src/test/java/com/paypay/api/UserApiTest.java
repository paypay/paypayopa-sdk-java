package com.paypay.api;

import com.paypay.ApiException;
import com.paypay.model.MaskedUserProfileResponse;
import com.paypay.model.NotDataResponse;
import com.paypay.model.UserAuthorizationStatus;

import org.junit.Ignore;
import org.junit.Test;

/**
 * API tests for UserApi
 */
@Ignore
public class UserApiTest {

    private final UserApi api = new UserApi();

    
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
        
        String userAuthorizationId = null;
        
        MaskedUserProfileResponse response = api.getMaskedUserProfile(userAuthorizationId);

        // TODO: test validations
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
        
        String userAuthorizationId = null;
        
        UserAuthorizationStatus response = api.getUserAuthorizationStatus(userAuthorizationId);

        // TODO: test validations
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
        
        String userAuthorizationId = null;
        
        NotDataResponse response = api.unlinkUser(userAuthorizationId);

        // TODO: test validations
    }
    
}
