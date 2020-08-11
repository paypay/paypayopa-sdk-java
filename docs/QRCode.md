
# QRCode

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**merchantPaymentId** | **String** | The unique payment transaction id provided by merchant |  Required
**amount** | [**MoneyAmount**](MoneyAmount.md) |  |  Required
**orderDescription** | **String** | Description of the order |  
**orderItems** | [**List&lt;MerchantOrderItem&gt;**](MerchantOrderItem.md) |  |  
**metadata** | **Object** | Extra information the merchant want to add | 
**codeType** | **String** | Please pass the fixed string “ORDER_QR” | Required
**storeInfo** | **String** | Store info for the merchant |  
**storeId** | **String** | Id to identify store under merchant | 
**terminalId** | **String** | Id to identify terminal device under store | 
**requestedAt** | **Long** | Epoch timestamp in seconds | 
**isAuthorization** | **Boolean** | By default it will be false, please set true if the amount will be captured later | 
**authorizationExpiry** | **Integer** | Epoch timestamp in seconds, expiry duration must be less then the expiry granted to the merchant | 
**redirectUrl** | **String** | The url of the page/app to open after the payment is complete |  
**redirectType** | [**RedirectTypeEnum**](#RedirectTypeEnum) | The type of redirect url |  
**userAgent** | **String** | The User agent of the web browser. When this parameter is provided, on mobile devices PayPay tries to open the browser that the merchant website is using. This can be found by using the javascript function navigator.userAgent | 



<a name="RedirectTypeEnum"></a>
## Enum: RedirectTypeEnum
Name | Value
---- | -----
WEB_LINK | &quot;WEB_LINK&quot;
APP_DEEP_LINK | &quot;APP_DEEP_LINK&quot;



