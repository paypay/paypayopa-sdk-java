# UserApi

Method | HTTP request | Description
------------- | ------------- | -------------
[**getMaskedUserProfile**](UserApi.md#getMaskedUserProfile) | **GET** /v2/user/profile/secure?userAuthorizationId&#x3D;{userAuthorizationId} | Get masked user profile
[**getUserAuthorizationStatus**](UserApi.md#getUserAuthorizationStatus) | **GET** /v2/user/authorizations?userAuthorizationId&#x3D;{userAuthorizationId} | Get user authorization status
[**unlinkUser**](UserApi.md#unlinkUser) | **DELETE** /v2/user/authorizations/{userAuthorizationId} | Unlink user


<a name="getMaskedUserProfile"></a>
# **getMaskedUserProfile**
> MaskedUserProfileResponse getMaskedUserProfile(userAuthorizationId)

Get masked user profile

Get the masked phone number of the user 

### Example
```java
// Import classes:
import jp.ne.paypay.ApiException;
import jp.ne.paypay.api.UserApi;



UserApi apiInstance = new UserApi(apiClient);

String userAuthorizationId = "USER_AUTHORIZATION_ID"  

try {
    MaskedUserProfileResponse result = apiInstance.getMaskedUserProfile(userAuthorizationId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserApi#getMaskedUserProfile");
    System.out.println(e.getResponseBody());
}
```
```
Please refer to the below document for more information :
https://www.paypay.ne.jp/opa/doc/v1.0/direct_debit#operation/getMaskedUserProfile
```

<a name="getUserAuthorizationStatus"></a>
# **getUserAuthorizationStatus**
> UserAuthorizationStatus getUserAuthorizationStatus(userAuthorizationId)

Get user authorization status

Get the authorization status of a user  **Timeout: 15s** 

### Example
```java
// Import classes:
import jp.ne.paypay.ApiException;
import jp.ne.paypay.api.UserApi;



UserApi apiInstance = new UserApi(apiClient);

String userAuthorizationId = "USER_AUTHORIZATION_ID"; // String | 

try {
    UserAuthorizationStatus result = apiInstance.getUserAuthorizationStatus(userAuthorizationId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserApi#getUserAuthorizationStatus");
    e.printStackTrace();
    System.out.println(e.getResponseBody());
}
```

```
Please refer to the below document for more information :
https://www.paypay.ne.jp/opa/doc/v1.0/direct_debit#operation/getUserAuthorizationStatus
```

<a name="unlinkUser"></a>
# **unlinkUser**
> NotDataResponse unlinkUser(userAuthorizationId)

Unlink user

Unlink a user from the client  **Timeout: 15s** 

### Example
```java
// Import classes:
import jp.ne.paypay.ApiException;
import jp.ne.paypay.api.UserApi;



UserApi apiInstance = new UserApi(apiClient);

String userAuthorizationId = "USER_AUTHORIZATION_ID"; // String | 

try {
    NotDataResponse result = apiInstance.unlinkUser(userAuthorizationId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserApi#unlinkUser");
    e.printStackTrace();
    System.out.println(e.getResponseBody());
}
```

```
Please refer to the below document for more information :
https://www.paypay.ne.jp/opa/doc/v1.0/direct_debit#operation/unlinkUser
```



