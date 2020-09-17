package jp.ne.paypay.api;

import jp.ne.paypay.ApiClient;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.ApiResponse;
import jp.ne.paypay.model.MerchantOrderItem;
import jp.ne.paypay.model.MoneyAmount;
import jp.ne.paypay.model.NotDataResponse;
import jp.ne.paypay.model.Payment;
import jp.ne.paypay.model.PaymentDetails;
import jp.ne.paypay.model.PaymentState;
import jp.ne.paypay.model.Refund;
import jp.ne.paypay.model.RefundDetails;
import jp.ne.paypay.model.RefundState;
import jp.ne.paypay.model.ResultInfo;
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
public class PendingPaymentApiTest {

    @Mock
    private final PendingPaymentApi api = Mockito.spy(new PendingPaymentApi());
    @Mock
    private final PaymentApi paymentApi = Mockito.spy(new PaymentApi());
    private ResultInfo resultInfo;
    private ApiClient apiClient;
    private Payment payment;

    @BeforeEach
    public void setUp() {
        apiClient = Mockito.mock(ApiClient.class);
        api.setApiClient(apiClient);
        paymentApi.setApiClient(apiClient);
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
     * Create pending payment
     *
     * Sends a push notification to the user requesting payment.  **Timeout: 30s**
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void createPendingPaymentTest() throws ApiException {

        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.resultInfo(resultInfo);
        payment.status(PaymentState.StatusEnum.COMPLETED).authorizedAt(Instant.now().getNano()).paymentId("paymentId");
        paymentDetails.data(payment);
        Assertions.assertNotNull(payment.toString());
        ApiResponse<PaymentDetails> paymentDetailsApiResponse = new ApiResponse<>(90001, null, paymentDetails);
        Mockito.when(api.createPendingPaymentWithHttpInfo(payment)).thenReturn(paymentDetailsApiResponse);
        PaymentDetails response = api.createPendingPayment(payment);
        Assertions.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
        Assertions.assertEquals(response.getData().getMerchantPaymentId(), "merchantPaymentId");
        Assertions.assertEquals(response.getData().getUserAuthorizationId(), "userAuthorizationId");
        Assertions.assertEquals(response.getData().getStatus(), PaymentState.StatusEnum.COMPLETED);
        Assertions.assertNotNull(response.getData());
        Assertions.assertNotNull(response.getData().getTerminalId());
        Assertions.assertNotNull(response.getData().getOrderReceiptNumber());
        Assertions.assertNotNull(response.getData().getAmount());
        Assertions.assertNotNull(response.getData().getRequestedAt());
        Assertions.assertNotNull(response.getData().getOrderItems());
        Assertions.assertNotNull(response.getData().getAuthorizedAt());

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
        Mockito.when(api.getPendingPaymentDetailsWithHttpInfo(merchantPaymentId)).thenReturn(paymentDetailsApiResponse);
        PaymentDetails response = api.getPaymentDetails(merchantPaymentId);

        Assertions.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
    }

    /**
     * Cancel a payment
     *
     * This api is used to cancel the pending order.  **Timeout: 15s**
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void cancelPaymentTest() throws ApiException {

        String merchantPaymentId = "merchantpaymentid";
        NotDataResponse notDataResponse = new NotDataResponse();
        notDataResponse.setResultInfo(resultInfo);
        ApiResponse<NotDataResponse> notDataResponseApiResponse = new ApiResponse<>(00001, null, notDataResponse);
        Assertions.assertEquals(notDataResponseApiResponse.getStatusCode(), 00001);
        Assertions.assertNull(notDataResponseApiResponse.getHeaders());
        Mockito.when(apiClient.escapeString(merchantPaymentId)).thenReturn(merchantPaymentId);
        Mockito.when(api.cancelPendingOrderWithHttpInfo(merchantPaymentId)).thenReturn(notDataResponseApiResponse);
        NotDataResponse response = api.cancelPendingOrder(merchantPaymentId);
        Assertions.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
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
        Assertions.assertNotNull(refund.toString());
        RefundDetails response = api.refundPayment(refund);
        Assertions.assertNotNull(response.toString());
        Assertions.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
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
        Mockito.when(paymentApi.getRefundDetailsWithHttpInfo(merchantRefundId)).thenReturn(paymentDetailsApiResponse);
        RefundDetails response = api.getRefundDetails(merchantRefundId);

        Assertions.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
    }

}
