# User API
The SDK will help the user to get User Authorization status and unlink the user from the client

# Integration

## Installation

```groovy
#To install the API client library to your local repository, simply execute:
gradle install
#Maven: Add this dependency to your project's POM:
<dependency>
    <groupId>jp.ne.paypay</groupId>
    <artifactId>paypayopa</artifactId>
    <version>0.8.0</version>
    <scope>compile</scope>
</dependency>
#Gradle: Add this dependency to your project's build file:
compile "jp.ne.paypay:paypayopa:0.8.0"
```
### Build your Client
Build your client by adding your API Key and Secret like defined below. We enable API Authentication using HMAC, however the SDK will take care of the authentication by itself. [Click here](https://www.paypay.ne.jp/opa/doc/v1.0/pending_payments#tag/Api-Authentication) if you want to know more about the authentication. 
```java
    ApiClient apiClient = new Configuration().getDefaultApiClient();
    apiClient.setProductionMode(false); //true for production and false for sandbox. Default is sandbox
    apiClient.setApiKey("YOUR_API_KEY");
    apiClient.setApiSecretKey("YOUR_API_SECRET");
    apiClient.setAssumeMerchant("YOUR_MERCHANT_KEY");
```

### Get User Authorization Status
To get the current authorization status of the user, use the following method with the parameter below

| Field  | Required  |Type   | Description  |  
|---|---|---|---|
|userAuthorizationId   |  Yes |string <= 64 characters  |The PayPay user reference id returned by the user authorization flow  |

```java

// Calling the method to get user authorization status
UserApi userApi = new UserApi(apiClient);
UserAuthorizationStatus response = userApi.getUserAuthorizationStatus("userAuthorizationId");
// Printing if the method call was SUCCESS
System.out.println(response.getResultInfo().getCode());

```
Did you get **SUCCESS** in the print statement above, if yes then the API execution has happened correctly.

For details of all the request and response parameters , check our [API Documentation guide](https://www.paypay.ne.jp/opa/doc/v1.0/continuous_payments#operation/getUserAuthorizationStatus).

### Unlink User
So you want to unlink the user from the client, use the following method with the parameter below

| Field  | Required  |Type   | Description  |  
|---|---|---|---|
|userAuthorizationId   |  Yes |string <= 64 characters  |The PayPay user reference id returned by the user authorization flow  |

```java
// Calling the method to unlink the user from the client
UserApi userApi = new UserApi(apiClient);
NotDataResponse response = userApi.unlinkUser("userAuthorizationId");
// Printing if the method call was SUCCESS
System.out.println(response.getResultInfo().getCode());
```
Did you get **SUCCESS** in the print statement above, if yes then the API execution has happened correctly.

For details of all the request and response parameters , check our [API Documentation guide](https://www.paypay.ne.jp/opa/doc/v1.0/continuous_payments#operation/unlinkUser).


### Response code list

**Get user authorization status**

|Status	|CodeId	|Code	|Message|
|---|---|---|---|
|401	||	INVALID_USER_AUTHORIZATION_ID	|The specified user authorization ID is invalid.|

**Unlink User**

|Status	|CodeId	|Code	|Message|
|---|---|---|---|
|401	||	INVALID_USER_AUTHORIZATION_ID	|The specified user authorization ID is invalid.|