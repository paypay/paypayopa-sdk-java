package jp.ne.paypay.api;

import com.squareup.okhttp.Call;
import jp.ne.paypay.ApiClient;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.Pair;
import jp.ne.paypay.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiUtil {
    public static Call getCallObject(ApiClient apiClient, String url, String key, String value, String method) throws ApiException {
        String localVarPath = url
                .replaceAll("\\{" + key+ "}", apiClient.escapeString(value));

        List<Pair> localVarQueryParams = new ArrayList<>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<>();

        Map<String, String> localVarHeaderParams = new HashMap<>();

        Map<String, Object> localVarFormParams = new HashMap<>();

        final String[] localVarAccepts = {
                Constants.APPLICATION_JSON
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put(Constants.ACCEPT, localVarAccept);

        final String[] localVarContentTypes = {

        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put(Constants.CONTENT_TYPE, localVarContentType);
        String[] localVarAuthNames = new String[]{Constants.HMAC_AUTH};
        apiClient.setReadTimeout(15);
        return apiClient.buildCall(localVarPath, method, localVarQueryParams, localVarCollectionQueryParams,
                null, localVarHeaderParams, localVarFormParams, localVarAuthNames);
    }

    public static void validateObject(Validator validator, Object body) {
        String message = validator.validate(body);
        if (message != null) {
            throw new IllegalArgumentException(message);
        }
    }
}
