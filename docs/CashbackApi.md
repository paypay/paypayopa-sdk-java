# CashbackApi

Method | HTTP request | Description
------------- | ------------- | ------------- 
[**giveCashback**](docs/cashbackApi.md#giveCashback) | **POST** /v2/cashback | Transfer money from merchants campaign wallet to user wallet
[**getCashback**](docs/cashbackApi.md#getCashback) | **GET** /v2/cashback/{merchantCashbackId} | Check the cashback details of the cashback given
[**reverseCashback**](docs/cashbackApi.md#reverseCashback) | **POST** /v2/cashback_reversal | Transfer money back from user wallet to merchants campaign wallet.
[**getReverseCashback**](docs/cashbackApi.md#getReverseCashback) | **GET** /v2/cashback_reversal/{merchantCashbackReversalId}/{merchantCashbackId} | Check the cashback reversal details of the cashback reversed

<a name="giveCashback"></a>
# **giveCashback**
> CashbackDetails createCashbackRequest(Cashback cashback)

Give Cashback to User

Transfer money from merchants campaign wallet to user wallet.

### Example
```java
//Import classes:
import jp.ne.paypay.ApiException;
import jp.ne.paypay.api.CashbackApi;
import jp.ne.paypay.model.Cashback;
import jp.ne.paypay.model.CashbackDetails;

CashbackApi apiInstance = new CashbackApi(apiClient);

Cashback cashback = new Cashback();
    cashback.setMerchantCashbackId("merchantCashbackId");
    cashback.setUserAuthorizationId(userAuthorizationId);
    cashback.setExpiryDate("2021-02-28"); // Optional, if set Should be with format YYYY-MM-DD
    cashback.setAmount(new MoneyAmount().amount(amount).currency(MoneyAmount.CurrencyEnum.JPY));

try {
    CashbackDetails result = apiInstance.createCashbackRequest(cashback);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CashbackApi#createCashbackRequest");
    System.out.println(e.getResponseBody());
}
```

```
Please refer to the below document for more information :
https://www.paypay.ne.jp/opa/doc/v1.0/cashback#operation/createCashback
```

<a name="getCashback"></a>
# **getCashback**
> CashbackDetails getCashbackDetails(String merchantCashbackId)

Check Cashback Details

Check the cashback details of the cashback given. 

### Example
```java
// Import classes:
import jp.ne.paypay.ApiException;
import jp.ne.paypay.api.CashbackApi;
import jp.ne.paypay.model.Cashback;
import jp.ne.paypay.model.CashbackDetails;

CashbackApi apiInstance = new CashbackApi(apiClient);

String merchantCashbackId = "MERCHANT_CASHBACK_ID"; 

try {
    CashbackDetails result = apiInstance.getCashbackDetails(merchantCashbackId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CashbackApi#getCashbackDetails");
    System.out.println(e.getResponseBody());
}
```

```
Please refer to the below document for more information :
https://www.paypay.ne.jp/opa/doc/v1.0/cashback#operation/checkCashback
```

<a name="reverseCashback"></a>
# **reverseCashback**
> ReverseCashbackDetails createReverseCashbackRequest(ReverseCashback reverseCashback)

Reverse a given cashback

Transfer money back from user wallet to merchants campaign wallet.

### Example
```java
//Import classes:
import jp.ne.paypay.ApiException;
import jp.ne.paypay.api.CashbackApi;
import jp.ne.paypay.model.ReverseCashback;
import jp.ne.paypay.model.ReverseCashbackDetails;

CashbackApi apiInstance = new CashbackApi(apiClient);

ReverseCashback reverseCashback = new ReverseCashback();
    reverseCashback.setMerchantCashbackReversalId("merchantCashbackReversalId");
    reverseCashback.setMerchantCashbackId("merchantCashbackId");
    reverseCashback.setAmount(new MoneyAmount().amount(amount).currency(MoneyAmount.CurrencyEnum.JPY));

try {
    ReverseCashbackDetails result = apiInstance.createReverseCashbackRequest(reverseCashback);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CashbackApi#createReverseCashbackRequest");
    System.out.println(e.getResponseBody());
}
```

```
Please refer to the below document for more information :
https://www.paypay.ne.jp/opa/doc/v1.0/cashback#operation/createCashbackReversal
```

<a name="getReverseCashback"></a>
# **getReverseCashback**
> ReverseCashbackDetails getReversedCashbackDetails(String merchantCashbackReversalId, String merchantCashbackId)

Check Cashback Reversal Details

Check the cashback reversal details of the cashback reversed

### Example
```java
// Import classes:
import jp.ne.paypay.ApiException;
import jp.ne.paypay.api.CashbackApi;
import jp.ne.paypay.model.ReverseCashback;
import jp.ne.paypay.model.ReverseCashbackDetails;

CashbackApi apiInstance = new CashbackApi(apiClient);

String merchantCashbackId = "MERCHANT_CASHBACK_ID"; 
String merchantCashbackReversalId = "MERCHANT_CASHBACK_REVERSAL_ID"; 

try {
    ReverseCashbackDetails result = apiInstance.getReversedCashbackDetails(merchantCashbackReversalId, merchantCashbackId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CashbackApi#getReversedCashbackDetails");
    System.out.println(e.getResponseBody());
}
```

```
Please refer to the below document for more information :
https://www.paypay.ne.jp/opa/doc/v1.0/cashback#operation/checkCashbackReversal
```