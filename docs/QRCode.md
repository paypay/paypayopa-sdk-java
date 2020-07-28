
# QRCode

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**merchantPaymentId** | **String** |  |  [optional]
**amount** | [**MoneyAmount**](MoneyAmount.md) |  |  [optional]
**orderDescription** | **String** | Description of the order |  [optional]
**orderItems** | [**List&lt;MerchantOrderItem&gt;**](MerchantOrderItem.md) |  |  [optional]
**metadata** | **Object** | Extra information the merchant want to add |  [optional]
**codeType** | **String** | Please pass the fixed string “ORDER_QR” |  [optional]
**storeInfo** | **String** | Store info for the merchant |  [optional]
**storeId** | **String** | Id to identify store under merchant |  [optional]
**terminalId** | **String** | Id to identify terminal device under store |  [optional]
**requestedAt** | **Long** |  |  [optional]
**isAuthorization** | **Boolean** | By default it will be false, please set true if the amount will be captured later |  [optional]
**authorizationExpiry** | **Integer** | Epoch timestamp in seconds, expiry duration must be less then the expiry granted to the merchant |  [optional]
**redirectUrl** | **String** | The url of the page/app to open after the payment is complete |  [optional]
**redirectType** | [**RedirectTypeEnum**](#RedirectTypeEnum) | The type of redirect url |  [optional]
**userAgent** | **String** | The User agent of the web browser. When this parameter is provided, on mobile devices PayPay tries to open the browser that the merchant website is using. This can be found by using the javascript function navigator.userAgent |  [optional]



<a name="RedirectTypeEnum"></a>
## Enum: RedirectTypeEnum
Name | Value
---- | -----
WEB_LINK | &quot;WEB_LINK&quot;
APP_DEEP_LINK | &quot;APP_DEEP_LINK&quot;



