package jp.ne.paypay.api;

public class ApiNameConstants {
    //API NAMES FOR RESOLVE URL
    public static final String CANCEL_PAYMENT = "v2_cancelPayment";
    public static final String CAPTURE_PAYMENT = "v2_captureAuthorizedOrder";
    public static final String CREATE_PAYMENT = "v2_createPayment";
    public static final String CREATE_QRCODE = "v2_createDynamicQRCode";
    public static final String DELETE_QRCODE = "v2_deleteDynamicQRCode";
    public static final String GET_PAYMENT = "v2_getPaymentDetail";
    public static final String GET_QR_PAYMENT = "v2_getQRPaymentDetails";
    public static final String GET_REFUND = "v2_getRefundDetails";
    public static final String REFUND_PAYMENT = "v2_createRefundPayment";
    public static final String REVERT_AUTHORIZE = "v2_revertAuthorizedOrder";
    public static final String PREAUTHORIZE_PAYMENT = "v2_createOrderAndAuthorize";
    public static final String CREATE_CONTINUOUS_PAYMENT = "v1_createSubscriptionPayment";
    public static final String CREATE_REQUEST_ORDER = "v1_createRequestOrder";
    public static final String GET_REQUEST_ORDER = "v1_getRequestOrder";
    public static final String CANCEL_REQUEST_ORDER = "v1_cancelRequestOrder";
    public static final String GET_SECURE_USER_PROFILE = "v2_getSecureUserProfile";
    public static final String CHECK_BALANCE = "v2_checkWalletBalance";
    public static final String GET_USER_AUTH_STATUS = "v2_userAuthStatus";
    public static final String UNLINK_USER = "v2_unlinkUser";
    public static final String CREATE_QR_SESSION = "v1_qrSession";
    public static final String CREATE_CASHBACK_REQUEST = "v2_createCashBackRequest";
    public static final String GET_CASHBACK_DETAILS = "v2_getCashbackDetails";
    public static final String CREATE_REVERSE_CASHBACK_REQUEST = "v2_createReverseCashBackRequest";
    public static final String GET_REVERSED_CASHBACK_DETAILS = "v2_getReversedCashBackDetails";

    public static final String GET_USER_CASHBACK_SETTING_STATUS = "v1_userCashbackSettingStatus";
}
