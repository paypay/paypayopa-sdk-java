package jp.ne.paypay.example;

import jp.ne.paypay.ApiClient;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.Configuration;
import jp.ne.paypay.api.PendingPaymentApi;
import jp.ne.paypay.model.NotDataResponse;
import jp.ne.paypay.model.Payment;
import jp.ne.paypay.model.PaymentDetails;
import jp.ne.paypay.model.Refund;
import jp.ne.paypay.model.RefundDetails;

import java.util.UUID;

public class PendingPaymentApiExample extends PaymentApiExample {


  public static void main(String[] args) {
    ApiClient apiClient = new Configuration().getDefaultApiClient();
    apiClient.setProductionMode(false);
    apiClient.setApiKey("API_KEY");
    apiClient.setApiSecretKey("API_SECRET_KEY");
    apiClient.setAssumeMerchant("ASSUME_MERCHANT_ID");
    String userAuthorizationId = "USER_AUTHORIZATION_ID";
    PendingPaymentApi pendingPaymentApi = new PendingPaymentApi(apiClient);

    PaymentDetails paymentDetails = pendingPayment(pendingPaymentApi, userAuthorizationId, 1);
    //Uncomment below line and comment above line and replace merchantPaymentId to check refund payment
    //PaymentDetails paymentDetails = getPendingPaymentDetails(pendingPaymentApi, "merchantPaymentId");

    if(paymentDetails != null && paymentDetails.getData() != null){
      String merchantPaymentId = paymentDetails.getData().getMerchantPaymentId();
      getPendingPaymentDetails(pendingPaymentApi, merchantPaymentId);
      //Cancel Pending Order
      cancelPendingPayment(pendingPaymentApi, merchantPaymentId);
      //Refund payment. Run this after payment is complete
      refundPendingPayment(pendingPaymentApi, paymentDetails.getData().getPaymentId());
    }


  }

  private static PaymentDetails pendingPayment(PendingPaymentApi paymentApi, String userAuthorizationId, int amount) {

    String merchantPaymentId = UUID.randomUUID().toString();
    Payment payment = getPaymentObject(merchantPaymentId, userAuthorizationId, amount);
    createPendingPayment(paymentApi, payment);
    return getPendingPaymentDetails(paymentApi, merchantPaymentId);
  }

  private static void cancelPendingPayment(PendingPaymentApi paymentApi, String merchantPaymentId) {

    if (merchantPaymentId != null) {
      cancelPendingOrder(paymentApi, merchantPaymentId);
      getPendingPaymentDetails(paymentApi, merchantPaymentId);
    }
  }

  private static void refundPendingPayment(PendingPaymentApi paymentApi, String paymentId) {
    String refundId = UUID.randomUUID().toString();
    if(paymentId != null){
      RefundDetails refundDetails = refundPendingPayment(paymentApi, paymentId, refundId);
      if (refundDetails.getData().getMerchantRefundId() != null) {
        getRefundDetails(paymentApi, refundDetails.getData().getMerchantRefundId());
      }
    }
  }

  private static void createPendingPayment(final PendingPaymentApi apiInstance, Payment payment) {
    try {
      PaymentDetails result = apiInstance.createPendingPayment(payment);
      System.out.println("\nAPI RESPONSE\n------------------\n");
      System.out.println(result.getResultInfo().getCode());
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println(e.getResponseBody());
    }
  }

  private static RefundDetails refundPendingPayment(final PendingPaymentApi apiInstance, String paymentId, String refundId) {
    RefundDetails result = null;
    try {
      Refund refund = getRefundObject(paymentId, refundId);
      result = apiInstance.refundPayment(refund);
      System.out.println("\nAPI RESPONSE\n------------------\n");
      System.out.println(result.getResultInfo().getCode());
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println(e.getResponseBody());
    }
    return result;
  }

  private static void getRefundDetails(final PendingPaymentApi apiInstance, final String merchantRefundId) {

    try {
      RefundDetails result = apiInstance.getRefundDetails(merchantRefundId);
      System.out.println("\nAPI RESPONSE\n------------------\n");
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println(e.getResponseBody());
    }
  }

  private static PaymentDetails getPendingPaymentDetails(final PendingPaymentApi apiInstance, String merchantPaymentId) {
    PaymentDetails result = null;
    try {
      result = apiInstance.getPaymentDetails(merchantPaymentId);
      System.out.println("\nAPI RESPONSE\n------------------\n");
      System.out.println(result.getResultInfo().getCode());
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println(e.getResponseBody());
    }
    return result;
  }

  private static void cancelPendingOrder(final PendingPaymentApi apiInstance, String merchantPaymentId) {
    try {
      NotDataResponse pendingOrderCancelled = apiInstance.cancelPendingOrder(merchantPaymentId);
      System.out.println("\n\nCancel Pending Order API RESPONSE\n------------------\n");
      System.out.println(pendingOrderCancelled);
    } catch (ApiException e) {
      System.err.println("Exception when calling PaymentApi#cancelPendingOrder" + e.getMessage());
      System.err.println(e.getResponseBody());
    }
  }
}

