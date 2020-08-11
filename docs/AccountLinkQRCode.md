
# AccountUserLinkQRCode

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**scopes** | **[**List&lt;AuthorizationScope&gt;**](AuthorizationScope.md)** | Scopes of the user authorization |  Required
**nonce** | **String** | Random generated string |  Required
**redirectType** | **String** | Parameter to decide whether to redirect to merchant app or merchant web application |  Default: "WEB_LINK"
**redirectUrl** | **String** | The callback endpoint provided by client. For WEB_LINK it must be HTTPS, and its domain should be in the allowed authorization callback domains |  Required
**referenceId** | **String** | The id used to identify the user in merchant system. It will be stored in the PayPay db for reconsilliation purpose |  Required
**phoneNumber** | **String** | The user mobile phone number | 
**deviceId** | **String** | The user mobile phone device id. If it is provided, we can use it to verify the user and skip the SMS verification, so as to provide more fluent UX | 
**userAgent** | **String** | The User agent of the web browser. When redirectType is WEB_LINK this parameter is provided, on mobile devices PayPay tries to open the browser that the merchant website is using. | 




