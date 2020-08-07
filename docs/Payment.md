
# Payment

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**merchantPaymentId** | **String** | The unique payment transaction id provided by merchant |  Required
**userAuthorizationId** | **String** | The PayPay user reference id returned by the user authorization flow |  Required
**amount** | [**MoneyAmount**](MoneyAmount.md) |  |  Required
**requestedAt** | **Long** | Epoch timestamp in seconds |  Required
**storeId** | **String** | Id to identify store under merchant |  
**terminalId** | **String** | Id to identify terminal device under store |  
**orderReceiptNumber** | **String** | Receipt number provided by merchant |  
**orderDescription** | **String** | Description of the order | 
**orderItems** | [**List&lt;MerchantOrderItem&gt;**](MerchantOrderItem.md) |  |  
**metadata** | **Object** | Extra information the merchant want to add |  
**expiresAt** | **Integer** | Epoch timestamp in seconds, expiry duration must be less then the expiry granted to the merchant | 
**amountDescription** | **String** | Description of the order | 




