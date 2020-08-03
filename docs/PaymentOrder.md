
# PaymentOrder

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**merchantPaymentId** | **String** |  |  [optional]
**userAuthorizationId** | **String** |  |  [optional]
**amount** | [**MoneyAmount**](MoneyAmount.md) |  |  [optional]
**requestedAt** | **Long** |  |  [optional]
**storeId** | **String** | Id to identify store under merchant |  [optional]
**terminalId** | **String** | Id to identify terminal device under store |  [optional]
**orderReceiptNumber** | **String** | Receipt number provided by merchant |  [optional]
**orderDescription** | **String** | Description of the order |  [optional]
**orderItems** | [**List&lt;MerchantOrderItem&gt;**](MerchantOrderItem.md) |  |  [optional]
**metadata** | **Object** | Extra information the merchant want to add |  [optional]
**expiresAt** | **Integer** | Epoch timestamp in seconds, expiry duration must be less then the expiry granted to the merchant |  [optional]
**amountDescription** | **String** | Description of the order |  [optional]




