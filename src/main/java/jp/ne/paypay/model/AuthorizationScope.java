package jp.ne.paypay.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Scopes of the user authorization
 */
@JsonAdapter(AuthorizationScope.Adapter.class)
public enum AuthorizationScope {

    DIRECT_DEBIT("direct_debit"),
    CASHBACK("cashback"),
    GET_BALANCE("get_balance"),
    QUICK_PAY("quick_pay"),
    CONTINUOUS_PAYMENTS("continuous_payments"),
    MERCHANT_TOPUP("merchant_topup"),
    PENDING_PAYMENTS("pending_payments"),
    USER_NOTIFICATION("user_notification"),
    USER_TOPUP("user_topup"),
    USER_PROFILE("user_profile"),
    PREAUTH_CAPTURE_NATIVE("preauth_capture_native"),
    PREAUTH_CAPTURE_TRANSACTIONS("preauth_capture_transaction"),
    PUSH_NOTIFICATION("push_notification"),
    NOTIFICATION_CENTER_OB("notification_center_ob"),
    NOTIFICATION_CENTER_AB("notification_center_ab"),
    NOTIFICATION_CENTER_TL("notification_center_tl");

    private String value;

    AuthorizationScope(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static AuthorizationScope fromValue(String text) {
        for (AuthorizationScope b : AuthorizationScope.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }

    public static class Adapter extends TypeAdapter<AuthorizationScope> {
        @Override
        public void write(final JsonWriter jsonWriter, final AuthorizationScope enumeration) throws IOException {
            jsonWriter.value(enumeration.getValue());
        }

        @Override
        public AuthorizationScope read(final JsonReader jsonReader) throws IOException {
            String value = jsonReader.nextString();
            return AuthorizationScope.fromValue(String.valueOf(value));
        }
    }
}
