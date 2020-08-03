package jp.ne.paypay.api;

import jp.ne.paypay.ApiClient;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.ApiResponse;
import jp.ne.paypay.model.ProductType;
import jp.ne.paypay.model.ResultInfo;
import jp.ne.paypay.model.WalletBalance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 * API tests for WalletApi
 */

public class WalletApiTest {

    @Mock
    private final WalletApi api = Mockito.spy(new WalletApi());
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
     * Check user wallet balance
     *
     * Check if user has enough balance to make a payment  **Timeout: 15s**
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void checkWalletBalanceTest() throws ApiException {

        String userAuthorizationId = "user-authorization-id";

        Integer amount = 10;

        String currency = "JPY";

        ProductType productType = null;
        WalletBalance walletBalance = new WalletBalance();

        walletBalance.setResultInfo(resultInfo);

        ApiResponse<WalletBalance> walletBalanceApiResponse = new ApiResponse<>(00001, null, walletBalance);
        Mockito.when(api.checkWalletBalanceWithHttpInfo(userAuthorizationId, amount, currency, productType)).thenReturn(walletBalanceApiResponse);
        WalletBalance response = api.checkWalletBalance(userAuthorizationId, amount, currency, productType);
        Assertions.assertEquals(response.getResultInfo().getMessage(), "SUCCESS");
    }

}
