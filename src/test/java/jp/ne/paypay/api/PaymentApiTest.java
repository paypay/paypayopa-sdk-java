package jp.ne.paypay.api;

import jp.ne.paypay.ApiClient;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.ApiResponse;
import jp.ne.paypay.model.AccountLinkQRCode;
import jp.ne.paypay.model.CaptureObject;
import jp.ne.paypay.model.LinkQRCodeResponse;
import jp.ne.paypay.model.MerchantOrderItem;
import jp.ne.paypay.model.MoneyAmount;
import jp.ne.paypay.model.NotDataResponse;
import jp.ne.paypay.model.Payment;
import jp.ne.paypay.model.PaymentDetails;
import jp.ne.paypay.model.QRCode;
import jp.ne.paypay.model.QRCodeDetails;
import jp.ne.paypay.model.Refund;
import jp.ne.paypay.model.RefundDetails;
import jp.ne.paypay.model.ResultInfo;
import jp.ne.paypay.model.AuthorizationScope;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * API tests for PaymentApi
 */
public class PaymentApiTest {

    @Mock
    private final PaymentApi api = Mockito.spy(new PaymentApi());
    private ResultInfo resultInfo;
    private ApiClient apiClient;

    @BeforeEach
    public void setUp(){
         apiClient = Mockito.mock(ApiClient.class);
         api.setApiClient(apiClient);
         resultInfo = new ResultInfo();
         resultInfo.setMessage("SUCCESS");
    }
    /**
     * Cancel a payment
     *
     * This api is used in case, while creating a payment, the client can not determine the status of the payment. For example, client get timeout or the response cannot contain the information to indicate the exact payment status.  By calling this api, if accepted, the OPA will guarantee the money eventually goes back to user&#x27;s account.  &lt;/br&gt;&lt;b style&#x3D;\&quot;color:red\&quot;&gt;Note:&lt;/b&gt; The Cancel API can be used until 00:14:59 AM the day after the Payment has happened. &lt;/br&gt;For 00:15 AM or later, please call the refund API to refund the payment.  **Timeout: 15s** 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void cancelPaymentTest() throws ApiException {

        String merchantPaymentId = "merchantpaymentid";
        NotDataResponse notDataResponse = new NotDataResponse();
        notDataResponse.setResultInfo(resultInfo);
        ApiResponse<NotDataResponse> notDataResponseApiResponse = new ApiResponse<>(00001, null, notDataResponse);
        Mockito.when(apiClient.escapeString(merchantPaymentId)).thenReturn(merchantPaymentId);
        Mockito.when(api.cancelPaymentWithHttpInfo(merchantPaymentId)).thenReturn(notDataResponseApiResponse);
        NotDataResponse response = api.cancelPayment(merchantPaymentId);
        Assertions.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
    }
    
    /**
     * Capture a payment authorization
     *
     * This api is used to capture the payment authorization for a payment  **Timeout: 30s** 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void capturePaymentAuthTest() throws ApiException {
        
        CaptureObject captureObject = new CaptureObject();
        captureObject.setMerchantCaptureId(UUID.randomUUID().toString());
        captureObject.setMerchantPaymentId("merchantPaymentId");
        captureObject.setAmount(new MoneyAmount().amount(10).currency(MoneyAmount.CurrencyEnum.JPY));
        captureObject.setOrderDescription("new Order");
        captureObject.setRequestedAt(Instant.now().getEpochSecond());
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setResultInfo(resultInfo);

        ApiResponse<PaymentDetails> paymentDetailsApiResponse = new ApiResponse<>(00001, null, paymentDetails);
        Mockito.when(api.capturePaymentAuthWithHttpInfo(captureObject)).thenReturn(paymentDetailsApiResponse);
        PaymentDetails response = api.capturePaymentAuth(captureObject);
        Assertions.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
    }
    
    /**
     * Create a payment
     *
     * Create a direct debit payment and start the money transfer.  **Timeout: 30s** 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createPaymentTest() throws ApiException {

        Payment payment = new Payment();
        payment.setAmount(new MoneyAmount().amount(10).currency(MoneyAmount.CurrencyEnum.JPY));
        payment.setMerchantPaymentId("merchantPaymentId");
        payment.setUserAuthorizationId("userAuthorizationId");
        payment.setRequestedAt(Instant.now().getEpochSecond());
        payment.setStoreId(RandomStringUtils.randomAlphabetic(8));
        payment.setTerminalId(RandomStringUtils.randomAlphanumeric(8));
        payment.setOrderReceiptNumber(RandomStringUtils.randomAlphanumeric(8));
        payment.setOrderDescription("Payment for Order ID:"+UUID.randomUUID().toString());
        MerchantOrderItem merchantOrderItem =
                new MerchantOrderItem()
                        .category("Dessert").name("Red Velvet Cake")
                        .productId(RandomStringUtils.randomAlphanumeric(8)).quantity(1)
                        .unitPrice(new MoneyAmount().amount(10).currency(MoneyAmount.CurrencyEnum.JPY));
        List<MerchantOrderItem> merchantOrderItems = new ArrayList<>();
        merchantOrderItems.add(merchantOrderItem);
        payment.setOrderItems(new ArrayList<MerchantOrderItem>(merchantOrderItems));

        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setResultInfo(resultInfo);
        String agreeSimilarTransaction = "True";
        ApiResponse<PaymentDetails> paymentDetailsApiResponse = new ApiResponse<>(00001, null, paymentDetails);
        Mockito.when(api.createPaymentWithHttpInfo(payment, agreeSimilarTransaction)).thenReturn(paymentDetailsApiResponse);
        PaymentDetails response = api.createPayment(payment, agreeSimilarTransaction);
        Assertions.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
    }
    
    /**
     * Create a Code
     *
     * Create a Code to receive payments.  **Timeout: 30s** 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createQRCodeTest() throws ApiException {

        QRCode qrCode = new QRCode();
        qrCode.setAmount(new MoneyAmount().amount(10).currency(MoneyAmount.CurrencyEnum.JPY));
        qrCode.setMerchantPaymentId(UUID.randomUUID().toString());
        qrCode.setCodeType("ORDER_QR");
        qrCode.setStoreId(RandomStringUtils.randomAlphabetic(8));
        qrCode.setStoreInfo("Just Bake");
        qrCode.setTerminalId(RandomStringUtils.randomAlphanumeric(8));
        qrCode.requestedAt(Instant.now().getEpochSecond());
        qrCode.redirectUrl("https://www.justbake.in/payment");
        qrCode.redirectType(QRCode.RedirectTypeEnum.WEB_LINK);//For Deep Link, RedirectTypeEnum.APP_DEEP_LINK
        qrCode.setOrderDescription("Payment for Order ID:"+UUID.randomUUID().toString());
        MerchantOrderItem merchantOrderItem =
                new MerchantOrderItem()
                        .category("Dessert").name("Red Velvet Cake")
                        .productId(RandomStringUtils.randomAlphanumeric(8)).quantity(1)
                        .unitPrice(new MoneyAmount().amount(10).currency(MoneyAmount.CurrencyEnum.JPY));
        List<MerchantOrderItem> merchantOrderItems = new ArrayList<>();
        merchantOrderItems.add(merchantOrderItem);
        qrCode.setOrderItems(merchantOrderItems);

        QRCodeDetails qrCodeDetails = new QRCodeDetails();
        qrCodeDetails.setResultInfo(resultInfo);
        ApiResponse<QRCodeDetails> qrCodeDetailsApiResponse = new ApiResponse<>(00001, null, qrCodeDetails);
        Mockito.when(api.createQRCodeWithHttpInfo(qrCode)).thenReturn(qrCodeDetailsApiResponse);
        QRCodeDetails response = api.createQRCode(qrCode);
        Assertions.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
    }
    
    /**
     * Delete a Code
     *
     * Delete a created Code.  **Timeout: 15s** 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteQRCodeTest() throws ApiException {

        String codeId = "codeId";
        NotDataResponse notDataResponse = new NotDataResponse();
        notDataResponse.setResultInfo(resultInfo);
        ApiResponse<NotDataResponse> notDataResponseApiResponse = new ApiResponse<>(00001, null, notDataResponse);
        Mockito.when(apiClient.escapeString(codeId)).thenReturn(codeId);
        Mockito.when(api.deleteQRCodeWithHttpInfo(codeId)).thenReturn(notDataResponseApiResponse);
        NotDataResponse response = api.deleteQRCode(codeId);
        Assertions.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
    }
    /**
     * Get payment details
     *
     * Get payment details.  **Timeout: 15s** 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getPaymentDetailsTest() throws ApiException {
        
        String merchantPaymentId = "merchantPaymentId";
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setResultInfo(resultInfo);
        ApiResponse<PaymentDetails> paymentDetailsApiResponse = new ApiResponse<>(00001, null, paymentDetails);
        Mockito.when(apiClient.escapeString(merchantPaymentId)).thenReturn(merchantPaymentId);
        Mockito.when(api.getPaymentDetailsWithHttpInfo(merchantPaymentId)).thenReturn(paymentDetailsApiResponse);
        PaymentDetails response = api.getPaymentDetails(merchantPaymentId);

        Assertions.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
    }
    
    /**
     * Get refund details
     *
     * Get refund details.  **Timeout: 15s** 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getRefundDetailsTest() throws ApiException {
        
        String merchantRefundId = "refundId";
        RefundDetails refundDetails = new RefundDetails();
        refundDetails.setResultInfo(resultInfo);
        ApiResponse<RefundDetails> paymentDetailsApiResponse = new ApiResponse<>(00001, null, refundDetails);
        Mockito.when(apiClient.escapeString(merchantRefundId)).thenReturn(merchantRefundId);
        Mockito.when(api.getRefundDetailsWithHttpInfo(merchantRefundId)).thenReturn(paymentDetailsApiResponse);
        RefundDetails response = api.getRefundDetails(merchantRefundId);

        Assertions.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
    }
    
    /**
     * Refund a payment
     *
     * Refund a payment.  **Timeout: 30s** 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void refundPaymentTest() throws ApiException {

        Refund refund = new Refund();
        refund.setAmount(new MoneyAmount().amount(1).currency(MoneyAmount.CurrencyEnum.JPY));
        refund.setMerchantRefundId("refundId");
        refund.setPaymentId("paymentId");
        refund.setReason("Testing");
        refund.setRequestedAt(Instant.now().getEpochSecond());
        RefundDetails refundDetails = new RefundDetails();
        refundDetails.setResultInfo(resultInfo);
        ApiResponse<RefundDetails> paymentDetailsApiResponse = new ApiResponse<>(00001, null, refundDetails);
        Mockito.when(api.refundPaymentWithHttpInfo(refund)).thenReturn(paymentDetailsApiResponse);
        RefundDetails response = api.refundPayment(refund);

        Assertions.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
    }

    /**
     * Create an Account Link QRCode
     * Create an ACCOUNT LINK QR and display it to the user.  **Timeout: 10s**
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createAccountLinkQRCodeTest() throws ApiException {

        AccountLinkQRCode accountLinkQRCode = new AccountLinkQRCode();
        List<AuthorizationScope> scopes = new ArrayList<>();
        scopes.add(AuthorizationScope.DIRECT_DEBIT);
        accountLinkQRCode.setScopes(scopes);
        accountLinkQRCode.setNonce(RandomStringUtils.randomAlphanumeric(8).toLowerCase());
        accountLinkQRCode.setDeviceId("device_id");
        accountLinkQRCode.setRedirectUrl("merchant.domain/test");
        accountLinkQRCode.setPhoneNumber("phone_number");
        accountLinkQRCode.setReferenceId("reference_id");
        accountLinkQRCode.setRedirectType(QRCode.RedirectTypeEnum.WEB_LINK);

        LinkQRCodeResponse linkQRCodeResponse = new LinkQRCodeResponse();
        linkQRCodeResponse.setResultInfo(resultInfo);
        ApiResponse<LinkQRCodeResponse> paymentDetailsApiResponse = new ApiResponse<>(8100001, null, linkQRCodeResponse);
        Mockito.when(api.createAccountLinkQRCodeWithHttpInfo(accountLinkQRCode)).thenReturn(paymentDetailsApiResponse);
        LinkQRCodeResponse response = api.createAccountLinkQRCode(accountLinkQRCode);

        Assertions.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
    }
}
