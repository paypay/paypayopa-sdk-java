# WalletApi


Method | HTTP request | Description
------------- | ------------- | -------------
[**checkWalletBalance**](WalletApi.md#checkWalletBalance) | **GET** /v2/wallet/check_balance | Check user wallet balance
[**getWalletBalance**](WalletApi.md#getWalletBalance) | **GET** /v6/wallet/balance | Get user wallet balance



<a name="checkWalletBalance"></a>
# **checkWalletBalance**
> WalletBalance checkWalletBalance(userAuthorizationId, amount, currency, productType)

Check user wallet balance

Check if user has enough balance to make a payment  **Timeout: 15s** 

### Example
```java
// Import classes:
import jp.ne.paypay.ApiException;
import jp.ne.paypay.api.WalletApi;



WalletApi apiInstance = new WalletApi(apiClient);

String userAuthorizationId = "USER_AUTHORIZATION_ID";

int amount = 10;

String currency = "JPY";

ProductType productType = ProductType.VIRTUAL_BONUS_INVESTMENT OR ProductType.PAY_LATER_REPAYMENT
                            
 // productType parameter in request is optional. For some merchants that are restricted to use only certain product types, the product type must be properly set.

try {
    WalletBalance result = apiInstance.checkWalletBalance(userAuthorizationId, amount, currency, null);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#checkWalletBalance");
    e.printStackTrace();
    System.out.println(e.getResponseBody());
}
```
```
Please refer to the below document for more information :
https://www.paypay.ne.jp/opa/doc/v1.0/direct_debit#operation/checkWalletBalance
```



<a name="getWalletBalance"></a>
# **getWalletBalance**
> GetWalletBalance getWalletBalance(userAuthorizationId, currency, productType)

Get user wallet balance

Get the user wallet account balance  **Timeout: 15s**

### Example
```java
// Import classes:
import jp.ne.paypay.ApiException;
import jp.ne.paypay.api.WalletApi;



WalletApi apiInstance = new WalletApi(apiClient);

String userAuthorizationId = "USER_AUTHORIZATION_ID";

String currency = "JPY";

ProductType productType = ProductType.VIRTUAL_BONUS_INVESTMENT OR ProductType.PAY_LATER_REPAYMENT

// productType parameter in request is optional. For some merchants that are restricted to use only certain product types, the product type must be properly set.

try {
    GetWalletBalance result = apiInstance.getWalletBalance(userAuthorizationId, currency, null);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#getWalletBalance");
    e.printStackTrace();
    System.out.println(e.getResponseBody());
}
```
```
Please refer to the below document for more information :
https://www.paypay.ne.jp/opa/doc/v1.0/get_balance#tag/Wallet/operation/getBalance
```
