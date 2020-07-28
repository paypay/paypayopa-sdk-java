
# UserAuthorizationData

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**userAuthorizationId** | **String** | PayPay user reference id |  [optional]
**referenceIds** | [**List**](List.md) | The id used to identify the user in merchant system |  [optional]
**status** | [**StatusEnum**](#StatusEnum) | User authorization status |  [optional]
**scopes** | [**List&lt;ScopesEnum&gt;**](#List&lt;ScopesEnum&gt;) | Scopes of the user authorization |  [optional]
**expireAt** | [**BigDecimal**](BigDecimal.md) | Expiration time in the number of seconds since January 1, 1970, 00:00:00 GTM. |  [optional]
**issuedAt** | [**BigDecimal**](BigDecimal.md) | Issuing time in the number of seconds since January 1, 1970, 00:00:00 GTM. |  [optional]



<a name="StatusEnum"></a>
## Enum: StatusEnum
Name | Value
---- | -----
ACTIVE | &quot;ACTIVE&quot;
INACTIVE | &quot;INACTIVE&quot;


<a name="List<ScopesEnum>"></a>
## Enum: List&lt;ScopesEnum&gt;
Name | Value
---- | -----
DIRECT_DEBIT | &quot;direct_debit&quot;
SHOW_BALANCE | &quot;show_balance&quot;
TOPUP | &quot;topup&quot;



