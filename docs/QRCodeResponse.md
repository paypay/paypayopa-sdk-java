
# QRCodeResponse

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**codeId** | **String** | The Id of the Code |  [optional]
**url** | **String** | The url to open webpage (will open app if app installed) |  [optional]
**deeplink** | **String** | App Deeplink to invoke (Not needed in this flow) |  [optional]
**expiryDate** | **Integer** |  |  [optional]
**merchantPaymentId** | **String** |  |  [optional]
**amount** | [**MoneyAmount**](MoneyAmount.md) |  |  [optional]
**orderDescription** | **String** | Description of the order |  [optional]
**orderItems** | [**List&lt;MerchantOrderItemResponse&gt;**](MerchantOrderItemResponse.md) |  |  [optional]
**metadata** | **Object** | Extra information the merchant want to add |  [optional]
**codeType** | **String** | Please pass the fixed string “ORDER_QR” |  [optional]
**storeInfo** | **String** | Store info for the merchant |  [optional]
**storeId** | **String** | Id to identify store under merchant |  [optional]
**terminalId** | **String** | Id to identify terminal device under store |  [optional]
**requestedAt** | **Long** |  |  [optional]
**redirectUrl** | **String** | The url of redirect after complete the payment |  [optional]
**redirectType** | [**RedirectTypeEnum**](#RedirectTypeEnum) | The type of redirect after complete the payment |  [optional]
**isAuthorization** | **Boolean** | If the payment is an authorization. |  [optional]
**authorizationExpiry** | **Integer** | In case the payment is just an authorization, this defines the expiry of the authorization |  [optional]
**amountDescription** | **String** | Description of the order |  [optional]
**userAgent** | **String** | The User agent of the web browser. |  [optional]



<a name="RedirectTypeEnum"></a>
## Enum: RedirectTypeEnum
Name | Value
---- | -----
WEB_LINK | &quot;WEB_LINK&quot;
APP_DEEP_LINK | &quot;APP_DEEP_LINK&quot;



