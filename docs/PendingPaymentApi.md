# PendingPaymentApi

Method | HTTP request | Description
------------- | ------------- | ------------- 
[**createPendingPayment**](docs/PendingPaymentApi.md#createPendingPayment) | **POST** /v1/requestOrder | Create a pending payment
[**getPaymentDetails**](docs/PendingPaymentApi.md#getPaymentDetails) | **GET** /v1/requestOrder/{merchantPaymentId} | Get payment details
[**cancelPendingOrder**](docs/PendingPaymentApi.md#cancelPendingOrder) | **DELETE** /v1/requestOrder/{merchantPaymentId} | Cancel a Pending Order
[**getRefundDetails**](docs/PendingPaymentApi.md#getRefundDetails) | **GET** /v2/refunds/{merchantRefundId} | Get refund details
[**refundPayment**](docs/PendingPaymentApi.md#refundPayment) | **POST** /v1/requestOrder/refunds | Refund a payment


<a name="createPendingPayment"></a>
# **createPendingPayment**
> PaymentDetails createPendingPayment(body)

Create a pending payment

Sends a push notification to the user requesting payment.  **Timeout: 30s** 

### Example
```java
//Import classes:
import jp.ne.paypay.ApiException;
import jp.ne.paypay.api.PendingPaymentApi;

PendingPaymentApi apiInstance = new PendingPaymentApi(apiClient);

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
      payment.setOrderItems(merchantOrderItems);

try {
    PaymentDetails result = apiInstance.createPendingPayment(payment);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PendingPaymentApi#createPendingPayment");
    System.out.println(e.getResponseBody());
}
```

```
Please refer to the below document for more information :
https://www.paypay.ne.jp/opa/doc/v1.0/pending_payments#operation/createPayment
```

<a name="cancelPendingOrder"></a>
# **cancelPendingOrder**
> NotDataResponse cancelPendingOrder(merchantPaymentId)

Cancel a payment

This API is used delete the pending order.

### Example
```java
//Import classes:
import jp.ne.paypay.ApiException;
import jp.ne.paypay.api.PendingPaymentApi;

PendingPaymentApi apiInstance = new PendingPaymentApi(apiClient);

String merchantPaymentId = "MERCHANT_PAYMENT_ID"; 

try {
      NotDataResponse result = apiInstance.cancelPendingOrder(merchantPaymentId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PendingPaymentApi#cancelPendingOrder" + e.getMessage());
      System.err.println(e.getResponseBody());
}
```
```
Please refer to the below document for more information :
https://www.paypay.ne.jp/opa/doc/v1.0/pending_payments#operation/cancelPendingOrder
```

<a name="getPaymentDetails"></a>
# **getPaymentDetails**
> PaymentDetails getPaymentDetails(merchantPaymentId)

Get payment details

Get payment details.  **Timeout: 15s** 

### Example
```java
// Import classes:
import jp.ne.paypay.ApiException;
import jp.ne.paypay.api.PendingPaymentApi;

PendingPaymentApi apiInstance = new PendingPaymentApi(apiClient);

String merchantPaymentId = "MERCHANT_PAYMENT_ID"; 

try {
    PaymentDetails result = apiInstance.getPaymentDetails(merchantPaymentId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PendingPaymentApi#getPaymentDetails");
    System.out.println(e.getResponseBody());
}
```

```
Please refer to the below document for more information :
https://www.paypay.ne.jp/opa/doc/v1.0/pending_payments#operation/getPaymentDetails
```

<a name="getRefundDetails"></a>
# **getRefundDetails**
> RefundDetails getRefundDetails(merchantRefundId)

Get refund details

Get refund details.  **Timeout: 15s** 

### Example
```java
// Import classes:
import jp.ne.paypay.ApiException;
import jp.ne.paypay.api.PendingPaymentApi;

PendingPaymentApi apiInstance = new PendingPaymentApi(apiClient);

String merchantRefundId = "MERCHANT_REFUND_ID"; // String 

try {
    RefundDetails result = apiInstance.getRefundDetails(merchantRefundId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PendingPaymentApi#getRefundDetails");
    System.out.println(e.getResponseBody());
}
```

```
Please refer to the below document for more information :
https://www.paypay.ne.jp/opa/doc/v1.0/pending_payments#operation/getRefundDetails
```

<a name="refundPayment"></a>
# **refundPayment**
> RefundDetails refundPayment(body)

Refund a payment

Refund a payment.  **Timeout: 30s** 

### Example
```java
// Import classes:
import jp.ne.paypay.ApiException;
import jp.ne.paypay.api.PendingPaymentApi;

PendingPaymentApi apiInstance = new PendingPaymentApi(apiClient);

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
    System.err.println("Exception when calling PendingPaymentApi#refundPayment");
    System.out.println(e.getResponseBody());
}
```

```
Please refer to the below document for more information :
https://www.paypay.ne.jp/opa/doc/v1.0/pending_payments#operation/refundPayment
```