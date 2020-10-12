package jp.ne.paypay.example;

import jp.ne.paypay.ApiClient;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.Configuration;
import jp.ne.paypay.api.UserApi;
import jp.ne.paypay.model.NotDataResponse;
import jp.ne.paypay.model.UserAuthorizationStatus;

public class UserApiExample {


  public static void main(String[] args) throws ApiException {
    ApiClient apiClient = new Configuration().getDefaultApiClient();
    apiClient.setProductionMode(false);
    apiClient.setApiKey("API_KEY");
    apiClient.setApiSecretKey("API_SECRET_KEY");
    apiClient.setAssumeMerchant("ASSUME_MERCHANT_ID");
    String userAuthorizationId = "USER_AUTHORIZATION_ID";

    UserApi userApi = new UserApi(apiClient);

    getUserAuthorizationStatus(userAuthorizationId, userApi);
    //Replace the "userAuthorizationId" with the actual one. Please note that this will Unlink the user from the client
    unlinkUser("userAuthorizationId", userApi);
  }

  private static void getUserAuthorizationStatus(String userAuthorizationId, UserApi userApi) {
    try{
      UserAuthorizationStatus userAuthorizationStatus = userApi.getUserAuthorizationStatus(userAuthorizationId);
      System.out.println(userAuthorizationStatus);
    }catch (ApiException e){
      System.out.println(e.getResponseBody());
      System.out.println(e.getResponseHeaders());
    }
  }
  private static void unlinkUser(String userAuthorizationId, UserApi userApi) {
    try{
      NotDataResponse notDataResponse = userApi.unlinkUser(userAuthorizationId);
      System.out.println(notDataResponse);
    }catch (ApiException e){
      System.out.println(e.getResponseBody());
      System.out.println(e.getResponseHeaders());
    }
  }
}

