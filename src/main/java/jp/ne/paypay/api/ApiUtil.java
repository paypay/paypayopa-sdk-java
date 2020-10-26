package jp.ne.paypay.api;

import com.squareup.okhttp.Call;
import jp.ne.paypay.ApiClient;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.Pair;
import jp.ne.paypay.Validator;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiUtil {
    public static Call getCallObject(ApiClient apiClient, String url, Pair pair, String method) throws ApiException {
        if (StringUtils.isEmpty(pair.getValue())) {
            throw new IllegalArgumentException("Missing the required parameter"+pair.getName());
        }
        String localVarPath = url
                .replaceAll("\\{" + pair.getName()+ "}", apiClient.escapeString(pair.getValue()));

        List<Pair> localVarQueryParams = new ArrayList<>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<>();

        Map<String, String> localVarHeaderParams = new HashMap<>();

        Map<String, Object> localVarFormParams = new HashMap<>();

        final String[] localVarAccepts = {
                ApiConstants.APPLICATION_JSON
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put(ApiConstants.ACCEPT, localVarAccept);

        final String[] localVarContentTypes = {

        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put(ApiConstants.CONTENT_TYPE, localVarContentType);
        String[] localVarAuthNames = new String[]{ApiConstants.HMAC_AUTH};
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

    public static Call postCallObject(ApiClient apiClient, String url, Object body, String agreeSimilarTransaction) throws ApiException{
        List<Pair> localVarQueryParams = new ArrayList<>();
        if(agreeSimilarTransaction != null){
            localVarQueryParams.addAll(apiClient.parameterToPair("agreeSimilarTransaction", agreeSimilarTransaction));
        }
        final String localVarAccept = apiClient.selectHeaderAccept(new String[]{ApiConstants.APPLICATION_JSON});
        Map<String, String> localVarHeaderParams = new HashMap<>();
        if (localVarAccept != null) localVarHeaderParams.put(ApiConstants.ACCEPT, localVarAccept);

        final String localVarContentType = apiClient.selectHeaderContentType(new String[]{});
        localVarHeaderParams.put(ApiConstants.CONTENT_TYPE, localVarContentType);
        String[] localVarAuthNames = new String[]{ApiConstants.HMAC_AUTH};
        apiClient.setReadTimeout(30);
        Map<String, Object> localVarFormParams = new HashMap<>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<>();
        return apiClient.buildCall(url, "POST", localVarQueryParams, localVarCollectionQueryParams,
                body, localVarHeaderParams, localVarFormParams, localVarAuthNames);
    }

    public static String buildResolveUrl(String apiName, String code, String codeId){
        String resolveUrl = ApiConstants.RESOLVE_BASE_URL+"?api-name=%s&code=%s&code-id=%s";
        return String.format(resolveUrl, apiName, code, codeId);
    }
}
