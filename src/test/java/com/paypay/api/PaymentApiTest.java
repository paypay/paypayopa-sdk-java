package com.paypay.api;

import com.paypay.ApiException;
import com.paypay.model.NotDataResponse;
import com.paypay.model.PaymentDetails;
import com.paypay.model.QRCodeDetails;
import com.paypay.model.RefundDetails;

import org.junit.Ignore;
import org.junit.Test;

/**
 * API tests for PaymentApi
 */
@Ignore
public class PaymentApiTest {

    private final PaymentApi api = new PaymentApi();

    
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
        
        String merchantPaymentId = null;
        
        NotDataResponse response = api.cancelPayment(merchantPaymentId);

        // TODO: test validations
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
        
        Object body = null;
        
        PaymentDetails response = api.capturePaymentAuth(body);

        // TODO: test validations
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
        
        Object body = null;
        
        String agreeSimilarTransaction = null;
        
        PaymentDetails response = api.createPayment(body, agreeSimilarTransaction);

        // TODO: test validations
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
        
        Object body = null;
        
        QRCodeDetails response = api.createQRCode(body);

        // TODO: test validations
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
        
        String codeId = null;
        
        NotDataResponse response = api.deleteQRCode(codeId);

        // TODO: test validations
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
        
        String merchantPaymentId = null;
        
        PaymentDetails response = api.getPaymentDetails(merchantPaymentId);

        // TODO: test validations
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
        
        String merchantRefundId = null;
        
        RefundDetails response = api.getRefundDetails(merchantRefundId);

        // TODO: test validations
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
        
        Object body = null;
        
        RefundDetails response = api.refundPayment(body);

        // TODO: test validations
    }
    
}
