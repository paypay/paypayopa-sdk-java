package jp.ne.paypay.api;

import jp.ne.paypay.ApiClient;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.ApiResponse;
import jp.ne.paypay.model.Cashback;
import jp.ne.paypay.model.CashbackDetails;
import jp.ne.paypay.model.MoneyAmount;
import jp.ne.paypay.model.ResultInfo;
import jp.ne.paypay.model.ReverseCashback;
import jp.ne.paypay.model.ReverseCashbackDetails;
import jp.ne.paypay.model.WalletType;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.Instant;

/**
 * API tests for PaymentApi
 */
public class CashbackApiTest {

    @Mock
    private final CashbackApi api = Mockito.spy(new CashbackApi());
    private ResultInfo resultInfo;
    private ApiClient apiClient;
    private Cashback cashback;
    private ReverseCashback reverseCashback;

    @BeforeEach
    public void setUp() {
        apiClient = Mockito.mock(ApiClient.class);
        api.setApiClient(apiClient);
        resultInfo = new ResultInfo();
        resultInfo.setMessage("SUCCESS");
        cashback = new Cashback();
        cashback.setAmount(new MoneyAmount().amount(10).currency(MoneyAmount.CurrencyEnum.JPY));
        cashback.setMerchantCashbackId("merchantCashbackId")
                .setUserAuthorizationId("userAuthorizationId")
                .setRequestedAt(Instant.now().getEpochSecond())
                .setWalletType(WalletType.CASHBACK)
                .setOrderDescription("Cashback for merchant cashback ID:"+cashback.getMerchantCashbackId());

        reverseCashback = new ReverseCashback();
        reverseCashback.setAmount(new MoneyAmount().amount(10).currency(MoneyAmount.CurrencyEnum.JPY));
        reverseCashback.setMerchantCashbackId("merchantCashbackId")
                .setMerchantCashbackReversalId("merchantCashbackReversalId")
                .setRequestedAt(Instant.now().getEpochSecond())
                .setReason("Cashback for merchant cashback ID:"+reverseCashback.getMerchantCashbackId());
    }

    /**
     * Create cashback request
     *
     * Transfer money from merchants campaign wallet to user wallet.
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void createCashbackRequestTest() throws ApiException {

        CashbackDetails cashbackDetails = new CashbackDetails();
        cashbackDetails.resultInfo(resultInfo);
        cashback.setStatus("REQUEST_ACCEPTED").setCashbackId("cashbackId");
        cashbackDetails.data(cashback);
        Assert.assertNotNull(cashback.toString());
        ApiResponse<CashbackDetails> cashbackDetailsApiResponse = new ApiResponse<>(8100001, null, cashbackDetails);
        Mockito.when(api.createCashbackRequestWithHttpInfo(cashback)).thenReturn(cashbackDetailsApiResponse);
        CashbackDetails response = api.createCashbackRequest(cashback);
        Assert.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
    }
    /**
     * Create reverse cashback request
     *
     * Transfer money back from user wallet to merchants campaign wallet
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void reverseCashbackTest() throws ApiException {

        ReverseCashbackDetails reverseCashbackDetails = new ReverseCashbackDetails();
        reverseCashbackDetails.resultInfo(resultInfo);
        reverseCashback.setStatus("REQUEST_ACCEPTED");
        reverseCashbackDetails.data(reverseCashback);
        Assert.assertNotNull(reverseCashback.toString());
        ApiResponse<ReverseCashbackDetails> cashbackDetailsApiResponse = new ApiResponse<>(8100001, null, reverseCashbackDetails);
        Mockito.when(api.createReverseCashbackRequestWithHttpInfo(reverseCashback)).thenReturn(cashbackDetailsApiResponse);
        ReverseCashbackDetails response = api.createReverseCashbackRequest(reverseCashback);
        Assert.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");

    }

    /**
     * Check Cashback Details
     *
     * Check the cashback details of the cashback given
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getCashbackDetailsTest() throws ApiException {

        String merchantCashbackId = "merchantCashbackId";
        CashbackDetails cashbackDetails = new CashbackDetails();
        cashbackDetails.setResultInfo(resultInfo);
        cashback.setStatus("SUCCESS").setCashbackId("cashbackId");
        cashbackDetails.data(cashback);
        ApiResponse<CashbackDetails> cashbackDetailsApiResponse = new ApiResponse<>(00001, null, cashbackDetails);
        Mockito.when(apiClient.escapeString(merchantCashbackId)).thenReturn(merchantCashbackId);
        Mockito.when(api.getCashbackDetailsWithHttpInfo(merchantCashbackId)).thenReturn(cashbackDetailsApiResponse);
        CashbackDetails response = api.getCashbackDetails(merchantCashbackId);

        Assert.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
    }

    /**
     * Check Reversed Cashback Details
     *
     * Check the reversed cashback details of the cashback given
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getReversedCashbackDetailsTest() throws ApiException {

        String merchantCashbackId = "merchantCashbackId";
        String merchantCashbackReversalId = "merchantCashbackReversalId";
        ReverseCashbackDetails reverseCashbackDetails = new ReverseCashbackDetails();
        reverseCashbackDetails.setResultInfo(resultInfo);
        reverseCashback.setStatus("SUCCESS");
        reverseCashbackDetails.data(reverseCashback);
        ApiResponse<ReverseCashbackDetails> reverseCashbackDetailsApiResponse = new ApiResponse<>(00001, null, reverseCashbackDetails);
        Mockito.when(apiClient.escapeString(merchantCashbackId)).thenReturn(merchantCashbackId);
        Mockito.when(apiClient.escapeString(merchantCashbackReversalId)).thenReturn(merchantCashbackReversalId);
        Mockito.when(api.getReversedCashbackDetailsWithHttpInfo(merchantCashbackReversalId, merchantCashbackId)).thenReturn(reverseCashbackDetailsApiResponse);
        ReverseCashbackDetails response = api.getReversedCashbackDetails(merchantCashbackReversalId, merchantCashbackId);

        Assert.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
    }
}
