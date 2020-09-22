package jp.ne.paypay.api;

import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import jp.ne.paypay.ApiClient;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.ApiResponse;
import jp.ne.paypay.Configuration;
import jp.ne.paypay.JSON;
import jp.ne.paypay.Pair;
import jp.ne.paypay.auth.Authentication;
import jp.ne.paypay.auth.HmacAuth;
import jp.ne.paypay.model.QRCode;
import jp.ne.paypay.model.QRCodeDetails;
import jp.ne.paypay.model.ResultInfo;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiClientTest {

    ApiClient apiClient = new ApiClient();

    @Mock
    private Call call;

    @BeforeEach
    void  init(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void configTest(){
        Configuration configuration = new Configuration();
        apiClient.setProductionMode(false);
        apiClient.setApiKey("api-key");
        apiClient.setApiSecretKey("api-secret-key");
        apiClient.setBasePath("basePath");
        configuration.setDefaultApiClient(apiClient);
        Assert.assertNotNull(configuration.getDefaultApiClient());
        Assert.assertNotNull(apiClient.getBasePath());
        Assert.assertFalse(apiClient.isProductionMode());
        Assert.assertEquals(apiClient.getBasePathProd(), "https://api.paypay.ne.jp");
        Assert.assertEquals(apiClient.getBasePathSandbox(), "https://stg-api.sandbox.paypay.ne.jp");
        apiClient.setProductionMode(true);
        Assert.assertEquals(apiClient.getBasePath(), "https://api.paypay.ne.jp");
        apiClient.setBasePathProd("prodUrl");
        Assert.assertEquals(apiClient.getBasePathProd(), "prodUrl");
        apiClient.setBasePathSandbox("sandboxUrl");
        Assert.assertEquals(apiClient.getBasePathSandbox(), "sandboxUrl");
        apiClient.setDefaultHeaderMap(new HashMap<>());
        Assert.assertNotNull(apiClient.getDefaultHeaderMap());
        Assert.assertEquals(apiClient.getAuthentications().size(), 1);
        Assert.assertNotNull(apiClient.getAuthentication("HmacAuth"));
        Map<String, Authentication> authentications = new HashMap<>();
        authentications.put("HmacAuth", new HmacAuth());
        authentications.put("BasicAuth", null);
        apiClient.setAuthentications(authentications);
        Assert.assertEquals(authentications.size(), 2);
        apiClient.setAssumeMerchant("merchantKey");
        Assert.assertEquals(apiClient.getDefaultHeaderMap().get("X-ASSUME-MERCHANT"), "merchantKey");
        Assert.assertEquals(apiClient.getAssumeMerchant(), "merchantKey");
        apiClient.setJson(new JSON());
        Assert.assertNotNull(apiClient.getJson());

    }

    @Test
    public void parameterToStringTest(){
        List<String> params = new ArrayList<>();
        params.add("Id");
        params.add("Name");
        String parameterToString = apiClient.parameterToString(params);
        Assert.assertEquals(parameterToString, "Id,Name");
        Date date  = new GregorianCalendar(2020, 2, 5).getTime();
        parameterToString = apiClient.parameterToString(date);
        Assert.assertTrue(parameterToString.startsWith("2020-"));
    }
    @Test
    public void parameterToPairTest(){
        List<Pair> pairs = apiClient.parameterToPair("name", "paypay");
        Assert.assertEquals(pairs.get(0).getName(), "name");
        Assert.assertEquals(pairs.get(0).getValue(), "paypay");
    }

    @Test
    public void sanitizeFileNameTest(){
        String sanitizeFilename = apiClient.sanitizeFilename("/temp/test/file.txt");
        Assert.assertEquals(sanitizeFilename, "file.txt");
    }

    @Test
    public void isJsonMimeTest(){
        boolean isJsonMime = apiClient.isJsonMime("application/json");
        Assert.assertEquals(isJsonMime, true);
        isJsonMime = apiClient.isJsonMime("application/text");
        Assert.assertEquals(isJsonMime, false);
    }

    @Test
    public void selectHeaderAcceptTest(){
        String[] accepts = {"application/json"};
        String selectHeaderAccept = apiClient.selectHeaderAccept(accepts);
        Assert.assertEquals(selectHeaderAccept, "application/json");
        selectHeaderAccept = apiClient.selectHeaderAccept(new String[1]);
        System.out.println(selectHeaderAccept);
        Assert.assertEquals("null", selectHeaderAccept);
        accepts[0] = "application/text";
        selectHeaderAccept = apiClient.selectHeaderAccept(accepts);
        Assert.assertEquals(selectHeaderAccept, "application/text");
    }

    @Test
    public void selectHeaderContentTypeTest(){
        String[] accepts = {"application/json"};
        String headerContentType = apiClient.selectHeaderContentType(accepts);
        Assert.assertEquals(headerContentType, "application/json");
        headerContentType = apiClient.selectHeaderContentType(new String[0]);
        System.out.println(headerContentType);
        Assert.assertEquals("application/json;charset=UTF-8", headerContentType);
        accepts[0] = "application/text";
        headerContentType = apiClient.selectHeaderContentType(accepts);
        Assert.assertEquals(headerContentType, "application/text");
    }

    @Test
    public void escapeStringTest(){
        String escapeString = apiClient.escapeString("temp/test");
        Assert.assertEquals(escapeString, "temp%2Ftest");
    }

    @Test
    public void deserializeTest() throws ApiException {
        Request.Builder requestBuild = new Request.Builder();
        requestBuild.url("http://paypay.ne.jp/v2/qrcode");
        requestBuild.header("content-type", "application/json");
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), "{\"merchantPaymentId\":'paymentId'}");
        requestBuild.post(requestBody);
        Request request = requestBuild.build();

        Response.Builder builder = new Response.Builder();
        builder.header("content-type","application/json");
        builder.request(request);
        builder.code(200);
        builder.protocol(Protocol.HTTP_2);

        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMessage("SUCCESS");
        QRCodeDetails qrCodeDetails = new QRCodeDetails();
        qrCodeDetails.setResultInfo(resultInfo);
        JSON json = new JSON();
        builder.body(ResponseBody.create(MediaType.parse("application/json"), json.serialize(qrCodeDetails) ));
        Response response = builder.build();

        Type localVarReturnType = new TypeToken<QRCodeDetails>() {
        }.getType();
        Object result = apiClient.deserialize(response, localVarReturnType);
        Assert.assertTrue(result instanceof QRCodeDetails);

        localVarReturnType = new TypeToken<File>() {
        }.getType();
        result = apiClient.deserialize(response, localVarReturnType);
        Assert.assertTrue(result instanceof File);

        json.setLenientOnJson(true);
        builder.body(ResponseBody.create(MediaType.parse("application/json"), json.serialize(qrCodeDetails) ));
        response = builder.build();
        result = apiClient.deserialize(response, localVarReturnType);
        Assert.assertTrue(result instanceof File);

        result = apiClient.deserialize(null, localVarReturnType);
        Assert.assertNull(result);

    }

    @Test
    public void handleResponseTest() throws ApiException, IOException {
        Request.Builder requestBuild = new Request.Builder();
        requestBuild.url("http://paypay.ne.jp/v2/qrcode");
        requestBuild.header("content-type", "application/json");
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), "{\"merchantPaymentId\":'paymentId'}");
        requestBuild.post(requestBody);
        Request request = requestBuild.build();

        Response.Builder builder = new Response.Builder();
        builder.header("content-type","application/json");
        builder.request(request);
        builder.code(200);
        builder.protocol(Protocol.HTTP_2);

        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMessage("SUCCESS");
        QRCodeDetails qrCodeDetails = new QRCodeDetails();
        qrCodeDetails.setResultInfo(resultInfo);
        JSON json = new JSON();
        builder.body(ResponseBody.create(MediaType.parse("application/json"), json.serialize(qrCodeDetails) ));
        Response response = builder.build();

        Type localVarReturnType = new TypeToken<QRCodeDetails>() {
        }.getType();
        Object result = apiClient.handleResponse(response, localVarReturnType);
        Assert.assertTrue(result instanceof QRCodeDetails);

         result = apiClient.handleResponse(response, null);
        Assert.assertFalse(result instanceof QRCodeDetails);

        localVarReturnType = new TypeToken<File>() {
        }.getType();
        result = apiClient.handleResponse(response, localVarReturnType);
        Assert.assertTrue(result instanceof File);

        Mockito.when(call.execute()).thenReturn(response);
        ApiResponse<Object> apiResponse = apiClient.execute(call, localVarReturnType);
        Assert.assertEquals(200, apiResponse.getStatusCode());
        Mockito.when(call.execute()).thenThrow(IOException.class);
        Assert.assertThrows(ApiException.class, ()->apiClient.execute(call, new TypeToken<File>() {
        }.getType()));
    }

    @Test
    public void handleUnsuccessfulResponseTest() throws ApiException, IOException {
        Request.Builder requestBuild = new Request.Builder();
        requestBuild.url("http://paypay.ne.jp/v2/qrcode");
        requestBuild.header("content-type", "application/json");
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), "{\"merchantPaymentId\":'paymentId'}");
        requestBuild.post(requestBody);
        Request request = requestBuild.build();

        Response.Builder builder = new Response.Builder();
        builder.header("content-type","application/json");
        builder.request(request);
        builder.code(400);
        builder.protocol(Protocol.HTTP_2);

        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMessage("FAILED");
        QRCodeDetails qrCodeDetails = new QRCodeDetails();
        qrCodeDetails.setResultInfo(resultInfo);
        JSON json = new JSON();
        ResponseBody responseBody = ResponseBody.create(MediaType.parse("application/json"), json.serialize(qrCodeDetails));
        builder.body(responseBody);
        Response response = builder.build();

        Type localVarReturnType = new TypeToken<QRCodeDetails>() {
        }.getType();
        Assert.assertThrows(ApiException.class, () -> apiClient.handleResponse(response, localVarReturnType));
        Assert.assertThrows(ApiException.class, () -> apiClient.handleResponse(response, null));
    }

    @Test
    public void serializeTest() throws ApiException {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMessage("SUCCESS");
        QRCodeDetails qrCodeDetails = new QRCodeDetails();
        qrCodeDetails.setResultInfo(resultInfo);
        RequestBody serialize = apiClient.serialize(qrCodeDetails, "application/json");
        Assert.assertEquals("application/json; charset=utf-8",serialize.contentType().toString());
    }

    @Test
    public void buildRequest() throws ApiException {
        List<Pair> queryParams = new ArrayList<>();
        queryParams.add(new Pair("name", "paypay"));
        QRCode qrCode = new QRCode();
        qrCode.setMerchantPaymentId("maerchant_payemnt_id");
        Map<String, String> headerParams = new HashMap<>();
        headerParams.put("content-type", "application/json");
        String[] localVarAuthNames = new String[]{"HmacAuth"};
        Request request = apiClient.buildRequest("/v2/qrcode", "POST", queryParams, queryParams, qrCode, headerParams, null, localVarAuthNames);
        Assert.assertEquals(request.method(), "POST");
        Assert.assertTrue(request.urlString().contains("/v2/qrcode"));
        request = apiClient.buildRequest("/v2/qrcode", "POST", queryParams, queryParams, null, headerParams, null, localVarAuthNames);
        Assert.assertNotNull(request.body());
        request = apiClient.buildRequest("/v2/qrcode", "DELETE", queryParams, queryParams, null, headerParams, null, localVarAuthNames);
        Assert.assertNull(request.body());
    }

    @Test
    public void apiExceptionTest(){
        ApiException apiException = new ApiException();
        Assert.assertNull(apiException.getCause());
        apiException = new ApiException(new Throwable("throwable"));
        Assert.assertEquals(apiException.getCause().getMessage(), "throwable");
        apiException = new ApiException(100, null, "ResponseBody");
        Assert.assertEquals(apiException.getCode(), 100);
        apiException = new ApiException(100, "Message");
        Assert.assertEquals(apiException.getMessage(), "Message");
        apiException = new ApiException(100, "Message_1", null, "ResponseBody");
        Assert.assertEquals(apiException.getMessage(), "Message_1");
        Assert.assertEquals(apiException.getResponseBody(), "ResponseBody");
        Assert.assertNull(apiException.getResponseHeaders());

    }

    @Test
    public void buildRequestBodyFormEncodingTest(){
        Map<String, Object> formParams = new HashMap<>();
        formParams.put("name","paypay");
        formParams.put("expiresAt", new Date());
        RequestBody requestBody = apiClient.buildRequestBodyFormEncoding(formParams);
        Assert.assertEquals(MediaType.parse("application/x-www-form-urlencoded"),requestBody.contentType());
    }

    @Test
    public void buildCallTest() throws ApiException {
        Map<String, Object> formParams = new HashMap<>();
        formParams.put("name","paypay");
        formParams.put("expiresAt", new Date());
        Map<String, String> headerParams = new HashMap<>();
        headerParams.put("content-type", "application/json");
        Call call = apiClient.buildCall("/v2/path","GET", null, null, null, headerParams, formParams, new String[]{"HmacAuth"});
        Assert.assertNotNull(call);
    }
}
