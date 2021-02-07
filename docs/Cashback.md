
# Cashback

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**merchantCashbackId** | **String** | The unique cashback transaction id provided by merchant |  Required
**userAuthorizationId** | **String** | The PayPay user reference id returned by the user authorization flow |  Required
**amount** | [**MoneyAmount**](MoneyAmount.md) |  |  Required
**requestedAt** | **Long** | Epoch timestamp in seconds |  Required
**orderDescription** | **String** | Description of the order | 
**walletType** | **Enum(WalletType)** | Enum: "PREPAID" "CASHBACK" | 
**expiryDate** | **String** | The date on which the Cashback Expires at midnight (format YYYY-MM-DD) | 
**metadata** | **Object** | Extra information the merchant want to add |  




