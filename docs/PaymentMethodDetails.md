
# PaymentMethodDetails

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**paymentMethodType** | **String** | The payment method type WALLET &lt;/br&gt; Credit cards are not currently supported. |  [optional]
**id** | **String** | Encrypted Payment method id to be passed to create payment |  [optional]
**issuerName** | **String** | Details of the issuer of the instrument |  [optional]
**issuerName2** | **String** | More details of the issuer of the instrument |  [optional]
**accountNo** | **String** | Masked account no/ credit card number |  [optional]
**logoUrl** | **String** | Logo URL of the issuer |  [optional]
**balance** | **String** | Balance of the payment instrument, only provided for PayPay Wallet |  [optional]
**verified** | **Boolean** | If the card has been verified using 3D secure |  [optional]
**disabled** | **Boolean** | If the card has been disabled by the user |  [optional]




