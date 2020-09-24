# Pending Payment
Sends a push notification requesting payment to a specific user for whom the merchant already has a user UserAuthorizationId.

# Integration

## Overview
PayPay's Payment SDK is the simplest way to achieve a smooth, quick integration. With PayPay's Payment SDK, you can build a custom Payment checkout process to suit your needs and fit within your branding guidelines.

# When to use Pending Payment
Pending Payment flow is recommended normally in the following scenarios
- Merchant wants to provide user an option to receive their order but pay for them after a time period
- Merchant wants to simplify the checkout process without payment procedures

## Understanding the Payment Flow
Following diagram defines the flow for PreAuth & Capture.
-- provide image explaining the flow

We recommend that the merchant implements a Polling of the Get payment Details API with a 4-5 second interval in order to know the status of the transaction.

## Let's get Started
Once you have understood the payment flow, before we start the integration make sure you have:

Before integrating with the SDK, run through this checklist:
- [Registered](https://developer.paypay.ne.jp/account/signup) for a PayPay developer/merchant Account
- Get the API key and secret from the Developer Panel. 
- Use the sandbox API Keys to test out the integration

## Integrating Pending Payment

### Installation

```groovy
#To install the API client library to your local repository, simply execute:
gradle install
#Maven: Add this dependency to your project's POM:
<dependency>
    <groupId>jp.ne.paypay</groupId>
    <artifactId>paypayopa</artifactId>
    <version>0.6.0</version>
    <scope>compile</scope>
</dependency>
#Gradle: Add this dependency to your project's build file:
compile "jp.ne.paypay:paypayopa:0.6.0"
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
	scopes.add(AuthorizationScope.PENDING_PAYMENTS);
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
   String jwtToken = "JWT_TOKEN";
   String apiSecretKey = "YOUR_API_SECRET_KEY";
  JwtResponseDto jwtResponseDto = JwtUtil.validateJWT(jwtToken, apiSecretKey);
 
```
### Create a Pending Payment
In order to create a pending payment, you will need to send a request to us with the following parameters:

| Field  | Required  |Type   | Description  |  
|---|---|---|---|
|merchantPaymentId   |  Yes |string <= 64 characters  |The unique payment transaction id provided by merchant   |
|userAuthorizationId   |  Yes |string <= 64 characters  |The PayPay user reference id returned by the user authorization flow   |
|amount  |Yes   |integer <= 11 characters  |Amount the user has to Pay   |
|orderDescription   |No   |string <= 255 characters|Description of the Order |

For details of all the request and response parameters , check our [API Documentation guide](https://www.paypay.ne.jp/opa/doc/v1.0/pending_payments#operation/createPayment)

```java
//Creating the payload to create a Pending Payment, additional parameters can be added basis the API Documentation

Payment payment = new Payment();
payment.setAmount(new MoneyAmount().amount(amount).currency(MoneyAmount.CurrencyEnum.JPY));
payment.setMerchantPaymentId("merchant_payment_id");
payment.setUserAuthorizationId("user_authorization_id");
payment.setOrderDescription("Mune's Favourite Cake");

// Calling the method to create a payment
PendingPaymentApi apiInstance = new PendingPaymentApi(apiClient);
PaymentDetails response = apiInstance.createPendingPayment(payment);
// Printing if the method call is SUCCESS, this does not mean the payment is a success
System.out.println(response.getResultInfo().getCode());

```
Did you get **SUCCESS** in the print statement above, if yes then the API execution has happened correctly.

### Get Payment Details
Now that you have created a pending payment, the next  step is to implement polling to get Payment Details. We recommend a 4-5 second interval between requests. Following are the important parameters that you can provide for this method:

| Field  | Required  |Type   | Description  |  
|---|---|---|---|
|merchantPaymentId   |  Yes |string <= 64 characters  |The unique payment transaction id provided by merchant   |
```java

// Calling the method to get payment details
PendingPaymentApi apiInstance = new PendingPaymentApi(apiClient);
PaymentDetails response = apiInstance.getPaymentDetails("merchantPaymentId");
// Printing if the method call was SUCCESS, this does not mean the payment was a success
System.out.println(response.getResultInfo().getCode());
// Printing if the transaction status for the code is CREATED
System.out.println(response.getData().getStatus());
```
Did you get **SUCCESS** in the print statement above, if yes then the API execution has happened correctly.
On successful creation of pending payment, the status will be CREATED.
If the pending order is cancelled, the status will be CANCELED.
Once the user completes the payment, the status will be COMPLETED.

For details of all the request and response parameters , check our [API Documentation guide](https://www.paypay.ne.jp/opa/doc/v1.0/pending_payments#operation/getPaymentDetails)


### Cancel a payment
This is used when you want to delete the pending order

Following are the important parameters that you can provide for this method:

| Field  | Required  |Type   | Description  |  
|---|---|---|---|
|merchantPaymentId   |  Yes |string <= 64 characters  |The unique payment transaction id provided by merchant   |
```java
// Calling the method to cancel a pending order
PendingPaymentApi apiInstance = new PendingPaymentApi(apiClient);
NotDataResponse response = apiInstance.cancelPendingOrder("merchantPaymentId");
// Printing if the method call was SUCCESS
System.out.println(response.getResultInfo().getCode());
```
Did you get **SUCCESS** in the print statement above, if yes then the API execution has happened correctly.

For details of all the request and response parameters , check our [API Documentation guide](https://www.paypay.ne.jp/opa/doc/v1.0/pending_payments#operation/cancelPendingOrder)

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
      refund.setReason("reason for refund");

// Calling the method to refund a Payment
PendingPaymentApi apiInstance = new PendingPaymentApi(apiClient);
RefundDetails response = apiInstance.refundPayment(refund);
// Printing if the method call was SUCCESS
System.out.println(response.getResultInfo().getCode());

```
Did you get **SUCCESS** in the print statement above, if yes then the API execution has happened correctly.

For details of all the request and response parameters , check our [API Documentation guide](https://www.paypay.ne.jp/opa/doc/v1.0/pending_payments#operation/refundPayment). **Please note that currently we only support 1 refund per order.**

### Fetch refund status and details
So you want to confirm the status of the refund, maybe because the request for the refund timed out when you were processing the same. Following are the important parameters that you can provide for this method:

| Field  | Required  |Type   | Description  |  
|---|---|---|---|
|merchantRefundId   |  Yes |string <= 64 characters  |The unique refund transaction id provided by merchant  |

```java
// Calling the method to get Refund Details
PendingPaymentApi apiInstance = new PendingPaymentApi(apiClient);
RefundDetails response = apiInstance.getRefundDetails("merchantRefundId");
// Printing if the method call was SUCCESS
System.out.println(response.getResultInfo().getCode());
```
Did you get **SUCCESS** in the print statement above, if yes then the API execution has happened correctly.

For details of all the request and response parameters , check our [API Documentation guide](https://www.paypay.ne.jp/opa/doc/v1.0/pending_payments#operation/getRefundDetails).


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
|400|	08100024|	MISSING_REQUEST_PARAMS|	|
|401|	08100023|	OP_OUT_OF_SCOPE|	The operation is not permitted|
|401|	08100016|	UNAUTHORIZED|	Unauthorized request|
|401|	        |	INVALID_USER_AUTHORIZATION_ID|	The specified user authorization ID is invalid|
|401|	        |	EXPIRED_USER_AUTHORIZATION_ID|	The user authorization ID expired|
|404|	08100007|	OPA_CLIENT_NOT_FOUND|	OPA Client not found|
|429|	08100998|	RATE_LIMIT|	Too many requests|
|500|	08100026|	SERVICE_ERROR|	|
|500|	08101000|	INTERNAL_SERVER_ERROR|	Something went wrong on PayPay service side|
|503|	08100999|	MAINTENANCE_MODE|	Sorry, we are down for scheduled maintenance|

**Create a pending payment**

|Status	|CodeId	|Code	|Message|
|---|---|---|---|
|201|	|	SUCCESS|	Success|
|400|	|	DUPLICATE_REQUEST_ORDER|	Request order with same payment ID exists|
|400|	|	SUSPECTED_DUPLICATE_ORDER|	The same order is created multiple times|

**Get payment details**

|Status	|CodeId	|Code	|Message|
|---|---|---|---|
|400|	|	INVALID_PARAMS|	The set parameter is invalid|
|400|	|	REQUEST_ORDER_NOT_FOUND|	Request order not found|


**Cancel a Pending Order**

|Status	|CodeId	|Code	|Message|
|---|---|---|---|
|404	|	|REQUEST_ORDER_NOT_FOUND|	Request order not in valid state|
|409	|	|INVALID_REQUEST_ORDER_STATE|	Request timed out|

**Refund a payment**

|Status	|CodeId	|Code	|Message|
|---|---|---|---|
|400	|	|INVALID_PARAMS	|The set parameter is invalid|
|401	|	|USER_STATE_IS_NOT_ACTIVE	|The request cannot be accepted because the user status is Inactive|
|404	|	|REQUEST_ORDER_NOT_FOUND	|Request order not found|
|409	|	|INVALID_REQUEST_ORDER_STATE	|Request order not in valid state|

**Fetch refund status and details**

|Status	|CodeId	|Code	|Message|
|---|---|---|---|
|404	||	NO_SUCH_REFUND_ORDER	|Refund not found|
|400	||	INVALID_PARAMS	|The set parameter is invalid|
