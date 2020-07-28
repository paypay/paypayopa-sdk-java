# PaymentApi

Method | HTTP request | Description
------------- | ------------- | -------------
[**cancelPayment**](PaymentApi.md#cancelPayment) | **DELETE** /v1/payments/{merchantPaymentId} | Cancel a payment
[**capturePaymentAuth**](PaymentApi.md#capturePaymentAuth) | **POST** /v1/payments/capture | Capture a payment authorization
[**createPayment**](PaymentApi.md#createPayment) | **POST** /v1/payments | Create a payment
[**createQRCode**](PaymentApi.md#createQRCode) | **POST** /v1/codes | Create a Code
[**deleteQRCode**](PaymentApi.md#deleteQRCode) | **DELETE** /v1/codes/{codeId} | Delete a Code
[**getPaymentDetails**](PaymentApi.md#getPaymentDetails) | **GET** /v1/payments/{merchantPaymentId} | Get payment details
[**getPaymentDetails_0**](PaymentApi.md#getPaymentDetails_0) | **GET** /v1/codes/payments/{merchantPaymentId} | Get payment details
[**getRefundDetails**](PaymentApi.md#getRefundDetails) | **GET** /v1/refunds/{merchantRefundId} | Get refund details
[**refundPayment**](PaymentApi.md#refundPayment) | **POST** /v1/refunds | Refund a payment
[**revertAuth**](PaymentApi.md#revertAuth) | **POST** /v1/payments/preauthorize/revert | Revert a payment authorization




<a name="cancelPayment"></a>
# **cancelPayment**
> NotDataResponse cancelPayment(merchantPaymentId)

Cancel a payment

This api is used in case, while creating a payment, the client can not determine the status of the payment. For example, client get timeout or the response cannot contain the information to indicate the exact payment status.

By calling this api, if accepted, the OPA will guarantee the money eventually goes back to user's account.


Note: The Cancel API can be used until 00:14:59 AM the day after the Payment has happened.
For 00:15 AM or later, please call the refund API to refund the payment. 

### Example
```java
// Import classes:
//import com.paypay.ApiException;
//import com.paypay.api.PaymentApi;



PaymentApi apiInstance = new PaymentApi();

String merchantPaymentId = "MERCHANT_PAYMENT_ID"; 

try {
    NotDataResponse result = apiInstance.cancelPayment(merchantPaymentId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PaymentApi#cancelPayment");
    e.printStackTrace();
}
```
```
Please refer to the below document for more information :
https://www.paypay.ne.jp/opa/doc/v1.0/dynamicqrcode#operation/cancelPayment 
```

<a name="capturePaymentAuth"></a>
# **capturePaymentAuth**
> PaymentDetails capturePaymentAuth(body)

Capture a payment authorization

This api is used to capture the payment authorization for a payment  **Timeout: 30s** 

### Example
```java
// Import classes:
//import com.paypay.ApiException;
//import com.paypay.api.PaymentApi;



PaymentApi apiInstance = new PaymentApi();

CaptureObject captureObject = new CaptureObject();
      captureObject.setMerchantCaptureId("MERCHANT_CAPTURE_ID");//UNIQUE ID
      captureObject.setMerchantPaymentId("MERCHANT_PAYMENT_ID");
      captureObject.setAmount(new MoneyAmount().amount(1).currency(MoneyAmount.CurrencyEnum.JPY));
      captureObject.setOrderDescription("ORDER_DESCRIPTION");
      captureObject.setRequestedAt(Instant.now().getEpochSecond());

try {
    PaymentDetails result = apiInstance.capturePaymentAuth(captureObject);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PaymentApi#capturePaymentAuth");
    e.printStackTrace();
}
```

```
Please refer to the below document for more information :
https://www.paypay.ne.jp/opa/doc/v1.0/dynamicqrcode#operation/capturePaymentAuth
```

<a name="createPayment"></a>
# **createPayment**
> PaymentDetails createPayment(body, agreeSimilarTransaction)

Create a payment

Create a direct debit payment and start the money transfer.  **Timeout: 30s** 

### Example
```java
// Import classes:
//import com.paypay.ApiException;
//import com.paypay.api.PaymentApi;



PaymentApi apiInstance = new PaymentApi();

Payment payment = new Payment();
      payment.setAmount(new MoneyAmount().amount(1).currency(MoneyAmount.CurrencyEnum.JPY));
      payment.setMerchantPaymentId("MERCHANT_PAYMENT_ID");
      payment.setUserAuthorizationId("USER_AUTHORIZATION_ID");
      payment.setRequestedAt(Instant.now().getEpochSecond());
      payment.setStoreId("STORE_ID");
      payment.setTerminalId("TERMINAL_ID");
      payment.setOrderReceiptNumber("ORDER_RECEIPT_NUMBER");
      payment.setOrderDescription("ORDER_DESCRIPTION");
      MerchantOrderItem merchantOrderItem =
              new MerchantOrderItem()
                      .category("pasteries").name("Moon Cake")
                      .productId("PRODUCT_ID").quantity(1)
                      .unitPrice(new MoneyAmount().amount(10).currency(MoneyAmount.CurrencyEnum.JPY));
      List<MerchantOrderItem> merchantOrderItems = new ArrayList<>();
      merchantOrderItems.add(merchantOrderItem);
      payment.setOrderItems(new ArrayList<MerchantOrderItem>(merchantOrderItems));

boolean agreeSimilarTransaction = true; // Boolean | (Optional) If the parameter is set to true, the payment duplication check will be bypassed. 

try {
    PaymentDetails result = apiInstance.createPayment(body, agreeSimilarTransaction);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PaymentApi#createPayment");
    e.printStackTrace();
}
```

```
Please refer to the below document for more information :
https://www.paypay.ne.jp/opa/doc/v1.0/direct_debit#operation/createPayment
```


<a name="createQRCode"></a>
# **createQRCode**
> QRCodeDetails createQRCode(body)

Create a Code

Create a Code to receive payments.  **Timeout: 30s** 

### Example
```java
// Import classes:
//import com.paypay.ApiException;
//import com.paypay.api.PaymentApi;



PaymentApi apiInstance = new PaymentApi();

QRCode qrCode = new QRCode();
      qrCode.setAmount(new MoneyAmount().amount(amount).currency(MoneyAmount.CurrencyEnum.JPY));
      qrCode.setMerchantPaymentId(MERCHANT_PAYMENT_ID);
      qrCode.setCodeType("ORDER_QR");
      qrCode.requestedAt(Instant.now().getEpochSecond());
      qrCode.redirectUrl("REDIRECT_URL");
      qrCode.redirectType(QRCode.RedirectTypeEnum.WEB_LINK);//For Deep Link, RedirectTypeEnum.APP_DEEP_LINK
      qrCode.setOrderDescription("ORDER_DESCRIPTION");
      MerchantOrderItem merchantOrderItem =
              new MerchantOrderItem()
                      .category("Psteries").name("Moon Cake")
                      .productId("PRODUCT_ID").quantity(1)
                      .unitPrice(new MoneyAmount().amount(10).currency(MoneyAmount.CurrencyEnum.JPY));
      List<MerchantOrderItem> merchantOrderItems = new ArrayList<>();
      merchantOrderItems.add(merchantOrderItem);
      qrCode.setOrderItems(merchantOrderItems);

try {
    QRCodeDetails result = apiInstance.createQRCode(qrCode);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PaymentApi#createQRCode");
    e.printStackTrace();
}
```

```
Please refer to the below document for more information :
https://www.paypay.ne.jp/opa/doc/v1.0/dynamicqrcode#operation/createQRCode
```

<a name="deleteQRCode"></a>
# **deleteQRCode**
> NotDataResponse deleteQRCode(codeId)

Delete a Code

Delete a created Code.  **Timeout: 15s** 

### Example
```java
// Import classes:
//import com.paypay.ApiException;
//import com.paypay.api.PaymentApi;



PaymentApi apiInstance = new PaymentApi();

String codeId = "QR_CODE_ID"; // String 

try {
    NotDataResponse result = apiInstance.deleteQRCode(codeId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PaymentApi#deleteQRCode");
    e.printStackTrace();
}
```

```
 Please refer to the below document for more information :
https://www.paypay.ne.jp/opa/doc/v1.0/dynamicqrcode#operation/deleteQRCode
```


<a name="getPaymentDetails"></a>
# **getPaymentDetails**
> PaymentDetails getPaymentDetails(merchantPaymentId)

Get payment details

Get payment details.  **Timeout: 15s** 

### Example
```java
// Import classes:
//import com.paypay.ApiException;
//import com.paypay.api.PaymentApi;



PaymentApi apiInstance = new PaymentApi();

String merchantPaymentId = "MERCHANT_PAYMENT_ID"; // String

try {
    PaymentDetails result = apiInstance.getPaymentDetails(merchantPaymentId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PaymentApi#getPaymentDetails");
    e.printStackTrace();
}
```

```
 Please refer to the below document for more information :
https://www.paypay.ne.jp/opa/doc/v1.0/direct_debit#operation/getPaymentDetails
```

<a name="getRefundDetails"></a>
# **getRefundDetails**
> RefundDetails getRefundDetails(merchantRefundId)

Get refund details

Get refund details.  **Timeout: 15s** 

### Example
```java
// Import classes:
//import com.paypay.ApiException;
//import com.paypay.api.PaymentApi;



PaymentApi apiInstance = new PaymentApi();

String merchantRefundId = "MERCHANT_REFUND_ID"; // String 

try {
    RefundDetails result = apiInstance.getRefundDetails(merchantRefundId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PaymentApi#getRefundDetails");
    e.printStackTrace();
}
```

```
 Please refer to the below document for more information :
https://www.paypay.ne.jp/opa/doc/v1.0/dynamicqrcode#operation/getRefundDetails
```

<a name="refundPayment"></a>
# **refundPayment**
> RefundDetails refundPayment(body)

Refund a payment

Refund a payment.  **Timeout: 30s** 

### Example
```java
// Import classes:
//import com.paypay.ApiException;
//import com.paypay.api.PaymentApi;



PaymentApi apiInstance = new PaymentApi();

Refund refund = new Refund();
      refund.setAmount(new MoneyAmount().amount(1).currency(MoneyAmount.CurrencyEnum.JPY));
      refund.setMerchantRefundId("MERCHANT_REFUND_ID");
      refund.setPaymentId("PAYMENT_ID");
      refund.setReason("REFUND REASON");
      refund.setRequestedAt(Instant.now().getEpochSecond())

try {
    RefundDetails result = apiInstance.refundPayment(refund);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PaymentApi#refundPayment");
    e.printStackTrace();
}
```

```
Please refer to the below document for more information :
https://www.paypay.ne.jp/opa/doc/v1.0/dynamicqrcode#operation/refundPayment
```


<a name="revertAuth"></a>
# **revertAuth**
> RevertAuthResponse revertAuth(body)

Revert a payment authorization

This api is used in case, the merchant wants to cancel the payment authorization because of cancellation of the order by the user.  **Timeout: 30s** 

### Example
```java
// Import classes:
//import com.paypay.ApiException;
//import com.paypay.api.PaymentApi;



PaymentApi apiInstance = new PaymentApi();

PaymentStateRevert body = new PaymentStateRevert();
      payment.setPaymentId("PAYMENT_ID");
      payment.merchantRevertId("MERCHANT_REVERT_AUTH_ID");
      payment.setRequestedAt(Instant.now().getEpochSecond()); | Revert Authorized Order Request

try {
    RevertAuthResponse result = apiInstance.revertAuth(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PaymentApi#revertAuth");
    e.printStackTrace();
}
```

```
Please refer to the below document for more information :
https://www.paypay.ne.jp/opa/doc/v1.0/dynamicqrcode#operation/revertAuth
```