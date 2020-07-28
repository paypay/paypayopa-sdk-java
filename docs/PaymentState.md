
# PaymentState

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**paymentId** | **String** |  |  [optional]
**status** | [**StatusEnum**](#StatusEnum) |  |  [optional]
**acceptedAt** | **Integer** |  |  [optional]
**refunds** | [**PaymentStateRefunds**](PaymentStateRefunds.md) |  |  [optional]
**authorizedAt** | **Integer** |  |  [optional]
**canceledAt** | **Integer** |  |  [optional]
**captures** | [**PaymentStateCaptures**](PaymentStateCaptures.md) |  |  [optional]
**revert** | [**PaymentStateRevert**](PaymentStateRevert.md) |  |  [optional]



<a name="StatusEnum"></a>
## Enum: StatusEnum
Name | Value
---- | -----
CREATED | &quot;CREATED&quot;
AUTHORIZED | &quot;AUTHORIZED&quot;
REAUTHORIZING | &quot;REAUTHORIZING&quot;
COMPLETED | &quot;COMPLETED&quot;
REFUNDED | &quot;REFUNDED&quot;
FAILED | &quot;FAILED&quot;
CANCELED | &quot;CANCELED&quot;
EXPIRED | &quot;EXPIRED&quot;



