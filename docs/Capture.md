
# Capture

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**merchantCaptureId** | **String** |  |  [optional]
**amount** | [**MoneyAmount**](MoneyAmount.md) |  |  [optional]
**orderDescription** | **String** | Description for Capture |  [optional]
**requestedAt** | **Long** |  |  [optional]
**expiresAt** | **Integer** | Epoch timestamp in seconds, expiry duration must be less then the expiry granted to the merchant |  [optional]
**status** | [**StatusEnum**](#StatusEnum) | Status is always USER_REQUESTED. |  [optional]
**acceptedAt** | **Integer** |  |  [optional]
**amountescription** | **String** | Description for Capture |  [optional]



<a name="StatusEnum"></a>
## Enum: StatusEnum
Name | Value
---- | -----
USER_REQUESTED | &quot;USER_REQUESTED&quot;
COMPLETED | &quot;COMPLETED&quot;



