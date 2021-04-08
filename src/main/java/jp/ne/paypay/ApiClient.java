package jp.ne.paypay;

import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.http.HttpMethod;
import jp.ne.paypay.api.ApiConstants;
import jp.ne.paypay.api.ApiUtil;
import jp.ne.paypay.auth.Authentication;
import jp.ne.paypay.auth.HmacAuth;
import jp.ne.paypay.model.NotDataResponse;
import jp.ne.paypay.model.ResponseParameters;
import okio.BufferedSink;
import okio.Okio;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiClient {

    private String basePath = ApiConstants.DEFAULT_BASE_PATH;
    private String basePathProd = ApiConstants.PROD_BASE_PATH;
    private String basePathSandbox = ApiConstants.SANDBOX_BASE_PATH;
    private String basePathPerf = ApiConstants.PERF_BASE_PATH;
    private Map<String, String> defaultHeaderMap = new HashMap<>();
    private Map<String, Authentication> authentications;
    private OkHttpClient httpClient;
    private JSON json;
    private String assumeMerchant;
    private boolean productionMode;
    private boolean perfMode;

    /*
     * Constructor for ApiClient
     */
    public ApiClient() {
        httpClient = new OkHttpClient();
        json = new JSON();
        // Set default User-Agent.
        setUserAgent(ApiConstants.USER_AGENT);
        authentications = new HashMap<>();
        authentications.put(ApiConstants.HMAC_AUTH, new HmacAuth());
        authentications = Collections.unmodifiableMap(authentications);
    }

    public boolean isProductionMode() {
        return productionMode;
    }
    public boolean isPerfMode() {
        return perfMode;
    }

    public String getBasePathProd() {
        return basePathProd;
    }

    public ApiClient setBasePathProd(String basePathProd) {
        this.basePathProd = basePathProd;
        return this;
    }

    public String getBasePathSandbox() {
        return basePathSandbox;
    }

    public ApiClient setBasePathSandbox(String basePathSandbox) {
        this.basePathSandbox = basePathSandbox;
        return this;
    }

    public Map<String, String> getDefaultHeaderMap() {
        return defaultHeaderMap;
    }

    public ApiClient setDefaultHeaderMap(Map<String, String> defaultHeaderMap) {
        this.defaultHeaderMap = defaultHeaderMap;
        return this;
    }

    public ApiClient setAuthentications(Map<String, Authentication> authentications) {
        this.authentications = authentications;
        return this;
    }

    public JSON getJson() {
        return json;
    }

    public ApiClient setJson(JSON json) {
        this.json = json;
        return this;
    }

    public ApiClient setProductionMode(boolean productionMode) {
        this.productionMode = productionMode;
        if(productionMode){
            this.basePath = basePathProd;
        }else{
            this.basePath = basePathSandbox;
        }

        return this;
    }
    public ApiClient setPerfMode(boolean perfMode) {
        this.perfMode = perfMode;
        if(perfMode){
            this.basePath = basePathPerf;
        }
        return this;
    }

    /**
     * Get base path
     *
     * @return Base path
     */
    public String getBasePath() {
        return basePath;
    }

    /**
     * Set base path
     *
     * @param basePath Base path of the URL (e.g https://stg-api.sandbox.paypay.ne.jp)
     * @return An instance of OkHttpClient
     */
    public ApiClient setBasePath(String basePath) {
        this.basePath = basePath;
        return this;
    }

    public String getAssumeMerchant() {
        return assumeMerchant;
    }

    public ApiClient setAssumeMerchant(String assumeMerchant) {
        if(StringUtils.isNotBlank(assumeMerchant)) {
            this.assumeMerchant = assumeMerchant;
            addDefaultHeader("X-ASSUME-MERCHANT", this.assumeMerchant);
        }
        return this;
    }

    /**
     * Get HTTP client
     *
     * @return An instance of OkHttpClient
     */
    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    /**
     * Set HTTP client
     *
     * @param httpClient An instance of OkHttpClient
     * @return ApiClient
     */
    public ApiClient setHttpClient(OkHttpClient httpClient) {
        this.httpClient = httpClient;
        return this;
    }


    /**
     * Get authentications (key: authentication name, value: authentication).
     *
     * @return Map of authentication objects
     */
    public Map<String, Authentication> getAuthentications() {
        return authentications;
    }

    /**
     * Get authentication for the given name.
     *
     * @param authName The authentication name
     * @return The authentication, null if not found
     */
    public Authentication getAuthentication(String authName) {
        return authentications.get(authName);
    }

    /**
     * Helper method to set API key for the first HTTP HMAC authentication.
     *
     * @param apiKey apiKey
     * @throws ApiException If HMAC authentication not configured
     */
    public void setApiKey(String apiKey) throws ApiException {
        for (Authentication auth : authentications.values()) {
            if (auth instanceof HmacAuth) {
                ((HmacAuth) auth).setApiKey(apiKey);
                return;
            }
        }
        throw new ApiException("HMAC authentication not configured: API key");
    }

    /**
     * Helper method to set request parameters for HTTP Hmac authentication.
     *
     * @param requestUrl requestUrl
     * @param method method
     * @param requestBody requestBody
     * @param contentType contentType
     * @throws RuntimeException If HMAC authentication not configured
     */
    private void setRequestParameters(String requestUrl, String method, Object requestBody, String contentType) {
        String body = requestBody != null ?  json.serialize(requestBody):null;
        for (Authentication auth : authentications.values()) {
            if (auth instanceof HmacAuth) {
                ((HmacAuth) auth).setRequestUrl(requestUrl).setHttpMethod(method).setRequestBody(body).setContentType(contentType);
                return;
            }
        }
        throw new RuntimeException("HMAC authentication not configured: API key");
    }

    /**
     * Helper method to set API secret key for the first HTTP Hmac authentication.
     *
     * @param apiSecret apiSecret
     * @throws ApiException If HMAC authentication not configured
     */
    public void setApiSecretKey(String apiSecret) throws ApiException {
        for (Authentication auth : authentications.values()) {
            if (auth instanceof HmacAuth) {
                ((HmacAuth) auth).setApiSecretKey(apiSecret);
                return;
            }
        }
        throw new ApiException("HMAC authentication not configured: API Secret key");
    }

    /**
     * Set the User-Agent header's value (by adding to the default header map).
     *
     * @param userAgent HTTP request's user agent
     * @return ApiClient
     */
    public ApiClient setUserAgent(String userAgent) {
        addDefaultHeader("User-Agent", userAgent);
        return this;
    }

    /**
     * Add a default header.
     *
     * @param key The header's key
     * @param value The header's value
     * @return ApiClient
     */
    public ApiClient addDefaultHeader(String key, String value) {
        defaultHeaderMap.put(key, value);
        return this;
    }

    /**
     * Get connection timeout (in milliseconds).
     *
     * @return Timeout in milliseconds
     */
    public int getConnectTimeout() {
        return httpClient.getConnectTimeout();
    }

    /**
     * Sets the connect timeout (in milliseconds).
     * A value of 0 means no timeout, otherwise values must be between 1 and
     *
     * @param connectionTimeout connection timeout in milliseconds
     * @return ApiClient
     */
    public ApiClient setConnectTimeout(int connectionTimeout) {
        httpClient.setConnectTimeout(connectionTimeout, TimeUnit.MILLISECONDS);
        return this;
    }

    /**
     * Sets the Read timeout (in seconds).
     * A value of 0 means no timeout, otherwise values must be between 1 and
     *
     * @param readTimeoutInSeconds connection timeout in seconds
     * @return ApiClient
     */
    public ApiClient setReadTimeout(int readTimeoutInSeconds) {
        httpClient.setReadTimeout(readTimeoutInSeconds, TimeUnit.SECONDS);
        return this;
    }

    /**
     * Format the given parameter object into string.
     *
     * @param param Parameter
     * @return String representation of the parameter
     */
    public String parameterToString(Object param) {
        if (param == null) {
            return "";
        } else if (param instanceof Date || param instanceof OffsetDateTime || param instanceof LocalDate) {
            //Serialize to json string and remove the " enclosing characters
            String jsonStr = json.serialize(param);
            return jsonStr.substring(1, jsonStr.length() - 1);
        } else if (param instanceof Collection) {
            StringBuilder b = new StringBuilder();
            for (Object o : (Collection)param) {
                if (b.length() > 0) {
                    b.append(",");
                }
                b.append(String.valueOf(o));
            }
            return b.toString();
        } else {
            return String.valueOf(param);
        }
    }

    /**
     * Formats the specified query parameter to a list containing a single {@code Pair} object.
     *
     * Note that {@code value} must not be a collection.
     *
     * @param name The name of the parameter.
     * @param value The value of the parameter.
     * @return A list containing a single {@code Pair} object.
     */
    public List<Pair> parameterToPair(String name, Object value) {
        List<Pair> params = new ArrayList<Pair>();

        // preconditions
        if (name == null || name.isEmpty() || value == null || value instanceof Collection) return params;

        params.add(new Pair(name, parameterToString(value)));
        return params;
    }

    /**
     * Sanitize filename by removing path.
     * e.g. ../../sun.gif becomes sun.gif
     *
     * @param filename The filename to be sanitized
     * @return The sanitized filename
     */
    public String sanitizeFilename(String filename) {
        return filename.replaceAll(".*[/\\\\]", "");
    }

    /**
     * Check if the given MIME is a JSON MIME.
     * JSON MIME examples:
     *   application/json
     *   application/json; charset=UTF8
     *   APPLICATION/JSON
     *   application/vnd.company+json
     * "* / *" is also default to JSON
     * @param mime MIME (Multipurpose Internet Mail Extensions)
     * @return True if the given MIME is JSON, false otherwise.
     */
    public boolean isJsonMime(String mime) {
      String jsonMime = "(?i)^(application/json|[^;/ \t]+/[^;/ \t]+[+]json)[ \t]*(;.*)?$";
      return mime != null && (mime.matches(jsonMime) || mime.equals("*/*"));
    }

    /**
     * Select the Accept header's value from the given accepts array:
     *   if JSON exists in the given array, use it;
     *   otherwise use all of them (joining into a string)
     *
     * @param accepts The accepts array to select from
     * @return The Accept header to use. If the given array is empty,
     *   null will be returned (not to set the Accept header explicitly).
     */
    public String selectHeaderAccept(String[] accepts) {
        if (accepts.length == 0) {
            return null;
        }
        for (String accept : accepts) {
            if (isJsonMime(accept)) {
                return accept;
            }
        }
        return String.join(",", accepts);
    }

    /**
     * Select the Content-Type header's value from the given array:
     *   if JSON exists in the given array, use it;
     *   otherwise use the first one of the array.
     *
     * @param contentTypes The Content-Type array to select from
     * @return The Content-Type header to use. If the given array is empty,
     *   or matches "any", JSON will be used.
     */
    public String selectHeaderContentType(String[] contentTypes) {
        if (contentTypes.length == 0 || contentTypes[0].equals("*/*")) {
             return "application/json;charset=UTF-8";
        }
        for (String contentType : contentTypes) {
            if (isJsonMime(contentType)) {
                return contentType;
            }
        }
        return contentTypes[0];
    }

    /**
     * Escape the given string to be used as URL query value.
     *
     * @param str String to be escaped
     * @return Escaped string
     */
    public String escapeString(String str) {
        try {
            return URLEncoder.encode(str, "utf8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    /**
     * Deserialize response body to Java object, according to the return type and
     * the Content-Type response header.
     *
     * @param <T> Type
     * @param response HTTP response
     * @param returnType The type of the Java object
     * @return The deserialized Java object
     * @throws ApiException If fail to deserialize response body, i.e. cannot read response body
     *   or the Content-Type of the response is not supported.
     */
    @SuppressWarnings("unchecked")
    public <T> T deserialize(Response response, Type returnType) throws ApiException {
        if (response == null || returnType == null) {
            return null;
        }

        if ("byte[]".equals(returnType.toString())) {
            // Handle binary response (byte array).
            try {
                return (T) response.body().bytes();
            } catch (IOException e) {
                throw new ApiException(e);
            }
        } else if (returnType.equals(File.class)) {
            // Handle file downloading.
            return (T) downloadFileFromResponse(response);
        }

        String respBody;
        try {
            if (response.body() != null)
                respBody = response.body().string();
            else
                respBody = null;
        } catch (IOException e) {
            throw new ApiException(e);
        }

        if (respBody == null || "".equals(respBody)) {
            return null;
        }

        String contentType = response.headers().get("Content-Type");
        if (contentType == null) {
            // ensuring a default content type
            contentType = "application/json";
        }
        if (isJsonMime(contentType)) {
            return json.deserialize(respBody, returnType);
        } else if (returnType.equals(String.class)) {
            // Expecting string, return the raw response body.
            return (T) respBody;
        } else {
            throw new ApiException(
                    "Content type \"" + contentType + "\" is not supported for type: " + returnType,
                    response.code(),
                    response.headers().toMultimap(),
                    respBody);
        }
    }

    /**
     * Serialize the given Java object into request body according to the object's
     * class and the request Content-Type.
     *
     * @param obj The Java object
     * @param contentType The request Content-Type
     * @return The serialized request body
     * @throws ApiException If fail to serialize the given object
     */
    public RequestBody serialize(Object obj, String contentType) throws ApiException {
        if (obj instanceof byte[]) {
            // Binary (byte array) body parameter support.
            return RequestBody.create(MediaType.parse(contentType), (byte[]) obj);
        } else if (obj instanceof File) {
            // File body parameter support.
            return RequestBody.create(MediaType.parse(contentType), (File) obj);
        } else if (isJsonMime(contentType)) {
            String content;
            if (obj != null) {
                content = json.serialize(obj);
            } else {
                content = null;
            }
            return RequestBody.create(MediaType.parse(contentType), content);
        } else {
            throw new ApiException("Content type \"" + contentType + "\" is not supported");
        }
    }

    /**
     * Download file from the given response.
     *
     * @param response An instance of the Response object
     * @throws ApiException If fail to read file content from response and write to disk
     * @return Downloaded file
     */
    public File downloadFileFromResponse(Response response) throws ApiException {
        BufferedSink sink =  null;
        try {
            File file = prepareDownloadFile(response);
            sink = Okio.buffer(Okio.sink(file));
            sink.writeAll(response.body().source());
            sink.close();
            return file;
        } catch (IOException e) {
            throw new ApiException(e);
        } finally {
            if (sink != null) {
                try {
                    sink.close();
                } catch (IOException e) {
                    throw new ApiException(e);
                }
            }
        }
    }

    /**
     * Prepare file for download
     *
     * @param response An instance of the Response object
     * @throws IOException If fail to prepare file for download
     * @return Prepared file for the download
     */
    public File prepareDownloadFile(Response response) throws IOException {
        String filename = null;
        String contentDisposition = response.header("Content-Disposition");
        if (contentDisposition != null && !"".equals(contentDisposition)) {
            // Get filename from the Content-Disposition header.
            Pattern pattern = Pattern.compile("filename=['\"]?([^'\"\\s]+)['\"]?");
            Matcher matcher = pattern.matcher(contentDisposition);
            if (matcher.find()) {
                filename = sanitizeFilename(matcher.group(1));
            }
        }

        String prefix = null;
        String suffix = null;
        if (filename == null) {
            prefix = "download-";
            suffix = "";
        } else {
            int pos = filename.lastIndexOf(".");
            if (pos == -1) {
                prefix = filename + "-";
            } else {
                prefix = filename.substring(0, pos) + "-";
                suffix = filename.substring(pos);
            }
            // File.createTempFile requires the prefix to be at least three characters long
            if (prefix.length() < 3)
                prefix = "download-";
        }
        return File.createTempFile(prefix, suffix);
    }

    /**
     * Execute HTTP call and deserialize the HTTP response body into the given return type.
     *
     * @param returnType The return type used to deserialize HTTP response body
     * @param <T> The return type corresponding to (same with) returnType
     * @param call Call
     * @param apiName The API Name for resolve url
     * @return ApiResponse object containing response status, headers and
     *   data, which is a Java object deserialized from response body and would be null
     *   when returnType is null.
     * @throws ApiException If fail to execute the call
     */
    public <T> ApiResponse<T> execute(Call call, Type returnType, String apiName) throws ApiException {
        try {
            Response response = call.execute();
            ResponseParameters responseParameters = new ResponseParameters()
                    .setResponse(response).setApiName(apiName).setReturnType(returnType);
            T data = handleResponse(responseParameters);
            return new ApiResponse<T>(response.code(), response.headers().toMultimap(), data);
        } catch (IOException e) {
            throw new ApiException(e);
        }
    }

    /**
     * Handle the given response, return the deserialized object when the response is successful.
     *
     * @param <T> Type
     * @param params ResponseParameters
     * @throws ApiException If the response has a unsuccessful status code or
     *   fail to deserialize the response body
     * @return Type
     */
    public <T> T handleResponse(ResponseParameters params) throws ApiException {
        Response response = params.getResponse();
        if (response.isSuccessful()) {
            if (params.getReturnType() == null || response.code() == 204) {
                // returning null if the returnType is not defined,
                // or the status code is 204 (No Content)
                if (response.body() != null) {
                    try {
                        response.body().close();
                    } catch (IOException e) {
                        throw new ApiException(response.message(), e, response.code(), response.headers().toMultimap());
                    }
                }
                return null;
            } else {
                return deserialize(response, params.getReturnType());
            }
        } else {
            String respBody = null;
            if (response.body() != null) {
                try {
                    respBody = response.body().string();
                } catch (IOException e) {
                    throw new ApiException(response.message(), e, response.code(), response.headers().toMultimap());
                }
            }
            throw new ApiException(response.message(), response.code(), response.headers().toMultimap(), respBody).setResolveUrl(getResolveUrl(params.getApiName(), respBody));
        }
    }

    private String getResolveUrl(String apiName, String respBody) {
        NotDataResponse responseBody = json.deserialize(respBody, new TypeToken<NotDataResponse>() {
        }.getType());
        String resolveUrl = null;
        if(StringUtils.isNotEmpty(apiName) && responseBody.getResultInfo() != null){
            resolveUrl = ApiUtil.buildResolveUrl(apiName, responseBody.getResultInfo().getCode(), responseBody.getResultInfo().getCodeId());
            System.out.println("This link helps you to troubleshoot the issue: "+resolveUrl);
        }
        return resolveUrl;
    }

    /**
     * Build HTTP call with the given options.
     *
     * @param path The sub-path of the HTTP URL
     * @param method The request method, one of "GET", "HEAD", "OPTIONS", "POST", "PUT", "PATCH" and "DELETE"
     * @param queryParams The query parameters
     * @param collectionQueryParams The collection query parameters
     * @param body The request body object
     * @param headerParams The header parameters
     * @param formParams The form parameters
     * @param authNames The authentications to apply
     * @return The HTTP call
     * @throws ApiException If fail to serialize the request body object
     */
    public Call buildCall(String path, String method, List<Pair> queryParams, List<Pair> collectionQueryParams, Object body, Map<String, String> headerParams, Map<String, Object> formParams, String[] authNames) throws ApiException {
        setRequestParameters(path, method, body, headerParams.get("Content-Type"));
        Request request = buildRequest(path, method, queryParams, collectionQueryParams, body, headerParams, formParams, authNames);
        System.out.println("\nAPI: "+request.method()+" "+request.url());
        return httpClient.newCall(request);
    }

    /**
     * Build an HTTP request with the given options.
     *
     * @param path The sub-path of the HTTP URL
     * @param method The request method, one of "GET", "HEAD", "OPTIONS", "POST", "PUT", "PATCH" and "DELETE"
     * @param queryParams The query parameters
     * @param collectionQueryParams The collection query parameters
     * @param body The request body object
     * @param headerParams The header parameters
     * @param formParams The form parameters
     * @param authNames The authentications to apply
     * @return The HTTP request
     * @throws ApiException If fail to serialize the request body object
     */
    public Request buildRequest(String path, String method, List<Pair> queryParams, List<Pair> collectionQueryParams, Object body, Map<String, String> headerParams, Map<String, Object> formParams, String[] authNames) throws ApiException {
            updateParamsForAuth(authNames, queryParams, headerParams);

        final String url = buildUrl(path, queryParams, collectionQueryParams);
        final Request.Builder reqBuilder = new Request.Builder().url(url);
        processHeaderParams(headerParams, reqBuilder);

        String contentType = headerParams.get("Content-Type");
        // ensuring a default content type
        if (contentType == null) {
            contentType = "application/json";
        }

        RequestBody reqBody;
        if (!HttpMethod.permitsRequestBody(method)) {
            reqBody = null;
        } else if ("application/x-www-form-urlencoded".equals(contentType)) {
            reqBody = buildRequestBodyFormEncoding(formParams);
        } else if (body == null) {
            if ("DELETE".equals(method)) {
                // allow calling DELETE without sending a request body
                reqBody = null;
            } else {
                // use an empty request body (for POST, PUT and PATCH)
                reqBody = RequestBody.create(MediaType.parse(contentType), "");
            }
        } else {
            reqBody = serialize(body, contentType);
        }
        return reqBuilder.method(method, reqBody).build();
    }

    /**
     * Build full URL by concatenating base path, the given sub path and query parameters.
     *
     * @param path The sub path
     * @param queryParams The query parameters
     * @param collectionQueryParams The collection query parameters
     * @return The full URL
     */
    public String buildUrl(String path, List<Pair> queryParams, List<Pair> collectionQueryParams) {
        final StringBuilder url = new StringBuilder();
        url.append(basePath).append(path);

        if (queryParams != null && !queryParams.isEmpty()) {
            // support (constant) query string in `path`, e.g. "/posts?draft=1"
            String prefix = path.contains("?") ? "&" : "?";
            for (Pair param : queryParams) {
                if (param.getValue() != null) {
                    if (prefix != null) {
                        url.append(prefix);
                        prefix = null;
                    } else {
                        url.append("&");
                    }
                    String value = parameterToString(param.getValue());
                    url.append(escapeString(param.getName())).append("=").append(escapeString(value));
                }
            }
        }

        if (collectionQueryParams != null && !collectionQueryParams.isEmpty()) {
            String prefix = url.toString().contains("?") ? "&" : "?";
            for (Pair param : collectionQueryParams) {
                if (param.getValue() != null) {
                    if (prefix != null) {
                        url.append(prefix);
                        prefix = null;
                    } else {
                        url.append("&");
                    }
                    String value = parameterToString(param.getValue());
                    // collection query parameter value already escaped as part of parameterToPairs
                    url.append(escapeString(param.getName())).append("=").append(value);
                }
            }
        }

        return url.toString();
    }

    /**
     * Set header parameters to the request builder, including default headers.
     *
     * @param headerParams Header parameters in the form of Map
     * @param reqBuilder Request Builder
     */
    public void processHeaderParams(Map<String, String> headerParams, Request.Builder reqBuilder) {
        for (Entry<String, String> param : headerParams.entrySet()) {
            reqBuilder.header(param.getKey(), parameterToString(param.getValue()));
        }
        for (Entry<String, String> header : defaultHeaderMap.entrySet()) {
            if (!headerParams.containsKey(header.getKey())) {
                reqBuilder.header(header.getKey(), parameterToString(header.getValue()));
            }
        }
    }

    /**
     * Update query and header parameters based on authentication settings.
     *
     * @param authNames The authentications to apply
     * @param queryParams  List of query parameters
     * @param headerParams  Map of header parameters
     * @throws ApiException If Authentication is undefined
     */
    public void updateParamsForAuth(String[] authNames, List<Pair> queryParams, Map<String, String> headerParams) throws ApiException {
        for (String authName : authNames) {
            Authentication auth = authentications.get(authName);
            if (auth == null) throw new ApiException("Authentication undefined: " + authName);
            auth.applyToParams(queryParams, headerParams);
        }
    }

    /**
     * Build a form-encoding request body with the given form parameters.
     *
     * @param formParams Form parameters in the form of Map
     * @return RequestBody
     */
    public RequestBody buildRequestBodyFormEncoding(Map<String, Object> formParams) {
        FormEncodingBuilder formBuilder  = new FormEncodingBuilder();
        for (Entry<String, Object> param : formParams.entrySet()) {
            formBuilder.add(param.getKey(), parameterToString(param.getValue()));
        }
        return formBuilder.build();
    }
}
