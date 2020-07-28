# WalletApi


Method | HTTP request | Description
------------- | ------------- | -------------
[**checkWalletBalance**](WalletApi.md#checkWalletBalance) | **GET** /v1/wallet/check_balance | Check user wallet balance




<a name="checkWalletBalance"></a>
# **checkWalletBalance**
> WalletBalance checkWalletBalance(userAuthorizationId, amount, currency, productType)

Check user wallet balance

Check if user has enough balance to make a payment  **Timeout: 15s** 

### Example
```java
// Import classes:
//import ApiException;
//import WalletApi;



WalletApi apiInstance = new WalletApi();

String userAuthorizationId = "USER_AUTHORIZATION_ID";

Integer amount = 10;

String currency = "JPY";

ProductType productType = ProductType.VIRTUAL_BONUS_INVESTMENT OR ProductType.PAY_LATER_REPAYMENT
                            
 // productType parameter in request is optional. For some merchants that are restricted to use only certain product types, the product type must be properly set.

try {
    WalletBalance result = apiInstance.checkWalletBalance(userAuthorizationId, amount, currency, null);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling WalletApi#checkWalletBalance");
    e.printStackTrace();
}
```
```
Please refer to the below document for more information :
https://www.paypay.ne.jp/opa/doc/v1.0/direct_debit#operation/checkWalletBalance
```
