package jp.ne.paypay.example;

import jp.ne.paypay.ApiClient;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.Configuration;
import jp.ne.paypay.api.CashbackApi;
import jp.ne.paypay.model.Cashback;
import jp.ne.paypay.model.CashbackDetails;
import jp.ne.paypay.model.MoneyAmount;
import jp.ne.paypay.model.ReverseCashback;
import jp.ne.paypay.model.ReverseCashbackDetails;

import java.time.Instant;
import java.util.UUID;

public class CashbackApiExample {


  public static void main(String[] args) throws ApiException {
    ApiClient apiClient = new Configuration().getDefaultApiClient();
    apiClient.setProductionMode(false);
    apiClient.setApiKey("API_KEY");
    apiClient.setApiSecretKey("API_SECRET_KEY");
    apiClient.setAssumeMerchant("YOUR_MERCHANT_ID");
    String userAuthorizationId = "USER_AUTHORIZATION_ID";
    CashbackApi cashbackApi = new CashbackApi(apiClient);

    CashbackDetails cashbackDetails = giveCashback(cashbackApi, userAuthorizationId, 1);
    if(cashbackDetails != null && cashbackDetails.getData() != null){
      reverseCashback(cashbackApi, cashbackDetails.getData().getMerchantCashbackId(), 1);
    }
  }

  private static CashbackDetails giveCashback(CashbackApi cashbackApi, String userAuthorizationId, int amount) {

    String merchantCashbackId = UUID.randomUUID().toString();
    Cashback cashback = getCashbackObject(merchantCashbackId, userAuthorizationId, amount);
    createCashbackRequest(cashbackApi, cashback);
    return getCashbackDetails(cashbackApi, merchantCashbackId);
  }

  private static CashbackDetails getCashbackDetails(final CashbackApi apiInstance, String merchantCashbackId) {
    CashbackDetails result = null;
    try {
      result = apiInstance.getCashbackDetails(merchantCashbackId);
      System.out.println("\nAPI RESPONSE\n------------------\n");
      System.out.println(result.getResultInfo().getCode());
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println(e.getResponseBody());
    }
    return result;
  }

  private static Cashback getCashbackObject(String merchantCashbackId, String userAuthorizationId, int amount){
    Cashback cashback = new Cashback();
    cashback.setMerchantCashbackId(merchantCashbackId);
    cashback.setUserAuthorizationId(userAuthorizationId);
    cashback.setRequestedAt(Instant.now().getEpochSecond());
    //cashback.setExpiryDate("2021-02-28"); // Optional, if set Should be with format YYYY-MM-DD
    cashback.setAmount(new MoneyAmount().amount(amount).currency(MoneyAmount.CurrencyEnum.JPY));
    return cashback;

  }

  private static CashbackDetails createCashbackRequest(final CashbackApi apiInstance, Cashback cashback) {
    CashbackDetails result = null;
    try {
      result = apiInstance.createCashbackRequest(cashback);
      System.out.println("\nAPI RESPONSE\n------------------\n");
      System.out.println(result.getResultInfo().getCode());
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println(e.getResponseBody());
    }
    return result;
  }

  private static ReverseCashbackDetails reverseCashback(CashbackApi cashbackApi, String merchantCashbackId, int amount) {

    String merchantCashbackReversalId = UUID.randomUUID().toString();
    ReverseCashback cashback = getReverseCashbackObject(merchantCashbackReversalId, merchantCashbackId, amount);
    createReverseCashbackRequest(cashbackApi, cashback);
    return getReversedCashbackDetails(cashbackApi, merchantCashbackReversalId, merchantCashbackId);
  }

  private static ReverseCashback getReverseCashbackObject(String merchantCashbackReversalId, String merchantCashbackId, int amount){
    ReverseCashback reverseCashback = new ReverseCashback();
    reverseCashback.setMerchantCashbackReversalId(merchantCashbackReversalId);
    reverseCashback.setMerchantCashbackId(merchantCashbackId);
    reverseCashback.setRequestedAt(Instant.now().getEpochSecond());
    reverseCashback.setAmount(new MoneyAmount().amount(amount).currency(MoneyAmount.CurrencyEnum.JPY));
    return reverseCashback;

  }

  private static ReverseCashbackDetails createReverseCashbackRequest(final CashbackApi apiInstance, ReverseCashback reverseCashback) {
    ReverseCashbackDetails result = null;
    try {
      result = apiInstance.createReverseCashbackRequest(reverseCashback);
      System.out.println("\nAPI RESPONSE\n------------------\n");
      System.out.println(result.getResultInfo().getCode());
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println(e.getResponseBody());
    }
    return result;
  }

  private static ReverseCashbackDetails getReversedCashbackDetails(final CashbackApi apiInstance, String merchantCashbackReversalId, String merchantCashbackId) {
    ReverseCashbackDetails result = null;
    try {
      result = apiInstance.getReversedCashbackDetails(merchantCashbackReversalId, merchantCashbackId);
      System.out.println("\nAPI RESPONSE\n------------------\n");
      System.out.println(result.getResultInfo().getCode());
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println(e.getResponseBody());
    }
    return result;
  }
}

