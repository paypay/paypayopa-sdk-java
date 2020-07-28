package jp.ne.paypay.example;

import jp.ne.paypay.ApiClient;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.Configuration;
import jp.ne.paypay.api.PaymentApi;
import jp.ne.paypay.api.UserApi;
import jp.ne.paypay.api.WalletApi;
import jp.ne.paypay.model.CaptureObject;
import jp.ne.paypay.model.MaskedUserProfileResponse;
import jp.ne.paypay.model.MerchantOrderItem;
import jp.ne.paypay.model.MoneyAmount;
import jp.ne.paypay.model.NotDataResponse;
import jp.ne.paypay.model.Payment;
import jp.ne.paypay.model.PaymentDetails;
import jp.ne.paypay.model.PaymentStateRevert;
import jp.ne.paypay.model.QRCode;
import jp.ne.paypay.model.QRCodeDetails;
import jp.ne.paypay.model.Refund;
import jp.ne.paypay.model.RefundDetails;
import jp.ne.paypay.model.RevertAuthResponse;
import jp.ne.paypay.model.UserAuthorizationStatus;
import jp.ne.paypay.model.WalletBalance;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

public class PaymentApiExample {


  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    //defaultClient.setBasePath("BASE_URL");
    defaultClient.setProductionMode(false); // Default is false
    defaultClient.setApiKey("YOUR_API_KEY");
    defaultClient.setApiSecretKey("YOUR_API_KEY_SECRET");
    defaultClient.setAssumeMerchant("YOUR_MERCHANT_KEY");

    PaymentApi paymentApi = new PaymentApi(defaultClient);
    UserApi userApi = new UserApi(defaultClient);
    WalletApi walletApiInstance = new WalletApi(defaultClient);

    String userAuthorizationId = "YOUR_USER_AUTHORIZATION_ID";
    directDebitFlow(walletApiInstance, paymentApi, userAuthorizationId);
    appInvokeFlow(paymentApi, walletApiInstance, userAuthorizationId);
    userApis(userApi, userAuthorizationId);

  }

  private static void appInvokeFlow(final PaymentApi paymentApi, final WalletApi walletApiInstance,
          final String userAuthorizationId) {
    int amount =1; String currency = "JPY";
    QRCodeDetails qrCodeDetails = createQRCode(paymentApi, amount);

    String marchantPaymentId  = qrCodeDetails!=null ?qrCodeDetails.getData().getMerchantPaymentId(): null;
    WalletBalance walletBalance = getWalletBalance(walletApiInstance, userAuthorizationId, amount, currency);
    if(marchantPaymentId != null && walletBalance != null && walletBalance.getData().isHasEnoughBalance()) {
        System.out.println("The QR Code can be used as a deeplink to invoke PayPay app and receive Payments. The user "
                + "can makes the payment using PayPay App");
        System.out.println("For this example, we will create payment using the API...");
        PaymentDetails paymentDetails = createPayment(paymentApi, marchantPaymentId, userAuthorizationId, amount);
        if (paymentDetails != null) {
          //capturePayment(paymentApi, marchantPaymentId, amount);
          System.out.println("Payment created successfully, Now calling the API to get payment details for payment " + "ID:"
                  + marchantPaymentId);
          String refundId = UUID.randomUUID().toString();
          paymentDetails = getPaymentDetails(paymentApi, marchantPaymentId);
          if (paymentDetails != null) {
            System.out.println("Creating Refund for the payment:" + paymentDetails.getData().getPaymentId());
            createRefund(paymentApi, paymentDetails.getData().getPaymentId(), refundId);
            System.out.println("Get refund details:" + refundId);
            getRefundDetails(paymentApi, refundId);
            System.out.println("Finally cancel the payment");
            cancelPayment(paymentApi, marchantPaymentId);
          }
        }
    }
    if(qrCodeDetails != null) {
      System.out.println("Delete the QR Code: "+qrCodeDetails.getData().getCodeId());
      deleteQrCode(paymentApi, qrCodeDetails.getData().getCodeId());
    }
  }

  private static void directDebitFlow(WalletApi walletApiInstance, PaymentApi paymentApi, String userAuthorizationId){

    String marchantPaymentId  = UUID.randomUUID().toString();
    System.out.println("Checking wallet balance...");
    int amount =1; String currency = "JPY";
    WalletBalance walletBalance = getWalletBalance(walletApiInstance, userAuthorizationId, amount, currency);
    if(walletBalance != null && walletBalance.getData().isHasEnoughBalance()){
      System.out.println("There is enough balance, now creating payment...");
      PaymentDetails paymentDetails = createPayment(paymentApi, marchantPaymentId, userAuthorizationId, amount);
      if (paymentDetails != null) {
        System.out.println("Payment created successfully, Now calling the API to get payment details for payment "
                + "ID:"+marchantPaymentId);
        String refundId = UUID.randomUUID().toString();
        paymentDetails = getPaymentDetails(paymentApi, marchantPaymentId);
        if(paymentDetails != null) {
          System.out.println("Creating Refund for the payment:" + paymentDetails.getData().getPaymentId());
          createRefund(paymentApi, paymentDetails.getData().getPaymentId(), refundId);
          System.out.println("Get refund details:"+refundId);
          getRefundDetails(paymentApi, refundId);
          System.out.println("Finally cancel the payment");
          cancelPayment(paymentApi, marchantPaymentId);
        }
      }
    }

  }
  private static WalletBalance getWalletBalance(final WalletApi apiInstance, String userAuthorizationId, int amount,
          String currency) {
    WalletBalance result = null;
    try {
      result = apiInstance.checkWalletBalance(userAuthorizationId, amount, currency, null);
      System.out.println("\nAPI RESPONSE\n------------------\n");
      System.out.println(result);
    } catch (ApiException e) {

      e.printStackTrace();
      System.out.println("--------------------");
      System.out.println(e.getResponseBody());
    }
    return result;
  }

  private static PaymentDetails createPayment(final PaymentApi apiInstance, String merchantPaymentId,
          String userAuthorizationId, int amount) {
    PaymentDetails result = null;
    try {
      Payment payment = new Payment();
      payment.setAmount(new MoneyAmount().amount(amount).currency(MoneyAmount.CurrencyEnum.JPY));
      payment.setMerchantPaymentId(merchantPaymentId);
      payment.setUserAuthorizationId(userAuthorizationId);
      payment.setRequestedAt(Instant.now().getEpochSecond());
      payment.setStoreId(RandomStringUtils.randomAlphabetic(8));
      payment.setTerminalId(RandomStringUtils.randomAlphanumeric(8));
      payment.setOrderReceiptNumber(RandomStringUtils.randomAlphanumeric(8));
      payment.setOrderDescription("Payment for Order ID:"+UUID.randomUUID().toString());
      MerchantOrderItem merchantOrderItem =
              new MerchantOrderItem()
                      .category("Dessert").name("Red Velvet Cake")
                      .productId(RandomStringUtils.randomAlphanumeric(8)).quantity(1)
                      .unitPrice(new MoneyAmount().amount(10).currency(MoneyAmount.CurrencyEnum.JPY));
      List<MerchantOrderItem> merchantOrderItems = new ArrayList<>();
      merchantOrderItems.add(merchantOrderItem);
      payment.setOrderItems(new ArrayList<MerchantOrderItem>(merchantOrderItems));
      result = apiInstance.createPayment(payment, "true");
      System.out.println("\nAPI RESPONSE\n------------------\n");
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println(e.getResponseBody());
    }
    return result;
  }

  private static void capturePayment(final PaymentApi apiInstance, String merchantPaymentId, int amount){
    try {
      CaptureObject captureObject = new CaptureObject();
      captureObject.setMerchantCaptureId(UUID.randomUUID().toString());
      captureObject.setMerchantPaymentId(merchantPaymentId);
      captureObject.setAmount(new MoneyAmount().amount(amount).currency(MoneyAmount.CurrencyEnum.JPY));
      captureObject.setOrderDescription("new Order");
      captureObject.setRequestedAt(Instant.now().getEpochSecond());
      PaymentDetails paymentDetails = apiInstance.capturePaymentAuth(captureObject);
      System.out.println(paymentDetails);
    } catch (ApiException e) {
      e.printStackTrace();
      System.out.println(e.getResponseBody());
    }

  }
  private static void createRefund(final PaymentApi apiInstance, String paymentId, String refundId) {

    try {
      Refund refund = new Refund();
      refund.setAmount(new MoneyAmount().amount(1).currency(MoneyAmount.CurrencyEnum.JPY));
      refund.setMerchantRefundId(refundId);
      refund.setPaymentId(paymentId);
      refund.setReason("Testing");
      refund.setRequestedAt(Instant.now().getEpochSecond());
      RefundDetails result = apiInstance.refundPayment(refund);
      System.out.println("\nAPI RESPONSE\n------------------\n");
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println(e.getResponseBody());
    }
  }

  private static QRCodeDetails createQRCode(final PaymentApi apiInstance, int amount) {
    QRCodeDetails result = null;
    try {
      QRCode qrCode = new QRCode();
      qrCode.setAmount(new MoneyAmount().amount(amount).currency(MoneyAmount.CurrencyEnum.JPY));
      qrCode.setMerchantPaymentId(UUID.randomUUID().toString());
      qrCode.setCodeType("ORDER_QR");
      qrCode.setStoreId(RandomStringUtils.randomAlphabetic(8));
      qrCode.setStoreInfo("Just Bake");
      qrCode.setTerminalId(RandomStringUtils.randomAlphanumeric(8));
      qrCode.requestedAt(Instant.now().getEpochSecond());
      qrCode.redirectUrl("https://www.justbake.in/payment");
      qrCode.redirectType(QRCode.RedirectTypeEnum.WEB_LINK);//For Deep Link, RedirectTypeEnum.APP_DEEP_LINK
      qrCode.setOrderDescription("Payment for Order ID:"+UUID.randomUUID().toString());
      //qrCode.isAuthorization(true);
      //Long expireAt = Instant.now().getEpochSecond()+5000;
      //qrCode.setAuthorizationExpiry(expireAt);
      MerchantOrderItem merchantOrderItem =
              new MerchantOrderItem()
                      .category("Dessert").name("Red Velvet Cake")
                      .productId(RandomStringUtils.randomAlphanumeric(8)).quantity(1)
                      .unitPrice(new MoneyAmount().amount(10).currency(MoneyAmount.CurrencyEnum.JPY));
      List<MerchantOrderItem> merchantOrderItems = new ArrayList<>();
      merchantOrderItems.add(merchantOrderItem);
      qrCode.setOrderItems(merchantOrderItems);
      result = apiInstance.createQRCode(qrCode);
      System.out.println("\nAPI RESPONSE\n------------------\n");
      System.out.println(result);
    } catch (ApiException e) {
      System.out.println(e.getResponseHeaders().toString());
      System.err.println(e.getResponseBody());
    }
    return result;
  }

  private static void getRefundDetails(final PaymentApi apiInstance, final String marchantRefundId) {

    try {
      RefundDetails result = apiInstance.getRefundDetails(marchantRefundId);
      System.out.println("\nAPI RESPONSE\n------------------\n");
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println(e.getResponseBody());
    }
  }

  private static PaymentDetails getPaymentDetails(final PaymentApi apiInstance, final String merchantPaymentId) {
    PaymentDetails result = null;
    try {
       result = apiInstance.getPaymentDetails(merchantPaymentId);
      System.out.println("\nAPI RESPONSE\n------------------\n");
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println(e.getResponseBody());
    }
    return result;
  }

  private static void deleteQrCode(final PaymentApi apiInstance, final String qrCode) {
    try {
      NotDataResponse result = apiInstance.deleteQRCode(qrCode);
      System.out.println("\nAPI RESPONSE\n------------------\n");
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println(e.getResponseBody());
    }
  }

  private static void cancelPayment(final PaymentApi apiInstance, final String merchantPaymentId) {
    try {
      NotDataResponse result = apiInstance.cancelPayment(merchantPaymentId);
      System.out.println("\n\nAPI RESPONSE\n------------------\n");
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PaymentApi#cancelPayment" + e.getMessage());
      System.err.println(e.getResponseBody());
    }
  }

  private static void  userApis(final UserApi apiInstance, final String userAutorizationId){
    getMaskedProfile(apiInstance, userAutorizationId);
    getUserAuthorizationStatus(apiInstance, userAutorizationId);
    //This will unlink the user AuthorizationId, run it only if it needs to be deleted
    //unlinkUser(apiInstance, userAutorizationId);
  }
  private static void getMaskedProfile(final UserApi apiInstance, final String userAutorizationId) {
    try {
      MaskedUserProfileResponse result = apiInstance.getMaskedUserProfile(userAutorizationId);
      System.out.println("\n\nAPI RESPONSE\n------------------\n");
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#getMaskedUserProfile" + e.getMessage());
      System.err.println(e.getResponseBody());
    }
  }

  private static void getUserAuthorizationStatus(final UserApi apiInstance, final String userAutorizationId) {
    try {
      UserAuthorizationStatus result = apiInstance.getUserAuthorizationStatus(userAutorizationId);
      System.out.println("\n\nAPI RESPONSE\n------------------\n");
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#getUserAuthorizationStatus" + e.getMessage());
      System.err.println(e.getResponseBody());
    }
  }

  private static void unlinkUser(final UserApi apiInstance, final String userAutorizationId) {
    try {
      NotDataResponse result = apiInstance.unlinkUser(userAutorizationId);
      System.out.println("\n\nAPI RESPONSE\n------------------\n");
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#unlinkUser" + e.getMessage());
      System.err.println(e.getResponseBody());
    }
  }

  private static void PaymentRevertAuth(final PaymentApi apiInstance, String paymentId, String merchantRevertId) {

    try {
      PaymentStateRevert payment = new PaymentStateRevert();
      payment.setPaymentId(paymentId);
      payment.setMerchantRevertId(merchantRevertId);
      payment.setRequestedAt(Instant.now().getEpochSecond());
      RevertAuthResponse result = apiInstance.revertAuth(payment);
      System.out.println("\nAPI RESPONSE\n------------------\n");
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println(e.getResponseBody());
    }
  }
}
