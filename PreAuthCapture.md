# PreAuth Capture
Allow a merchant to block money in the user's wallet and capture later.

# Integration

## Overview
PayPay's Payment SDK is the simplest way to achieve a smooth, quick integration. With PayPay's Payment SDK, you can build a custom Payment checkout process to suit your needs and fit within your branding guidelines.

# When to use PreAuth and Capture
PreAuth & Capture flow is recommended normally in the following scenarios
- Merchant need to block money at the time of creating an order and take the money at the time of fulfilling the order
- It is used in On demand taxis, On demand services, Ecommerce websites, etc

## Understanding the Payment Flow
Following diagram defines the flow for PreAuth & Capture.
-- provide image explaining the flow

We recommend that the merchant implements a Polling of the Get payment Details API with a 4-5 second interval in order to know the status of the transaction.

## Lets get Started
Once you have understood the payment flow, before we start the integration make sure you have:

Before integrating with the SDK, run through this checklist:
- [Registered](https://stg-www.paypay-corp.co.jp/dev-panel/account/signup) for a PayPay developer/merchant Account
- Get the API key and secret from the Developer Panel. 
- Use the sandbox API Keys to test out the integration

## Integrating PreAuth & Capture

### Installation

```groovy
#To install the API client library to your local repository, simply execute:
gradle install
#Maven: Add this dependency to your project's POM:
<dependency>
    <groupId>jp.ne.paypay</groupId>
    <artifactId>paypayopa</artifactId>
    <version>0.5.0</version>
    <scope>compile</scope>
</dependency>
#Gradle: Add this dependency to your project's build file:
compile "jp.ne.paypay:paypayopa:0.5.0"
```
### Build your Client
Build your client by adding your API Key and Secret like defined below. We enable API Authentication using HMAC, however the SDK will take care of the authentication by itself. [Click here](https://www.paypay.ne.jp/opa/doc/v1.0/preauth_capture#tag/Api-Authentication) if you want to know more about the authentication. 
```java
    ApiClient apiClient = new Configuration().getDefaultApiClient();
    apiClient.setProductionMode(false); //true for production and false for sandbox. Default is sandbox
    apiClient.setApiKey("YOUR_API_KEY");
    apiClient.setApiSecretKey("YOUR_API_SECRET");
    apiClient.setAssumeMerchant("YOUR_MERCHANT_KEY");
```
# Acquire User Authorization
First of all you need to acquire a user Authorization. Following diagram defines the flow to acquire a user authorization.

![](https://www.paypay.ne.jp/opa/doc/v1.0/imgs/authorization-sequence.png)

In order to acquire an authorization you need to create a JWT Token -

| Claim  | Required  |Type   | Description  |  
|---|---|---|---|
|scopes   |  Yes |Array of string |Items Enum: 'direct_debit' 'cashback' 'get_balance' 'quick_pay' 'continuous_payments' 'merchant_topup' 'pending_payments' 'user_notification' 'user_topup' 'user_profile' 'preauth_capture_native' 'preauth_capture_transaction' 'push_notification' 'notification_center_ob' 'notification_center_ab' 'notification_center_tl' Scopes of the user authorization   |
|nonce   |  Yes |string  |Random generated string   |
| redirectType |No    |string  |Default: 'WEB_LINK' Enum: 'APP_DEEP_LINK' 'WEB_LINK' Parameter to decide whether to redirect to merchant app or merchant web application   |
|redirectUrl   |Yes   |string  |The callback endpoint provided by client. For 'WEB_LINK' it must be HTTPS, and its domain should be in the allowed authorization callback domains|
|referenceId   |Yes   |string  |The id used to identify the user in merchant system. It will be stored in the PayPay db for reconsilliation purpose |
|phoneNumber   |No    |string  |The user mobile phone number |
|deviceId      |No    |string  |The user mobile phone device id. If it is provided, we can use it to verify the user and skip the SMS verification, so as to provide more fluent UX |
|userAgent     |Yes   |string  |The User agent of the web browser. When redirectType is 'WEB_LINK' this parameter is provided, on mobile devices PayPay tries to open the browser that the merchant website is using. |

```java
PaymentApi apiInstance = new PaymentApi(apiClient);
AccountLinkQRCode accountLinkQRCode = new AccountLinkQRCode();
List<AuthorizationScope> scopes = new ArrayList<>();
	scopes.add(AuthorizationScope.DIRECT_DEBIT);
	accountLinkQRCode.setScopes(scopes);
	accountLinkQRCode.setNonce(RandomStringUtils.randomAlphanumeric(8).toLowerCase());
	accountLinkQRCode.setDeviceId("device_id");
	accountLinkQRCode.setRedirectUrl("merchant.domain/test");
	accountLinkQRCode.setPhoneNumber("phone_number");
	accountLinkQRCode.setReferenceId("reference_id");
	accountLinkQRCode.setRedirectType(QRCode.RedirectTypeEnum.WEB_LINK);
	
LinkQRCodeResponse response = apiInstance.createAccountLinkQRCode(accountLinkQRCode);
// Printing if the method call was SUCCESS
System.out.println(response.getResultInfo().getCode());
// Printing the link to the generated QR Code
System.out.println(response.getData().getLinkQRCodeURL());

```
Once the user has granted authorization, we will return the UserAuthorizationID as a part of the JWT Token in response/ webhook
```java
  // Retrieving userAuthorizationId from response JWT
   String jwtAudience = "paypay.ne.jp";
   String jwtToken = "JWT_TOKEN";
   String apiSecretKey = "YOUR_API_SECRET_KEY";
  JwtResponseDto jwtResponseDto = JwtUtil.validateJWT(jwtToken, jwtAudience, apiSecretKey);
 
```
### Create a Payment Authorization
In order to create a payment authorization, you will need to send a request to us with the following parameters:

| Field  | Required  |Type   | Description  |  
|---|---|---|---|
|merchantPaymentId   |  Yes |string <= 64 characters  |The unique payment transaction id provided by merchant   |
|userAuthorizationId   |  Yes |string <= 64 characters  |The PayPay user reference id returned by the user authorization flow   |
| amount  |Yes   |integer <= 11 characters  |Amount the user has to Pay   |
|requestedAt   |  Yes |integer  |Epoch timestamp in seconds|
|orderDescription   |No   |string <= 255 characters|Description of the Order |

For details of all the request and response parameters , check our [API Documentation guide](https://www.paypay.ne.jp/opa/doc/v1.0/preauth_capture#operation/createAuth)

```java
//Creating the payload to create a Payment Authorization, additional parameters can be added basis the API Documentation

Payment payment = new Payment();
payment.setAmount(new MoneyAmount().amount(amount).currency(MoneyAmount.CurrencyEnum.JPY));
payment.setMerchantPaymentId("merchant_payment_id");
payment.setUserAuthorizationId("user_authorization_id");
payment.setRequestedAt(Instant.now().getEpochSecond());
payment.setOrderDescription("Mune's Favourite Cake");
String agreeSimilarTransaction = "true";

// Calling the method to create a payment
PaymentApi apiInstance = new PaymentApi(apiClient);
PaymentDetails response = apiInstance.createPaymentAuthorization(payment, agreeSimilarTransaction);
// Printing if the method call is SUCCESS, this does not mean the payment is a success
System.out.println(response.getResultInfo().getCode());
//Printing if the status is "AUTHORIZED"
System.out.println(response.getData().getStatus());
```
Did you get **SUCCESS** in the print statement above and status is 'AUTHORIZED', if yes then the API execution has happened correctly.

### Get Payment Details
Now that you have created a payment authorization, the next  step is to implement polling to get Payment Details. We recommend a 4-5 second interval between requests. Following are the important parameters that you can provide for this method:

| Field  | Required  |Type   | Description  |  
|---|---|---|---|
|merchantPaymentId   |  Yes |string <= 64 characters  |The unique payment transaction id provided by merchant   |
```java

// Calling the method to get payment details
PaymentApi apiInstance = new PaymentApi(apiClient);
PaymentDetails response = apiInstance.getPaymentDetails("merchantPaymentId");
// Printing if the method call was SUCCESS, this does not mean the payment was a success
System.out.println(response.getResultInfo().getCode());
// Printing if the transaction status for the code has COMPLETED/ AUTHORIZED
System.out.println(response.getData().getStatus());
```
Did you get **SUCCESS** in the print statement above, if yes then the API execution has happened correctly.
On successful payment authorization, the status will be AUTHORIZED.
Once the payment is captured, the status will be COMPLETED.

For details of all the request and response parameters , check our [API Documentation guide](https://www.paypay.ne.jp/opa/doc/v1.0/preauth_capture#operation/getPaymentDetails)

### Cancel a payment
So you want to cancel a Payment. In most cases this should not be needed for payment happening in this flow, however following can be a case when this might be needed.
- Polling for Get Payment Details timeout, and you are uncertain of the status

Note: The Cancel API can be used until 00:14:59 AM the day after the Payment has happened. For 00:15 AM or later, please call the refund API to refund the payment.

Following are the important parameters that you can provide for this method:

| Field  | Required  |Type   | Description  |  
|---|---|---|---|
|merchantPaymentId   |  Yes |string <= 64 characters  |The unique payment transaction id provided by merchant   |
```java
// Calling the method to cancel a Payment
PaymentApi apiInstance = new PaymentApi(apiClient);
NotDataResponse response = apiInstance.cancelPayment("merchantPaymentId");
// Printing if the method call was SUCCESS
System.out.println(response.getResultInfo().getCode());

```
Did you get **SUCCESS** in the print statement above, if yes then the API execution has happened correctly.

For details of all the request and response parameters , check our [API Documentation guide](https://www.paypay.ne.jp/opa/doc/v1.0/preauth_capture#operation/cancelPayment)

### Refund a payment
So the user has decided to return the goods they have purchased and needs to be given a refund. Following are the important parameters that you can provide for this method:

| Field  | Required  |Type   | Description  |  
|---|---|---|---|
|merchantRefundId   |  Yes |string <= 64 characters  |The unique refund transaction id provided by merchant  |
|paymentId   |  Yes |string <= 64 characters  |The payment transaction id provided by PayPay |
|amount   |  Yes |integer <= 11 characters  |The amount to be refunded |
|reason   |  No |integer <= 11 characters  |The reason for refund |
```java

// Creating the payload to refund a Payment, additional parameters can be added basis the API Documentation
Refund refund = new Refund();
      refund.setAmount(new MoneyAmount().amount(1).currency(MoneyAmount.CurrencyEnum.JPY));
      refund.setMerchantRefundId("merchant_refund_id");
      refund.setPaymentId("paypay_payment_id");
      refund.setRequestedAt(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
      refund.setReason("reason for refund");

// Calling the method to refund a Payment
PaymentApi apiInstance = new PaymentApi(apiClient);
RefundDetails response = apiInstance.refundPayment(refund);
// Printing if the method call was SUCCESS
System.out.println(response.getResultInfo().getCode());

```
Did you get **SUCCESS** in the print statement above, if yes then the API execution has happened correctly.

For details of all the request and response parameters , check our [API Documentation guide](https://www.paypay.ne.jp/opa/doc/v1.0/preauth_capture#operation/refundPayment). **Please note that currently we only support 1 refund per order.**

### Fetch refund status and details
So you want to confirm the status of the refund, maybe because the request for the refund timed out when you were processing the same. Following are the important parameters that you can provide for this method:

| Field  | Required  |Type   | Description  |  
|---|---|---|---|
|merchantRefundId   |  Yes |string <= 64 characters  |The unique refund transaction id provided by merchant  |

```java
// Calling the method to get Refund Details
PaymentApi apiInstance = new PaymentApi(apiClient);
RefundDetails response = apiInstance.getRefundDetails("merchantRefundId");
// Printing if the method call was SUCCESS
System.out.println(response.getResultInfo().getCode());
```
Did you get **SUCCESS** in the print statement above, if yes then the API execution has happened correctly.

For details of all the request and response parameters , check our [API Documentation guide](https://www.paypay.ne.jp/opa/doc/v1.0/preauth_capture#operation/getRefundDetails).

### Capture a payment authorization
So you are implementing a PreAuth and Capture, and hence want to capture the payment later. If you want to increase the amount, we will send a notification to the user asking for consent.
In this case, please ensure you have created payment authorization. Following are the important parameters that you can provide for this method:

| Field  | Required  |Type   | Description  |  
|---|---|---|---|
|merchantPaymentId   |  Yes |string <= 64 characters  |The unique payment transaction id provided by merchant   |
|merchantCaptureId   |  Yes |string <= 64 characters  |The unique capture transaction id provided by merchant  |
| amount  |Yes   |integer <= 11 characters  |Amount to be captured   |
| requestedAt  |Yes   |integer |Epoch timestamp in seconds   |
|orderDescription   |Yes   |string <= 255 characters|Description of the Capture for the user|

```java

// Creating the payload to capture a Payment Authorization, additional parameters can be added basis the API Documentation
CaptureObject captureObject = new CaptureObject();
      captureObject.setMerchantCaptureId("merchant_capture_id");
      captureObject.setMerchantPaymentId("merchant_payment_id");
      captureObject.setAmount(new MoneyAmount().amount(1).currency(MoneyAmount.CurrencyEnum.JPY));
      captureObject.setOrderDescription("Order Shipped, Cake with toppings");
      captureObject.setRequestedAt(Instant.now().getEpochSecond());

// Calling the method to Capture a Payment Authorization
PaymentApi apiInstance = new PaymentApi(apiClient);
PaymentDetails response = apiInstance.capturePaymentAuth(captureObject);
// Printing if the method call was SUCCESS
System.out.println(response.getResultInfo().getCode());
```
Did you get **SUCCESS** in the print statement above, if yes then the API execution has happened correctly.

For details of all the request and response parameters , check our [API Documentation guide](https://www.paypay.ne.jp/opa/doc/v1.0/preauth_capture#operation/capturePaymentAuth).

### Revert a payment authorization
So the user has cancelled the order while the payment status was still AUTHORIZED, please use the revert a payment authorization method to refund back to the user. Following are the important parameters that you can provide for this method:

| Field  | Required  |Type   | Description  |  
|---|---|---|---|
|merchantRevertId   |  Yes |string <= 64 characters  |The unique revert transaction id provided by merchant   |
|paymentId   |  Yes |string <= 64 characters  |The payment transaction id provided by PayPay |
|requestedAt  |Yes   |integer |Epoch timestamp in seconds   |
|reason   |No   |string <= 255 characters|Reason for reverting the payment authorization|

```java
// Creating the payload to revert a Payment Authorization, additional parameters can be added basis the API Documentation
PaymentStateRevert payment = new PaymentStateRevert();
      payment.setPaymentId("paypay_payment_id");
      payment.merchantRevertId("merchant_revert_id");
      payment.setRequestedAt(Instant.now().getEpochSecond());
      payment.setReason("reason for refund");

// Calling the method to Revert a Payment Authorization
PaymentApi apiInstance = new PaymentApi(apiClient);
RevertAuthResponse response = apiInstance.revertAuth(payment);
// Printing if the method call was SUCCESS
System.out.println(response.getResultInfo().getCode());
```
Did you get **SUCCESS** in the print statement above, if yes then the API execution has happened correctly.

For details of all the request and response parameters , check our [API Documentation guide](https://www.paypay.ne.jp/opa/doc/v1.0/preauth_capture#operation/revertAuth).

### Error Handling
PayPay uses HTTP response status codes and error code to indicate the success or failure of the requests. With this information, you can decide what error handling strategy to use. In general, PayPay returns the following http status codes.

#### HTTP 2xx
**200**
Everything works as expected.

**201**
The requested resource(e.g. Create payment authorization) was created.

**202**
Means the request is received, and will be processed sometime later.

#### HTTP 4xx
**400**
This status code indicates an error because the information provided in request is not able to be processed. The following OPA error code may be returned.

- INVALID_PARAMS
The information provided by the request contains invalid data. E.g. unsupported currency.

- UNACCEPTABLE_OP
The requested operation is not able to be processed due to the current condition. E.g. the transaction limit exceeded.

- NO_SUFFICIENT_FUND
There is no sufficient fund for the transaction.

**401**
This status code indicates an authorization error. The following OPA error code may be returned.

- UNAUTHORIZED
No valid api key and secret provided.

- OP_OUT_OF_SCOPE
The operation is not permitted.

**404**
This status code indicates that the requested resource is not existing in the system.

**429**
This status code indicates that the client sent too many requests in a specific period of time, and hit the rate limits. You should slow down the request sending or contact us to rise your limit.

#### HTTP 5xx
**500**
This status code indicates that something went wrong on the PayPay side. A few OPA error code could be returned.

- TRANSACTION_FAILED
This code means the transaction is failed on the PayPay side. You can create new transactions for the same purpose with reasonable backoff time.

- INTERNAL_SERVER_ERROR
This code means that something goes wrong, but we don't know exactly if the transaction has happened or not. It should be treated as unknown payment status.

**502,503,504**
Treated as unknown payment status.

#### Timeout
The recommended timeout setting is specified in each API. The most important one is for the payment creation api, where the read timeout should not be less than 30 seconds. When timeout happens, it should be treated as unknown payment status.

#### Handle unknown payment status
There are two ways to react with this situation:
- Use the query api to query the transaction status. If the original transaction was failed or not found in PayPay, you can start a new transaction for the same purpose.
- Or, you can cancel the transaction, if the cancel api is provided. After the cancellation is accepted, you can start a new transaction for the same purpose.
 

### Response code list
**Common response code**

| Status  | CodeId  |Code   | Message  |  
|---|---|---|---|
|200|	08100001|	SUCCESS|	Success|
|202|	08100001|	REQUEST_ACCEPTED|	Request accepted|
|400|	08100006|	INVALID_REQUEST_PARAMS|	Invalid request params|
|401|	08100023|	OP_OUT_OF_SCOPE|	The operation is not permitted|
|400|	08100024|	MISSING_REQUEST_PARAMS|	|
|401|	08100016|	UNAUTHORIZED|	Unauthorized request|
|404|	08100007|	OPA_CLIENT_NOT_FOUND|	OPA Client not found|
|429|	08100998|	RATE_LIMIT|	Too many requests|
|500|	08100026|	SERVICE_ERROR|	|
|500|	08101000|	INTERNAL_SERVER_ERROR|	Something went wrong on PayPay service side|
|503|	08100999|	MAINTENANCE_MODE|	Sorry, we are down for scheduled maintenance|


**Get payment details**

|Status	|CodeId	|Code	|Message|
|---|---|---|---|
|400|	01652075|	DYNAMIC_QR_PAYMENT_NOT_FOUND|	Dynamic QR payment not found|
|400|	01650000|	DYNAMIC_QR_BAD_REQUEST|	Dynamic QR bad request error|


**Cancel a Payment**

|Status	|CodeId	|Code	|Message|
|---|---|---|---|
|400	|00200044	|ORDER_NOT_REVERSIBLE|	Order cannot be reversed|
|500	|00200034	|INTERNAL_SERVER_ERROR|	Request timed out|

**Refund a payment**

|Status	|CodeId	|Code	|Message|
|---|---|---|---|
|400	|00200004	|INVALID_PARAMS	|Invalid parameters received|
|400	|00200013	|UNACCEPTABLE_OP	|Order cannot be refunded|
|400	|00200014	|UNACCEPTABLE_OP	|Multiple refund not allowed|
|400	|00200015	|INVALID_PARAMS	|Invalid refund amount|
|400	|01103027	|CANCELED_USER	|Canceled user|
|404	|00200001	|RESOURCE_NOT_FOUND	|Order not found|
|500	|00200002	|TRANSACTION_FAILED	|Transaction failed|
|500	|00200003	|TRANSACTION_FAILED	|Transaction failed|
|500	|00800017	|TRANSACTION_FAILED	|Balance exceeded|
|500	|00200034	|INTERNAL_SERVER_ERROR	|Request timed out|

**Fetch refund status and details**

|Status	|CodeId	|Code	|Message|
|---|---|---|---|
|404	|00200018|	NO_SUCH_REFUND_ORDER	|Refund not found|
|500	|00200034|	INTERNAL_SERVER_ERROR	|Request timed out|

**Create a payment authorization**

|Status	|CodeId	|Code	|Message|
|---|---|---|---|
|400	|	|CANCELED_USER	|Canceled user|
|400	|	|INVALID_PARAMS	|Invalid parameters received|
|400	|	|NO_SUFFICIENT_FUND	|The user's balance is insufficient to make payment.|
|400	|	|UNSUPPORTED_PAYMENT_METHOD	|Payment method is not supported|
|400	|	|PRE_AUTH_CAPTURE_UNSUPPORTED_MERCHANT	|Merchant do not support Pre-Auth-Capture|
|400	|	|PRE_AUTH_CAPTURE_INVALID_EXPIRY_DATE	|Provided Expiry Date is above the allowed limit of Max allowed expiry days|
|400	|	|SUSPECTED_DUPLICATE_PAYMENT	|If a merchant tries collect same amount money from same user again within 5 minutes, the request would be rejected with this very error code. This design is mainly to prevent the duplicated payments which are usually caused by design flaws in client code. However, sometimes, the merchant would intentionally collect mutiple same amount payments from a single user. In such case, the client need to send a specific parameter in order to bypass the duplication check. This is detailed in the payment creation api spec.|
|400	|	|UNACCEPTABLE_OP	|The requested operation is not able to be processed due to the current condition. E.g. the transaction limit exceeded|
|401	|	|USER_STATE_IS_NOT_ACTIVE	|Inactive user|
|401	|	|INVALID_USER_AUTHORIZATION_ID	|If the user authorization id is expired or revoked by the use. The client need to go through the authorization flow again to get the user authorization id|
|401	|	|EXPIRED_USER_AUTHORIZATION_ID	|The user authorization ID expired.|
|404	|	|NO_VALID_PAYMENT_METHOD	|No available payment method.|
|404	|	|PAYMENT_METHOD_NOT_FOUND	|Payment method not found|
|500	|	|TRANSACTION_FAILED	|This code means the transaciton is failed in PayPay side. You can create new transaction for the same purpose with reasonable backoff time|

**Capture a payment authorization**

|Status	|CodeId	|Code	|Message|
|---|---|---|---|
|202	|08300103	|USER_CONFIRMATION_REQUIRED	|User confirmation required as requested amount is above allowed limit|
|400	|00400035	|UNACCEPTABLE_OP	|Total transaction limit exceeds merchant limit|
|400	|00200039	|ALREADY_CAPTURED	|Cannot capture already captured acquiring order|
|400	|01103027	|CANCELED_USER	|Canceled user|
|400	|00400062	|HIGHER_CAPTURE_NOT_PERMITTED	|Merchant not allowed to capture higher amount|
|400	|00200043	|ORDER_EXPIRED	|Order cannot be captured or updated as it has already expired|
|400	|00200035	|ORDER_NOT_CAPTURABLE	|Order is not capturable|
|400	|00200038	|REAUTHORIZATION_IN_PROGRESS	|Order is being reauthorized|
|400	|00400064	|TOO_CLOSE_TO_EXPIRY	|Order cannot be reauthorized as request is too close to expiry time|

**Revert a payment authorization**

|Status	|CodeId	|Code	|Message|
|---|---|---|---|
|400	|00200042|ORDER_NOT_CANCELABLE	|Order is not cancelable|




