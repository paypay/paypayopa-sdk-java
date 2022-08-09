package jp.ne.paypay.api;

import com.google.gson.Gson;
import jp.ne.paypay.ApiClient;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.ApiResponse;
import jp.ne.paypay.model.AccountLinkQRCode;
import jp.ne.paypay.model.AuthorizationScope;
import jp.ne.paypay.model.Capture;
import jp.ne.paypay.model.CaptureObject;
import jp.ne.paypay.model.LinkQRCodeResponse;
import jp.ne.paypay.model.LinkQRCodeResponseData;
import jp.ne.paypay.model.MerchantOrderItem;
import jp.ne.paypay.model.MerchantOrderItemResponse;
import jp.ne.paypay.model.MoneyAmount;
import jp.ne.paypay.model.NotDataResponse;
import jp.ne.paypay.model.Payment;
import jp.ne.paypay.model.PaymentDetails;
import jp.ne.paypay.model.PaymentMethod;
import jp.ne.paypay.model.PaymentMethodsResponse;
import jp.ne.paypay.model.PaymentState;
import jp.ne.paypay.model.PaymentStateCaptures;
import jp.ne.paypay.model.PaymentStateRevert;
import jp.ne.paypay.model.QRCode;
import jp.ne.paypay.model.QRCodeDetails;
import jp.ne.paypay.model.QRCodeResponse;
import jp.ne.paypay.model.Refund;
import jp.ne.paypay.model.RefundDetails;
import jp.ne.paypay.model.RefundState;
import jp.ne.paypay.model.ResultInfo;
import jp.ne.paypay.model.RevertAuthResponse;
import jp.ne.paypay.model.RevertAuthResponseData;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.InputStreamReader;
import java.io.Reader;
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
    private Payment payment;

    @BeforeEach
    public void setUp() {
        apiClient = Mockito.mock(ApiClient.class);
        api.setApiClient(apiClient);
        resultInfo = new ResultInfo();
        resultInfo.setMessage("SUCCESS");
        payment = new Payment();
        payment.setAmount(new MoneyAmount().amount(10).currency(MoneyAmount.CurrencyEnum.JPY));
        payment.merchantPaymentId("merchantPaymentId")
                .userAuthorizationId("userAuthorizationId")
                .requestedAt(Instant.now().getEpochSecond())
                .storeId(RandomStringUtils.randomAlphabetic(8))
                .terminalId(RandomStringUtils.randomAlphanumeric(8))
                .orderReceiptNumber(RandomStringUtils.randomAlphanumeric(8))
                .orderDescription("Payment for Order ID:" + UUID.randomUUID().toString());
        MerchantOrderItem merchantOrderItem =
                new MerchantOrderItem()
                        .category("Dessert").name("Red Velvet Cake")
                        .productId(RandomStringUtils.randomAlphanumeric(8)).quantity(1)
                        .unitPrice(new MoneyAmount().amount(10).currency(MoneyAmount.CurrencyEnum.JPY));
        List<MerchantOrderItem> merchantOrderItems = new ArrayList<>();
        merchantOrderItems.add(merchantOrderItem);
        payment.orderItems(merchantOrderItems);
    }

    /**
     * Cancel a payment
     *
     * This api is used in case, while creating a payment, the client can not determine the status of the payment. For example, client get timeout or the response cannot contain the information to indicate the exact payment status.  By calling this api, if accepted, the OPA will guarantee the money eventually goes back to user&#x27;s account.  &lt;/br&gt;&lt;b style&#x3D;\&quot;color:red\&quot;&gt;Note:&lt;/b&gt; The Cancel API can be used until 00:14:59 AM the day after the Payment has happened. &lt;/br&gt;For 00:15 AM or later, please call the refund API to refund the payment.  **Timeout: 15s** 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void cancelPaymentTest() throws ApiException {

        String merchantPaymentId = "merchantpaymentid";
        NotDataResponse notDataResponse = new NotDataResponse();
        notDataResponse.setResultInfo(resultInfo);
        ApiResponse<NotDataResponse> notDataResponseApiResponse = new ApiResponse<>(00001, null, notDataResponse);
        Assert.assertEquals(notDataResponseApiResponse.getStatusCode(), 00001);
        Assert.assertNull(notDataResponseApiResponse.getHeaders());
        Mockito.when(apiClient.escapeString(merchantPaymentId)).thenReturn(merchantPaymentId);
        Mockito.when(api.cancelPaymentWithHttpInfo(merchantPaymentId)).thenReturn(notDataResponseApiResponse);
        NotDataResponse response = api.cancelPayment(merchantPaymentId);
        Assert.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
    }

    /**
     * Capture a payment authorization
     *
     * This api is used to capture the payment authorization for a payment  **Timeout: 30s** 
     *
     * @throws ApiException if the Api call fails
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
        payment.setStatus(PaymentState.StatusEnum.COMPLETED);
        PaymentStateCaptures paymentStateCaptures = new PaymentStateCaptures();
        Capture capture = new Capture();
        capture.acceptedAt(Instant.now().getNano());
        capture.amount(payment.getAmount());
        capture.setExpiresAt(Instant.now().getNano());
        capture.setAmountDescription("amount captured");
        capture.setMerchantCaptureId(captureObject.getMerchantCaptureId());
        capture.setOrderDescription(captureObject.getOrderDescription());
        capture.setRequestedAt(captureObject.getRequestedAt());
        capture.setAcceptedAt(Instant.now().getNano());
        capture.setStatus(Capture.StatusEnum.COMPLETED);
        Assert.assertNotNull(capture.toString());
        paymentStateCaptures.addDataItem(capture);
        payment.setCaptures(paymentStateCaptures);
        paymentDetails.setData(payment);
        ApiResponse<PaymentDetails> paymentDetailsApiResponse = new ApiResponse<>(00001, null, paymentDetails);
        Mockito.when(api.capturePaymentAuthWithHttpInfo(captureObject)).thenReturn(paymentDetailsApiResponse);
        PaymentDetails response = api.capturePaymentAuth(captureObject);
        Assert.assertNotNull(response.toString());
        Assert.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
        Assert.assertNotNull(response.getData());
        Assert.assertEquals(response.getData().getMerchantPaymentId(), "merchantPaymentId");
        Assert.assertEquals(response.getData().getUserAuthorizationId(), "userAuthorizationId");
        Assert.assertEquals(response.getData().getStatus(), Payment.StatusEnum.COMPLETED);
        Assert.assertNotNull(response.getData().getTerminalId());
        Assert.assertNotNull(response.getData().getOrderReceiptNumber());
        Assert.assertNotNull(response.getData().getCaptures());
        Assert.assertNotNull(response.getData().getCaptures().getData().get(0));
        Assert.assertNotNull(response.getData().getCaptures().getData().get(0).getAcceptedAt());
        Assert.assertNotNull(response.getData().getCaptures().getData().get(0).getAmount());
        Assert.assertNotNull(response.getData().getCaptures().getData().get(0).getAmountDescription());
        Assert.assertNotNull(response.getData().getCaptures().getData().get(0).getExpiresAt());
        Assert.assertNotNull(response.getData().getCaptures().getData().get(0).getMerchantCaptureId());
        Assert.assertNotNull(response.getData().getCaptures().getData().get(0).getOrderDescription());
        Assert.assertEquals(response.getData().getCaptures().getData().get(0).getStatus(), Capture.StatusEnum.COMPLETED);
    }

    /**
     * Create a payment
     *
     * Create a direct debit payment and start the money transfer.  **Timeout: 30s** 
     *
     * @throws ApiException if the Api call fails
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
        payment.setOrderDescription("Payment for Order ID:" + UUID.randomUUID().toString());
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
        Assert.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
    }

    /**
     * Create a payment Authorization
     *
     * Create a direct debit payment and start the money transfer.  **Timeout: 30s**
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void createPaymentAuthorizationTest() throws ApiException {

        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setResultInfo(resultInfo);
        payment.setStatus(PaymentState.StatusEnum.AUTHORIZED);
        payment.setAuthorizedAt(Instant.now().getNano());
        payment.setPaymentId("paymentId");
        paymentDetails.setData(payment);
        String agreeSimilarTransaction = "True";
        ApiResponse<PaymentDetails> paymentDetailsApiResponse = new ApiResponse<>(00001, null, paymentDetails);
        Mockito.when(api.createPaymentAuthorizationWithHttpInfo(payment, agreeSimilarTransaction)).thenReturn(paymentDetailsApiResponse);
        PaymentDetails response = api.createPaymentAuthorization(payment, agreeSimilarTransaction);
        Assert.assertNotNull(response.toString());
        Assert.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
        Assert.assertEquals(response.getData().getMerchantPaymentId(), "merchantPaymentId");
        Assert.assertEquals(response.getData().getUserAuthorizationId(), "userAuthorizationId");
        Assert.assertEquals(response.getData().getPaymentId(), "paymentId");
        Assert.assertEquals(response.getData().getStatus(), PaymentState.StatusEnum.AUTHORIZED);
        Assert.assertNotNull(response.getData());
        Assert.assertNotNull(response.getData().getTerminalId());
        Assert.assertNotNull(response.getData().getOrderReceiptNumber());
        Assert.assertNotNull(response.getData().getAmount());
        Assert.assertNotNull(response.getData().getRequestedAt());
        Assert.assertNotNull(response.getData().getOrderItems());
        Assert.assertNotNull(response.getData().getAuthorizedAt());

    }


    /**
     * Create a Code
     *
     * Create a Code to receive payments.  **Timeout: 30s** 
     *
     * @throws ApiException if the Api call fails
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
        qrCode.setOrderDescription("Payment for Order ID:" + UUID.randomUUID().toString());
        MerchantOrderItem merchantOrderItem =
                new MerchantOrderItem()
                        .category("Dessert").name("Red Velvet Cake")
                        .productId(RandomStringUtils.randomAlphanumeric(8)).quantity(1)
                        .unitPrice(new MoneyAmount().amount(10).currency(MoneyAmount.CurrencyEnum.JPY));
        List<MerchantOrderItem> merchantOrderItems = new ArrayList<>();
        merchantOrderItems.add(merchantOrderItem);
        qrCode.setOrderItems(merchantOrderItems);
        String storeInfo = "Just Bake";
        String codeType = "ORDER_QR";
        String redirectUrl = "https://www.justbake.in/payment";
        QRCodeDetails qrCodeDetails = new QRCodeDetails();
        qrCodeDetails.setResultInfo(resultInfo);
        QRCodeResponse qrCodeResponse = new QRCodeResponse();
        qrCodeResponse.amount(new MoneyAmount().amount(10).currency(MoneyAmount.CurrencyEnum.JPY));
        qrCodeResponse.setMerchantPaymentId(UUID.randomUUID().toString());
        qrCodeResponse.setCodeType(codeType);
        qrCodeResponse.setStoreId(RandomStringUtils.randomAlphabetic(8));
        qrCodeResponse.setStoreInfo(storeInfo);
        qrCodeResponse.setTerminalId(RandomStringUtils.randomAlphanumeric(8));
        qrCodeResponse.requestedAt(Instant.now().getEpochSecond());
        qrCodeResponse.redirectUrl(redirectUrl);
        qrCodeResponse.redirectType(QRCodeResponse.RedirectTypeEnum.WEB_LINK);//For Deep Link, RedirectTypeEnum.APP_DEEP_LINK
        qrCodeResponse.setOrderDescription("Payment for Order ID:" + UUID.randomUUID().toString());
        MerchantOrderItemResponse merchantOrderItemResponse =
                new MerchantOrderItemResponse()
                        .category("Dessert").name("Red Velvet Cake")
                        .productId(RandomStringUtils.randomAlphanumeric(8)).quantity(1)
                        .unitPrice(new MoneyAmount().amount(10).currency(MoneyAmount.CurrencyEnum.JPY));
        List<MerchantOrderItemResponse> merchantOrderItemsList = new ArrayList<>();
        merchantOrderItemsList.add(merchantOrderItemResponse);
        qrCodeResponse.setOrderItems(merchantOrderItemsList);
        qrCodeDetails.setData(qrCodeResponse);
        ApiResponse<QRCodeDetails> qrCodeDetailsApiResponse = new ApiResponse<>(00001, null, qrCodeDetails);
        Mockito.when(api.createQRCodeWithHttpInfo(qrCode)).thenReturn(qrCodeDetailsApiResponse);
        QRCodeDetails response = api.createQRCode(qrCode);
        Assert.assertNotNull(response.toString());
        Assert.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
        Assert.assertEquals(response.getData().getStoreInfo(), storeInfo);
        Assert.assertEquals(response.getData().getCodeType(), codeType);
        Assert.assertEquals(response.getData().getRedirectUrl(), redirectUrl);
        Assert.assertEquals(response.getData().getRedirectType(), QRCodeResponse.RedirectTypeEnum.WEB_LINK);
        Assert.assertNotNull(response.getData());
        Assert.assertNotNull(response.getData().getMerchantPaymentId());
        Assert.assertNotNull(response.getData().getOrderItems());
        Assert.assertNotNull(response.getData().getRequestedAt());
        Assert.assertNotNull(response.getData().getTerminalId());
        Assert.assertNotNull(response.getData().getOrderDescription());

    }

    /**
     * Delete a Code
     *
     * Delete a created Code.  **Timeout: 15s** 
     *
     * @throws ApiException if the Api call fails
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
        Assert.assertNotNull(response.toString());
        Assert.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
    }

    /**
     * Get payment details
     *
     * Get payment details.  **Timeout: 15s** 
     *
     * @throws ApiException if the Api call fails
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

        Assert.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
    }

    @Test
    @DisplayName("getPaymentMethods should contain paymentMethods")
    public void getPaymentMethodsTest() throws ApiException {
        // Build mock response
        String userAuthorizationId = "user-authorization-id";
        Gson gson = new Gson();
        String path = "/api_fixtures/v4_getPaymentMethods.json";
        Reader reader = new InputStreamReader(getClass().getResourceAsStream(path));
        PaymentMethodsResponse paymentMethodsResponse = gson.fromJson(reader, PaymentMethodsResponse.class);
        ApiResponse<PaymentMethodsResponse> paymentMethodsApiResponse = new ApiResponse<>(00001, null, paymentMethodsResponse);
        Mockito.when(api.getPaymentMethodsWithHttpInfo(userAuthorizationId, null)).thenReturn(paymentMethodsApiResponse);

        // Call entrypoint method
        PaymentMethodsResponse response = api.getPaymentMethods(userAuthorizationId, null);
        PaymentMethod firstElementOfPaymentMethods = response.getData()
            .getPaymentMethods()
            .get(0);

        Assertions.assertAll(
            () -> Assertions.assertEquals(response.getResultInfo().getCode(), "SUCCESS"),
            () -> Assertions.assertNotNull(firstElementOfPaymentMethods.getPaymentMethodType(), "paymentMethodType should exist."),
            () -> Assertions.assertNotNull(firstElementOfPaymentMethods.getPaymentMethodId(), "id should exist."),
            () -> Assertions.assertNotNull(firstElementOfPaymentMethods.getIssuerName(), "issuerName should exist."),
            () -> Assertions.assertNotNull(firstElementOfPaymentMethods.getDisabled()),
            () -> Assertions.assertNotNull(firstElementOfPaymentMethods.getLimited()),
            () -> Assertions.assertNotNull(firstElementOfPaymentMethods.getPaymentMethodStatus(), "paymentMethodStatus should exist.")
        );
    }

    @Test
    @DisplayName("getPaymentMethods requires userAuthorizationId parameter")
    public void getPaymentMethodsTestErrorCase() throws ApiException {
        String userAuthorizationId = null;

        Assertions.assertThrows(ApiException.class, () -> {
            api.getPaymentMethods(userAuthorizationId, null);
        });
    }

    /**
     * Get payment details
     *
     * Get payment details.  **Timeout: 15s**
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getCodePaymentDetailsTest() throws ApiException {

        String merchantPaymentId = "merchantPaymentId";
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setResultInfo(resultInfo);
        ApiResponse<PaymentDetails> paymentDetailsApiResponse = new ApiResponse<>(00001, null, paymentDetails);
        Mockito.when(apiClient.escapeString(merchantPaymentId)).thenReturn(merchantPaymentId);
        Mockito.when(api.getCodesPaymentDetailsWithHttpInfo(merchantPaymentId)).thenReturn(paymentDetailsApiResponse);
        PaymentDetails response = api.getCodesPaymentDetails(merchantPaymentId);

        Assert.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
    }

    /**
     * Get refund details
     *
     * Get refund details.  **Timeout: 15s** 
     *
     * @throws ApiException if the Api call fails
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

        Assert.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
    }

    /**
     * Refund a payment
     *
     * Refund a payment.  **Timeout: 30s** 
     *
     * @throws ApiException if the Api call fails
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
        refund.setStatus(RefundState.StatusEnum.CREATED);
        refundDetails.setData(refund);
        ApiResponse<RefundDetails> paymentDetailsApiResponse = new ApiResponse<>(00001, null, refundDetails);
        Mockito.when(api.refundPaymentWithHttpInfo(refund)).thenReturn(paymentDetailsApiResponse);
        Assert.assertNotNull(refund.toString());
        RefundDetails response = api.refundPayment(refund);
        Assert.assertNotNull(response.toString());
        Assert.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
    }

    /**
     * Revert a payment authorization
     *
     * Revert a payment authorization.  **Timeout: 30s**
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void revertAuthTest() throws ApiException {

        PaymentStateRevert paymentStateRevert = new PaymentStateRevert();
        paymentStateRevert.setPaymentId(UUID.randomUUID().toString());
        paymentStateRevert.setMerchantRevertId(UUID.randomUUID().toString());
        paymentStateRevert.setRequestedAt(Instant.now().getEpochSecond());
        RevertAuthResponse revertAuthResponse = new RevertAuthResponse();
        revertAuthResponse.setResultInfo(resultInfo);
        RevertAuthResponseData revertAuthResponseData = new RevertAuthResponseData();
        revertAuthResponseData.setPaymentId(paymentStateRevert.getPaymentId());
        revertAuthResponseData.setAcceptedAt(Instant.now().getNano());
        revertAuthResponseData.setRequestedAt(Instant.now().getEpochSecond());
        revertAuthResponseData.setReason("Order Cancelled");
        revertAuthResponseData.setStatus(RevertAuthResponseData.StatusEnum.CANCELED);
        revertAuthResponse.setData(revertAuthResponseData);
        ApiResponse<RevertAuthResponse> revertAuthResponseApiResponse = new ApiResponse<>(00001, null, revertAuthResponse);
        Mockito.when(api.revertAuthWithHttpInfo(paymentStateRevert)).thenReturn(revertAuthResponseApiResponse);
        Assert.assertNotNull(paymentStateRevert.toString());
        RevertAuthResponse response = api.revertAuth(paymentStateRevert);
        Assert.assertNotNull(response.toString());
        Assert.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
        Assert.assertNotNull(response.getData());
        Assert.assertNotNull(response.getData().getAcceptedAt());
        Assert.assertNotNull(response.getData().getRequestedAt());
        Assert.assertEquals(response.getData().getReason(), "Order Cancelled");
        Assert.assertEquals(response.getData().getStatus(), RevertAuthResponseData.StatusEnum.CANCELED);
    }


    /**
     * Create an Account Link QRCode
     * Create an ACCOUNT LINK QR and display it to the user.  **Timeout: 10s**
     *
     * @throws ApiException if the Api call fails
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
        LinkQRCodeResponseData linkQRCodeResponseData = new LinkQRCodeResponseData();
        linkQRCodeResponseData.setLinkQRCodeURL("urlLink");
        linkQRCodeResponse.setData(linkQRCodeResponseData);
        ApiResponse<LinkQRCodeResponse> paymentDetailsApiResponse = new ApiResponse<>(8100001, null, linkQRCodeResponse);
        Mockito.when(api.createAccountLinkQRCodeWithHttpInfo(accountLinkQRCode)).thenReturn(paymentDetailsApiResponse);
        Assert.assertNotNull(accountLinkQRCode.toString());
        Assert.assertNotNull(accountLinkQRCode.getScopes());
        Assert.assertNotNull(accountLinkQRCode.getNonce());
        Assert.assertNotNull(accountLinkQRCode.getDeviceId());
        Assert.assertNotNull(accountLinkQRCode.getRedirectUrl());
        Assert.assertNotNull(accountLinkQRCode.getPhoneNumber());
        Assert.assertNotNull(accountLinkQRCode.getReferenceId());

        LinkQRCodeResponse response = api.createAccountLinkQRCode(accountLinkQRCode);
        Assert.assertNotNull(response.toString());
        Assert.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
        Assert.assertNotNull(response.getData());
        Assert.assertNotNull(response.getData().getLinkQRCodeURL());
    }

    /**
     * Create an Account Link QRCode Failed response
     * Create an ACCOUNT LINK QR and display it to the user.  **Timeout: 10s**
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void createAccountLinkQRCodeFailedTest() throws ApiException {

        AccountLinkQRCode accountLinkQRCode = new AccountLinkQRCode();
        List<AuthorizationScope> scopes = new ArrayList<>();
        scopes.add(AuthorizationScope.DIRECT_DEBIT);
        accountLinkQRCode.setScopes(scopes)
                .setNonce(RandomStringUtils.randomAlphanumeric(8).toLowerCase())
                .setDeviceId("device_id")
                .setRedirectUrl("merchant.domain/test")
                .setPhoneNumber("phone_number")
                .setReferenceId("reference_id")
                .setRedirectType(QRCode.RedirectTypeEnum.WEB_LINK);
        Assert.assertNotNull(accountLinkQRCode.toString());
        LinkQRCodeResponse linkQRCodeResponse = new LinkQRCodeResponse();
        resultInfo.setMessage("FAILED");
        linkQRCodeResponse.setResultInfo(resultInfo);
        ApiResponse<LinkQRCodeResponse> paymentDetailsApiResponse = new ApiResponse<>(8100001, null, linkQRCodeResponse);
        Mockito.when(api.createAccountLinkQRCodeWithHttpInfo(accountLinkQRCode)).thenReturn(paymentDetailsApiResponse);
        LinkQRCodeResponse response = api.createAccountLinkQRCode(accountLinkQRCode);

        Assert.assertEquals(response.getResultInfo().getMessage(), "FAILED");
    }

    /**
     * Create continuous payment
     *
     * Create a continuous payment and start the money transfer.  **Timeout: 30s**
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void createContinuousPaymentTest() throws ApiException {

        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.resultInfo(resultInfo);
        payment.status(PaymentState.StatusEnum.COMPLETED).authorizedAt(Instant.now().getNano()).paymentId("paymentId");
        paymentDetails.data(payment);
        Assert.assertNotNull(payment.toString());
        ApiResponse<PaymentDetails> paymentDetailsApiResponse = new ApiResponse<>(90001, null, paymentDetails);
        Mockito.when(api.createContinuousPaymentWithHttpInfo(payment)).thenReturn(paymentDetailsApiResponse);
        PaymentDetails response = api.createContinuousPayment(payment);
        Assert.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
        Assert.assertEquals(response.getData().getMerchantPaymentId(), "merchantPaymentId");
        Assert.assertEquals(response.getData().getUserAuthorizationId(), "userAuthorizationId");
        Assert.assertEquals(response.getData().getPaymentId(), "paymentId");
        Assert.assertEquals(response.getData().getStatus(), PaymentState.StatusEnum.COMPLETED);
        Assert.assertNotNull(response.getData());
        Assert.assertNotNull(response.getData().getTerminalId());
        Assert.assertNotNull(response.getData().getOrderReceiptNumber());
        Assert.assertNotNull(response.getData().getAmount());
        Assert.assertNotNull(response.getData().getRequestedAt());
        Assert.assertNotNull(response.getData().getOrderItems());
        Assert.assertNotNull(response.getData().getAuthorizedAt());

    }

    @Test
    @DisplayName("Constraint Violations Test")
    public void constraintViolationsTest() throws ApiException {
        payment.setMerchantPaymentId(null);
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setResultInfo(resultInfo);
        payment.setStatus(PaymentState.StatusEnum.AUTHORIZED);
        payment.setAuthorizedAt(Instant.now().getNano());
        payment.setPaymentId("paymentId");
        paymentDetails.setData(payment);
        String agreeSimilarTransaction = "True";
        ApiResponse<PaymentDetails> paymentDetailsApiResponse = new ApiResponse<>(00001, null, paymentDetails);
        Mockito.when(api.createPaymentAuthorizationWithHttpInfo(payment, agreeSimilarTransaction)).thenReturn(paymentDetailsApiResponse);
        Assert.assertThrows(IllegalArgumentException.class, () -> api.createPaymentAuthorization(payment, agreeSimilarTransaction));
    }
}
